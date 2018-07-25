package gr.sys.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * shiro 用户认证 授权
 */
public class MyShiroRealm extends AuthorizingRealm {

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        principalCollection.getPrimaryPrincipal();
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        authenticationToken.getPrincipal();
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo("jiangzy", "jiangzy", getName());
        return simpleAuthenticationInfo;
    }
}
