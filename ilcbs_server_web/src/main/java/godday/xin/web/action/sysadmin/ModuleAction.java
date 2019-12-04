package godday.xin.web.action.sysadmin;

import com.opensymphony.xwork2.ModelDriven;
import godday.xin.domain.Module;
import godday.xin.service.ModuleService;
import godday.xin.utils.Page;
import godday.xin.web.action.BaseAction;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Namespace("/sysadmin")
@Result(name="alist",
        type="redirectAction",
        location = "/moduleAction_list")
public class ModuleAction extends BaseAction implements ModelDriven{
    @Autowired
    private ModuleService moduleService;
    private Module model=new Module();
    private Page page = new Page();
    public void setModel(Module model) {
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
    @Action(value = "moduleAction_list",
            results = {@Result(name="list",location = "/WEB-INF/pages/sysadmin/module/jModuleList.jsp")} )
    public String list(){
      org.springframework.data.domain.Page page2=
              moduleService.findPage(null, new PageRequest(page.getPageNo()-1, page.getPageSize()));
      page.setTotalPage(page2.getTotalPages()); //总页数
      page.setTotalRecord(page2.getTotalElements()); //总记录书
      page.setResults(page2.getContent());
      page.setUrl("moduleAction_list"); //上页下页跳转的路径
      super.push(page);
        return "list";
    }



    @Action(value = "moduleAction_delete")
    public String delete(){
        String []ids = model.getId().split(", ");
        moduleService.delete(ids);
        return "alist";
    }


    @Action(value = "moduleAction_toview",
            results = {@Result(name="toview",location = "/WEB-INF/pages/sysadmin/module/jModuleView.jsp")})
    public String toView(){
        Module module = (Module) moduleService.get(model.getId());
        super.push(module);
        return "toview";
    }


    @Action(value = "moduleAction_tocreate",
            results = {@Result(name="tocreate",location = "/WEB-INF/pages/sysadmin/module/jModuleCreate.jsp")})
    public String tocreate() {
        return "tocreate";
    }

    @Action(value = "moduleAction_insert")
    public String to_insert(){
        moduleService.saveOrUpdate(model);
        return "alist";
    }

    @Action(value = "moduleAction_toupdate",
            results = {@Result(name="to_update",
                    location = "/WEB-INF/pages/sysadmin/module/jModuleUpdate.jsp")})
    public String update(){
       /* Module module=moduleService.get(model.getId());
        Specification spc = new Specification() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery query, CriteriaBuilder cb) {
                return cb.isNotNull(root);
            }
        };
        List<Module> moduleList=moduleService.find(spc);
        super.put("moduleList",moduleList);
        super.push(module);
        return "to_update";*/
       Module module = moduleService.get(model.getId());
       super.push(module);

        return "to_update";
    }
    @Action(value = "moduleAction_update")
    public String save(){
        Module module = moduleService.get(model.getId());
        module.setName(model.getName());
        module.setLayerNum(model.getLayerNum());
        module.setCpermission(model.getCpermission());
        module.setCurl(model.getCurl());
        module.setCtype(model.getCtype());
        module.setState(model.getState());
        module.setBelong(model.getBelong());
        module.setCwhich(model.getCwhich());
        module.setRemark(model.getRemark());
        module.setOrderNo(model.getOrderNo());
        moduleService.saveOrUpdate(module);
        super.put("save_flag","保存成功！");
        return "alist";
    }

    /*
<table class="commonTable" cellspacing="1">
	        <tr>
	            <td class="columnTitle">模块名：</td>
	            <td class="tableContent"><input type="text" name="name" value="${name}"/></td>
	            <td class="columnTitle">层数：</td>
	            <td class="tableContent"><input type="text" name="layerNum" value="${layerNum}"/></td>
	        </tr>
	        <tr>
	            <td class="columnTitle">权限标识：</td>
	            <td class="tableContent"><input type="text" name="cpermission" value="${cpermission}"/></td>
	            <td class="columnTitle">链接：</td>
	            <td class="tableContent"><input type="text" name="curl" value="${curl}"/></td>
	        </tr>
	        <tr>
	            <td class="columnTitle">类型：</td>
	            <td class="tableContentAuto">
	            	<input type="radio" name="ctype" value="0" <c:if test="${ctype==0}">checked</c:if> class="input"/>主菜单
	            	<input type="radio" name="ctype" value="1" <c:if test="${ctype==1}">checked</c:if> class="input"/>左侧菜单
	            	<input type="radio" name="ctype" value="2" <c:if test="${ctype==2}">checked</c:if> class="input"/>按钮
	            	<input type="radio" name="ctype" value="3" <c:if test="${ctype==3}">checked</c:if> class="input"/>链接
	            	<input type="radio" name="ctype" value="4" <c:if test="${ctype==4}">checked</c:if> class="input"/>状态
	            </td>
	            <td class="columnTitle">状态：</td>
	            <td class="tableContentAuto">
	            	<input type="radio" name="state" value="1" <c:if test="${state==1}">checked</c:if> class="input"/>启用
	            	<input type="radio" name="state" value="0" <c:if test="${state==0}">checked</c:if> class="input"/>停用
	            </td>
	        </tr>
	        <tr>
	            <td class="columnTitle">从属：</td>
	            <td class="tableContent"><input type="text" name="belong" value="${belong}"/></td>
	            <td class="columnTitle">复用标识：</td>
	            <td class="tableContent"><input type="text" name="cwhich" value="${cwhich}"/></td>
	        </tr>
	        <tr>
	            <td class="columnTitle">说明：</td>
	            <td class="tableContent">
	            	<textarea name="remark" style="height:120px;">${remark}</textarea>
	            </td>
	            <td class="columnTitle">排序号：</td>
	            <td class="tableContent"><input type="text" name="orderNo" value="${orderNo}"/></td>
	        </tr>
		</table>

     */
}
