package gr.sys.shiro;

import gr.sys.model.SysMenu;
import gr.sys.model.SysRole;
import gr.sys.model.SysUser;
import gr.sys.service.LoginService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * shiro 用户认证 授权
 */
public class ShiroRealm extends AuthorizingRealm {

    @Autowired
    private LoginService loginService;

    /**
     * 角色分配和权限分配
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String userName = (String)principalCollection.getPrimaryPrincipal();
        SysUser user = loginService.getUserByName(userName);
        //需要先分配角色
        List<SysRole> roleList = user.getRoleList();
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        for (SysRole role: roleList) {
            //添加角色
            simpleAuthorizationInfo.addRole(role.getRoleName());
            for (SysMenu menu: role.getMenuList()){
                //添加权限
                simpleAuthorizationInfo.addStringPermission(menu.getPerms());
            }
        }
        return simpleAuthorizationInfo;
    }

    /**
     * 身份认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    public AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        Object principal = authenticationToken.getPrincipal();
        if (principal == null) {
            return null;//这里返回null，shiro控制器根据返回的null判断，用户名有误，产生UnknownAccountException用户名出错异常，一般不会出现这个错误，因为前台和后台校验不允许出现空用户名
        }
        String userName = principal.toString();
        SysUser user = loginService.getUserByName(userName);
        if (user == null){
            return null;//这里返回null，表示该用户名不存在，和上一个null都是触发shiro产生UnknownAccountException异常，也就是账户异常
        }
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(user.getUserName(), user.getPassword(), getName());
        return simpleAuthenticationInfo;
    }
}
