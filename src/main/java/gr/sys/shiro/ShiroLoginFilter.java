package gr.sys.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * 本来打算是自定义一个过滤器，登录成功后返回上一次URL，
 * 事实证明完全不需要，
 * 但这个自定义的过滤器依然不删除，还有配置类中配置过程也不删除，
 * 毕竟可能后期可能需要在过滤的时候添加一些什么功能
 */
public class ShiroLoginFilter extends FormAuthenticationFilter {
}
