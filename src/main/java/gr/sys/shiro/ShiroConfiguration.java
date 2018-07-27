package gr.sys.shiro;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.DelegatingFilterProxy;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class ShiroConfiguration {

    @Bean("myShiroRealm")
    public MyShiroRealm myShiroRealm(){
        return new MyShiroRealm();
    }

    //权限管理
    @Bean("securityManager")
    public SecurityManager securityManager(@Qualifier("myShiroRealm") MyShiroRealm myShiroRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(myShiroRealm);
        return securityManager;
    }

    @Bean("myFormAuthenticationFilter")
    public MyFormAuthenticationFilter myFormAuthenticationFilter(){
        return new MyFormAuthenticationFilter();
    }

    @Bean
    public FilterRegistrationBean delegatingFilterProxy(){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        DelegatingFilterProxy proxy = new DelegatingFilterProxy();
        proxy.setTargetFilterLifecycle(true);
        proxy.setTargetBeanName("shiroFilter");
        filterRegistrationBean.setFilter(proxy);
        return filterRegistrationBean;
    }

    //filter工厂，设置对应过滤条件和跳转条件
    @Bean("shiroFilter")
    public ShiroFilterFactoryBean shiroFilterFactoryBean(@Qualifier("securityManager") SecurityManager securityManager, @Qualifier("myFormAuthenticationFilter") MyFormAuthenticationFilter myFormAuthenticationFilter){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

        Map<String, Filter> filterMap = shiroFilterFactoryBean.getFilters();
        filterMap.put("authc", myFormAuthenticationFilter);
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        Map<String, String> map = new HashMap<>();
        //登出
        map.put("/logout", "logout");
        //静态资源访问
        //map.put("/**","anon");
        //对所有用户认证
        map.put("/back/**", "authc");
        //登录
        shiroFilterFactoryBean.setLoginUrl("/login");
        //登录成功跳转界面
        shiroFilterFactoryBean.setSuccessUrl("/back/index");

        //没有权限跳转界面
        shiroFilterFactoryBean.setUnauthorizedUrl("");

        //过滤条件
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);

        return shiroFilterFactoryBean;
    }
}
