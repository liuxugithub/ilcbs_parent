package godday.xin.web.action.sysadmin;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import godday.xin.domain.Dept;
import godday.xin.domain.Role;
import godday.xin.domain.User;
import godday.xin.domain.Userinfo;
import godday.xin.service.DeptService;
import godday.xin.service.RoleService;
import godday.xin.service.UserService;
import godday.xin.utils.Page;
import godday.xin.web.action.BaseAction;
import lombok.Getter;
import lombok.Setter;
import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.*;
@Namespace("/sysadmin")
@Result(name="alist",
        type="redirectAction",
        location = "/userAction_list")
@Getter
@Setter
public class UserAction extends BaseAction implements ModelDriven{
    @Autowired
    private UserService UserService;
    @Autowired
    private DeptService deptService;

    @Autowired
    private RoleService roleService;
    private String[] roleIds;
    private User model=new User();
    private Page page = new Page();
    @Action(value = "userAction_list",
            results = {@Result(name="list",location = "/WEB-INF/pages/sysadmin/user/jUserList.jsp")} )
    public String list(){
        System.out.println("进来了，开始处理");
      org.springframework.data.domain.Page page2=
              UserService.findPage(null, new PageRequest(page.getPageNo()-1, page.getPageSize()));
      page.setTotalPage(page2.getTotalPages()); //总页数
      page.setTotalRecord(page2.getTotalElements()); //总记录书
      page.setResults(page2.getContent());
      page.setUrl("userAction_list"); //上页下页跳转的路径
        System.out.println("脚本为:"+page.getLinks());
      super.push(page);
        return "list";
    }
    @Action(value = "userAction_toview",
            results = {@Result(name="toview",location = "/WEB-INF/pages/sysadmin/user/jUserView.jsp")})
    public String toView(){
        User User = UserService.get(model.getId());
        super.push(User);
        return "toview";
    }

    @Action(value = "userAction_tocreate",
            results = {@Result(name="tocreate",location = "/WEB-INF/pages/sysadmin/user/jUserCreate.jsp")})
    public String tocreate() {
        Specification<User> spec=new Specification<User>() {
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                return cb.equal(root.get("state").as(Integer.class), 1);
            }
        };
        List<User> UserList=UserService.find(spec);
        super.put("userList",UserList);
        List<Dept> deptList=deptService.find(new Specification<Dept>() {
            @Override
            public Predicate toPredicate(Root<Dept> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                return cb.equal(root.get("state").as(Integer.class), 1);
            }
        });
        super.put("deptList",deptList);
        return "tocreate";
    }
    @Action(value = "userAction_insert")
    public String to_insert(){
        UserService.saveOrUpdate(model);
        return "alist";
    }
     @Action(value = "userAction_toupdate",
            results = {@Result(name="to_update",
                    location = "/WEB-INF/pages/sysadmin/user/jUserUpdate.jsp")})
    public String update(){
        /* User user = UserService.get(model.getId());
         super.push(user);
         Specification spec = new Specification() {
             @Override
             public Predicate toPredicate(Root root, CriteriaQuery query, CriteriaBuilder cb) {
                 return cb.isNotNull(root);
             }
         };
         List<Dept> depts=deptService.find(spec);
         super.put("deptList",depts);
         super.push(UserService.get(model.getId()).getDept());*/
         User user = UserService.get(model.getId());
         super.push(user);
         // 2.查询部门列表，压入值栈中
         Specification<Dept> spec = new Specification<Dept>() {
             // root:取当前对象的属性.as(属性中的类型) query：order，group cb:or and equles from
             // User where state = 1
             @Override
             public Predicate toPredicate(Root<Dept> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                 // TODO Auto-generated method stub
                 return cb.equal(root.get("state").as(Integer.class), 1);
             }
         };
         List<Dept> deptList = deptService.find(spec);
         super.put("deptList", deptList);
         return "to_update";
    }
    @Action(value = "userAction_update")
    public String save(){
        System.out.println("id_string:"+model.getId());
        User user=UserService.get(model.getId());
        user.setDept(model.getDept());
        user.getUserinfo().setName(model.getUserinfo().getName());
        UserService.saveOrUpdate(user);
        super.put("save_flag","保存成功！");

      /*  // TODO Auto-generated method stub
        // 根据id查询数据库的对象
        User user = UserService.get(model.getId());
        user.setDept(model.getDept());
        user.getUserinfo().setName(model.getUserinfo().getName());
        user.setState(model.getState());
        UserService.saveOrUpdate(user);*/
        return "alist";
    }
    @Action(value = "userAction_delete")
    public String delete(){
        String []ids = model.getId().split(", ");
        UserService.delete(ids);
        return "alist";
    }

    @Action(value = "userAction_torole" , results = {@Result(name="user_role",
            location = "/WEB-INF/pages/sysadmin/user/jUserRole.jsp")})
    public String toRole(){

        User user=UserService.get(model.getId());
        Set<Role> roleList= user.getRoles();
        Userinfo userinfo = user.getUserinfo();
        List<Role> roles= roleService.find(new Specification<Role>() {
            @Override
            public Predicate toPredicate(Root<Role> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                return cb.isNotNull(root);
            }
        });
        List<String> roleStr = new ArrayList<>();
        for(Role role: roleList){
            roleStr.add(role.getName());
        }
        super.put("roleStr",roleStr);
        super.put("roleList",roles);
        super.put("userinfo",userinfo);
        return "user_role";
    }



    @Action(value = "userAction_role")
    public String toSaveRoles(){
        User user=UserService.get(model.getId());
        Set<Role> roles = new HashSet<>();
        for(String id : roleIds){
            System.out.println("读取的id:"+id);
            roles.add(roleService.get(id));
        }
        user.setRoles(roles);
        UserService.saveOrUpdate(user);
        return "alist";
    }
}
