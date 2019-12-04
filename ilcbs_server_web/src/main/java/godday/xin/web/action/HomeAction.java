package godday.xin.web.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 * @Description:
 * @Author:		传智播客 java学院	传智.宋江
 * @Company:	http://java.itcast.cn
 * @CreateDate:	2014年10月31日
 */

@Namespace("/")
@Results({
		@Result(name="title",location="/WEB-INF/pages/home/title.jsp"),
		@Result(name="fmain",location="/WEB-INF/pages/home/fmain.jsp"),
		@Result(name="toleft",location="/WEB-INF/pages/${moduleName}/left.jsp"),
		@Result(name="tomain",location="/WEB-INF/pages/${moduleName}/main.jsp")
})
public class HomeAction extends BaseAction{

	private String moduleName;		//动态指定跳转的模块，在struts.xml中配置动态的result
	public String getModuleName() {
		return moduleName;
	}
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	@Action("homeAction_fmain")
	public String fmain(){
		return "fmain";
	}
	@Action("homeAction_title")
	public String title(){
		return "title";
	}

	//转向moduleName指向的模块
	@Action("homeAction_tomain")
	public String tomain(){
		return "tomain";
	}

	@Action("homeAction_toleft")
	public String toleft(){
		return "toleft";
	}

}
