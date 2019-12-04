package godday.xin.web.action.sysadmin;

import com.opensymphony.xwork2.ModelDriven;
import godday.xin.domain.Dept;
import godday.xin.service.DeptService;
import godday.xin.utils.Page;
import godday.xin.web.action.BaseAction;
import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;
@Namespace("/sysadmin")
@Result(name="alist",
        type="redirectAction",
        location = "/deptAction_list")
public class DeptAction extends BaseAction implements ModelDriven{
    @Autowired
    private DeptService deptService;
    private Dept model=new Dept();
    private Page page = new Page();
    public void setModel(Dept model) {
        this.model = model;
    }
    public Page getPage() {
        return page;
    }
    public void setPage(Page page) {
        this.page = page;
    }

    @Override
    public Object getModel() {
        return model;
    }

    @Action(value = "deptAction_list",
            results = {@Result(name="list",location = "/WEB-INF/pages/sysadmin/dept/jDeptList.jsp")} )
    public String list(){
      org.springframework.data.domain.Page page2=
              deptService.findPage(null, new PageRequest(page.getPageNo()-1, page.getPageSize()));
      page.setTotalPage(page2.getTotalPages()); //总页数
      page.setTotalRecord(page2.getTotalElements()); //总记录书
      page.setResults(page2.getContent());
      page.setUrl("deptAction_list"); //上页下页跳转的路径
        System.out.println("脚本为:"+page.getLinks());
      super.push(page);
        return "list";
    }



    @Action(value = "deptAction_toview",
            results = {@Result(name="toview",location = "/WEB-INF/pages/sysadmin/dept/jDeptView.jsp")})
    public String toView(){
        Dept dept = deptService.get(model.getId());
        super.push(dept);
        return "toview";
    }



    @Action(value = "deptAction_tocreate",
            results = {@Result(name="tocreate",location = "/WEB-INF/pages/sysadmin/dept/jDeptCreate.jsp")})
    public String tocreate() {
        Specification<Dept> spec=new Specification<Dept>() {
            @Override
            public Predicate toPredicate(Root<Dept> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                return cb.equal(root.get("state").as(Integer.class), 1);
            }
        };
        List<Dept> deptList=deptService.find(spec);
        super.put("deptList",deptList);
        return "tocreate";
    }


    @Action(value = "deptAction_insert")
    public String to_insert(){
        deptService.saveOrUpdate(model);
        return "alist";
    }

     @Action(value = "deptAction_toupdate",
            results = {@Result(name="to_update",
                    location = "/WEB-INF/pages/sysadmin/dept/jDeptUpdate.jsp")})
    public String update(){
       /* Dept dept=deptService.get(model.getId());
        Specification spc = new Specification() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery query, CriteriaBuilder cb) {
                return cb.isNotNull(root);
            }
        };
        List<Dept> deptList=deptService.find(spc);
        super.put("deptList",deptList);
        super.push(dept);
        return "to_update";*/
         return "to_update";
    }

    @Action(value = "deptAction_update")
    public String save(){
        deptService.saveOrUpdate(model);
        super.put("save_flag","保存成功！");
        return "alist";
    }

    @Action(value = "deptAction_delete")
    public String delete(){
        String []ids = model.getId().split(", ");
        deptService.delete(ids);
        return "alist";
    }
}
