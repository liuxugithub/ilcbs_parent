package godday.xin.web.shiro;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
public class PasswordMatcher extends SimpleCredentialsMatcher
{

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        System.out.println("调用了密码比较器");
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
        String username=usernamePasswordToken.getUsername();
        String password=new String(usernamePasswordToken.getPassword());
        String pwd=(String)info.getCredentials();
        return equals(password, pwd);
    }
}
