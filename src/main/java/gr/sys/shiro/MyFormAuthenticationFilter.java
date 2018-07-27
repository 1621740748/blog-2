package gr.sys.shiro;

import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class MyFormAuthenticationFilter extends FormAuthenticationFilter {

    @Override
    protected void issueSuccessRedirect(ServletRequest request, ServletResponse response) throws Exception {
        System.out.println("hello form filter");
        System.out.println(getSuccessUrl()+"123");
    }
}
