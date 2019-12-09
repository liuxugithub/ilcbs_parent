package godday.xin.web.shiro;

import godday.xin.domain.Module;
import godday.xin.domain.Role;
import godday.xin.domain.User;
import godday.xin.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Set;

public class AuthRealm extends AuthorizingRealm {

    @Autowired
    private  UserService userservice;
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        /*System.out.println("调用了授权方法1111");
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addStringPermission("系统首页");
        User user = (User) principalCollection.getPrimaryPrincipal();
        Set<Role> r_list= user.getRoles();
        for(Role role : r_list){
            Set<Module> modules = role.getModules();
            for(Module module : modules){
                System.out.println("Cpermission"+module.getCpermission());
                info.addStringPermission(module.getCpermission());
            }
        }
        System.out.println("11111111dfsf");
        System.out.println("hello,world"+info.getStringPermissions());
        return info;*/
        // TODO Auto-generated method stub
        System.out.println("调用了授权方法");
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        User user = (User) principalCollection.getPrimaryPrincipal();
        //获取用户的角色及角色的模块
        Set<Role> roles = user.getRoles();
        System.out.println(roles);
        System.out.println("角色数量:"+roles.size());
        for (Role role : roles) {
            System.out.println("角色:"+role.getName());
            Set<Module> modules = role.getModules();
            for (Module module : modules) {
                if(!module.getCpermission().equals("null")){
                    System.out.println("权限:"+module.getCpermission());
                    info.addStringPermission(module.getCpermission());

                }

            }
        }
/*		Set<String> stringPermissions = info.getStringPermissions();
		System.out.println(stringPermissions);*/
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("调用了认证方法");
        UsernamePasswordToken u = (UsernamePasswordToken) authenticationToken;
        final String name=u.getUsername();

        List<User> users=  userservice.find(new Specification<User>() {
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                return cb.equal(root.get("userName").as(String.class),name);
            }
        });
        //principal(用户标示) , credentials(用户凭据), realmName
        if(users !=null && users.size()>0){
            User user = users.get(0);
            return new SimpleAuthenticationInfo(user, user.getPassword(),getName());
        }

        return null;
    }

}
