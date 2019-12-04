package godday.xin.web.action.sysadmin;

import com.alibaba.fastjson.JSON;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import godday.xin.domain.Dept;
import godday.xin.domain.Module;
import godday.xin.domain.Role;
import godday.xin.exception.SysException;
import godday.xin.service.DeptService;
import godday.xin.service.ModuleService;
import godday.xin.service.RoleService;
import godday.xin.utils.Page;
import godday.xin.utils.UtilFuns;
import godday.xin.web.action.BaseAction;
import lombok.Getter;
import lombok.Setter;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.omg.Dynamic.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
@Namespace("/sysadmin")
@Result(name="alist",
        type="redirectAction",
        location = "/roleAction_list")
@Getter
@Setter
public class RoleAction extends BaseAction implements ModelDriven{
    @Autowired
    private RoleService RoleService;
    @Autowired
    private ModuleService moduleService;
    private Role model=new Role();
    private Page page = new Page();
    private  String[] id;
    private  String [] moduleIds;
    public String[] getId() {
        return id;
    }
    public void setId(String[] id) {
        this.id = id;
    }
    @Action(value = "roleAction_list",
            results = {@Result(name="list",location = "/WEB-INF/pages/sysadmin/role/jRoleList.jsp")} )
    public String list(){
        System.out.println("进来了，开始处理");
      org.springframework.data.domain.Page page2=
      RoleService.findPage(null, new PageRequest(page.getPageNo()-1, page.getPageSize()));
      page.setTotalPage(page2.getTotalPages()); //总页数
      page.setTotalRecord(page2.getTotalElements()); //总记录书
      page.setResults(page2.getContent());
      page.setUrl("roleAction_list"); //上页下页跳转的路径
      super.push(page);
        return "list";
    }
    @Action(value = "roleAction_toview",
            results = {@Result(name="toview",location = "/WEB-INF/pages/sysadmin/role/jRoleView.jsp")})
    public String toView(){
        Role Role = RoleService.get(model.getId());
        super.push(Role);
        return "toview";
    }

    @Action(value = "roleAction_tocreate",
            results = {@Result(name="tocreate",location = "/WEB-INF/pages/sysadmin/role/jRoleCreate.jsp")})
    public String tocreate() {
        return  "tocreate";
    }
    @Action(value = "roleAction_insert")
    public String to_insert(){
        RoleService.saveOrUpdate(model);
        return "alist";
    }
     @Action(value = "roleAction_toupdate",
            results = {@Result(name="to_update",
                    location = "/WEB-INF/pages/sysadmin/role/jRoleUpdate.jsp")})
    public String update(){
        Role role=RoleService.get(model.getId());
        super.push(role);
        return  "to_update";
    }
    @Action(value = "roleAction_update")
    public String save(){
        Role role=RoleService.get(model.getId());
        role.setName(model.getName());
        role.setRemark(model.getRemark());
        RoleService.saveOrUpdate(role);
        return "alist";
    }
    @Action(value = "roleAction_delete")
    public String delete(){
        String []ids = model.getId().split(", ");
        RoleService.delete(ids);
        return "alist";
    }
    @Action(value = "roleAction_tomodule",results = {@Result(name="as_module",
            location="/WEB-INF/pages/sysadmin/role/jRoleModule.jsp")})
    public String PermissionMoudle() throws SysException {
        try {
            super.push(RoleService.get(model.getId()));
        }catch (Exception e){

            e.printStackTrace();
            throw  new SysException("请您勾选一个角色再进行此操作");
        }

        return  "as_module";
    }

    @Action(value = "roleActon_genzTreeNodes")
    public  String role_module() throws IOException {
        Role role = RoleService.get(model.getId());
        //选择角色的模块
        Set<Module> modules = role.getModules();
        //

        Set<String> module_ids=new HashSet<>();
        for(Module module : modules){
            module_ids.add(module.getId());
        }
        List<Module> all_module=moduleService.find(new Specification<Module>() {
            @Override
            public Predicate toPredicate(Root<Module> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                return cb.equal(root.get("state").as(Integer.class),"1");
            }
        });
        List list  = new ArrayList();
        for(Module module : all_module){
            Map map=new HashMap<>();
            map.put("id", module.getId());
            map.put("name",module.getName());
            map.put("pid", module.getParentId());
            if(module_ids.contains(module.getId())){
                map.put("checked", true);
            }
            list.add(map);
        }
        String jsonString = JSON.toJSONString(list);
        System.out.println("jsonString:"+jsonString);

/*
[
			{ id:1, pId:0, name:"随意勾选 1"},
			{ id:111, pId:11, name:"随意勾选 1-1-1"}]
 */
        StringBuilder sb = new StringBuilder();
        /*int size=all_module.size();
        sb.append("[");
        for(Module module : all_module){
            size--;
            sb.append("{");
           sb.append("\"id\":").
                   append("\""+module.getId()+"\"").
                   append(",\"pId\":").append("\""+module.getParentId()+"\"").
                   append(",\"name\":").append("\""+module.getName()+"\"");
           if(module_ids.contains(module.getId())){
               sb.append(",\"checked\":\"true\"");
           }
            sb.append("}");
           if(size>0){
               sb.append(",");
           }
        }
        sb.append("]");
        */

        HttpServletResponse response = ServletActionContext.getResponse();
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(jsonString);
        return NONE;
    }

    @Action(value = "roleAction_module")
    public String save_role_module(){
        Role role = RoleService.get(model.getId());
        Set<Module> modules = new HashSet<>();
        for(String s : moduleIds){
            modules.add(moduleService.get(s));
        }
        role.setModules(modules);
        RoleService.saveOrUpdate(role);
        return "alist";
    }

}
