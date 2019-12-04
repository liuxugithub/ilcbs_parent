package godday.xin.web.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import godday.xin.domain.Module;
import godday.xin.domain.Role;
import godday.xin.domain.User;
import godday.xin.utils.SysConstant;
import godday.xin.utils.UtilFuns;
import org.apache.http.HttpResponse;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.session.HttpServletSession;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.StrutsStatics;
import org.apache.struts2.convention.annotation.*;
import org.apache.ws.commons.schema.constants.Constants;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.mail.Session;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Set;

/**
 * @Description: 登录和退出类
 * @Author:		传智播客 java学院	传智.宋江
 * @Company:	http://java.itcast.cn
 * @CreateDate:	2014年10月31日
 * 继承BaseAction的作用
 * 1.可以与struts2的API解藕合
 * 2.还可以在BaseAction中提供公有的通用方法
 */
@Namespace("/")
@Results({
	@Result(name="login",location="/WEB-INF/pages/sysadmin/login/login.jsp"),
	@Result(name="success",location="/WEB-INF/pages/home/fmain.jsp"),
	@Result(name="logout",location="/index.jsp")})
public class LoginAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private String username;
	private String password;
	//SSH传统登录方式
	@Action("loginAction_login")
	public String login() throws Exception {

//		if(true){
//			String msg = "登录错误，请重新填写用户名密码!";
//			this.addActionError(msg);
//			throw new Exception(msg);
//		}
//		User user = new User(username, password);
//		User login = userService.login(user);
//		if (login != null) {
//			ActionContext.getContext().getValueStack().push(user);
//			session.put(SysConstant.CURRENT_USER_INFO, login);	//记录session
//			return SUCCESS;
//		}
//		return "login";

		/*if (UtilFuns.isEmpty(username) && ActionContext.getContext().getSession().get(SysConstant.CURRENT_USER_INFO) == null)
			return "login";

		if (ActionContext.getContext().getSession().get(SysConstant.CURRENT_USER_INFO) != null) {
			return SUCCESS;
		} else {
			try {
				Subject subject = SecurityUtils.getSubject();
				Md5Hash hash = new Md5Hash(password, username, 2);
				System.out.println(hash.toString());
				UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username, hash.toString());
				subject.login(usernamePasswordToken);
				User user = (User) subject.getPrincipal();
				session.put(SysConstant.CURRENT_USER_INFO, user);
				return SUCCESS;
			} catch (Exception e) {
				e.printStackTrace();
				super.put("errorInfo", "用户名密码错误，请重新输入");
				return "login";
			}
		}*/
		if(UtilFuns.isEmpty(username)){
			return "login";
		}
		Subject subject = SecurityUtils.getSubject();
		// 组装token的时候，密码进行md5处理
		Md5Hash hash = new Md5Hash(password, username, 2);
		System.out.println("decode the pwd:"+hash.toString());
		UsernamePasswordToken token = new UsernamePasswordToken(username, hash.toString());
		try {
			subject.login(token);
			User user = (User) subject.getPrincipal();
			session.put(SysConstant.CURRENT_USER_INFO, user);
			return SUCCESS;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			super.put("errorInfo", "您的用户名或密码错误"); //登录页面的错误信息提示
			return "login";
		}
	}
	//退出
	@Action("loginAction_logout")
	public String logout(){
			session.remove(SysConstant.CURRENT_USER_INFO);//删除session
		SecurityUtils.getSubject().logout();   //登出
		return "logout";
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

}

