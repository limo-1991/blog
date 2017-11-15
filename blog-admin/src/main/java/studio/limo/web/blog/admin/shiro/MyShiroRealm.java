package studio.limo.web.blog.admin.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import studio.limo.web.blog.core.bean.AdminUser;
import studio.limo.web.blog.core.dao.AdminUserDao;

public class MyShiroRealm extends AuthorizingRealm {

    @Autowired
    AdminUserDao adminUserDao;
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        //        AdminUser adminUser  = (AdminUser) principals.getPrimaryPrincipal();
//        for(SysRole role:userInfo.getRoleList()){
//            authorizationInfo.addRole(role.getRole());
//            for(SysPermission p:role.getPermissions()){
//                authorizationInfo.addStringPermission(p.getPermission());
//            }
//        }
        return new SimpleAuthorizationInfo();
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String account = (String)token.getPrincipal();
        AdminUser adminUser = adminUserDao.findByAccount(account);

        if (adminUser == null){
            return null;
        }
        return new SimpleAuthenticationInfo(adminUser, adminUser.getPassword(), getName());
    }
}
