package studio.limo.web.blog.admin.shiro;

import org.apache.log4j.Logger;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import studio.limo.web.blog.core.bean.Permission;
import studio.limo.web.blog.core.bean.Role;
import studio.limo.web.blog.core.bean.User;
import studio.limo.web.blog.core.service.UserService;

import java.util.Set;

public class MyShiroRealm extends AuthorizingRealm {

    private static Logger logger = Logger.getLogger(MyShiroRealm.class);
    @Autowired
    UserService userService;
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        User user = (User) principals.getPrimaryPrincipal();
        Set<Role> roles = userService.findByAccount(user.getAccount()).getRoles();
        for (Role role :roles){
            authorizationInfo.addRole(role.getName());
            for (Permission permission:role.getPermissions()){
                authorizationInfo.addStringPermission(permission.getName());
            }
        }
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String account = (String)token.getPrincipal();
        User user = userService.findByAccount(account);
        if (user == null){
            return null;
        }
        return new SimpleAuthenticationInfo(user, user.getPassword(), getName());
    }
}
