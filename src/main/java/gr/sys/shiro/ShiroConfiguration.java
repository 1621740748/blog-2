package gr.sys.shiro;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.DelegatingFilterProxy;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 描述：Shiro权限配置
 */
@Configuration
public class ShiroConfiguration {

    // 配置核心安全事务管理器
    @Bean(name = "securityManager")
    public SecurityManager securityManager(@Qualifier("shiroRealm") ShiroRealm shiroRealm) {
        System.err.println("--------------shiro已经加载----------------");
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        manager.setRealm(shiroRealm);
//        manager.setSessionManager(sessionManager);
//        manager.setCacheManager();
        return manager;
    }

    @Bean
    public ShiroLoginFilter shiroLoginFilter(){
        ShiroLoginFilter shiroLoginFilter = new ShiroLoginFilter();
        //shiroLoginFilter.setPasswordParam("password");
        return shiroLoginFilter;
    }

    @Bean
    public FilterRegistrationBean shiroLoginFilteRegistration(ShiroLoginFilter filter) {
        FilterRegistrationBean registration = new FilterRegistrationBean(filter);
        registration.setEnabled(false);
        return registration;
    }

    /**
     * ShiroFilterFactoryBean 处理拦截资源文件问题。
     * 注意：单独一个ShiroFilterFactoryBean配置是或报错的，以为在
     * 初始化ShiroFilterFactoryBean的时候需要注入：SecurityManager
     *
     Filter Chain定义说明
     1、一个URL可以配置多个Filter，使用逗号分隔
     2、当设置多个过滤器时，全部验证通过，才视为通过
     3、部分过滤器可指定参数，如perms，roles
     *
     */
    @Bean
    public ShiroFilterFactoryBean shirFilter(@Qualifier("securityManager") SecurityManager manager){

        System.out.println("  。。。。shirFilter。。。。。。 ");

        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        bean.setSecurityManager(manager);
        // 配置登录的url和登录成功的url

        bean.setLoginUrl("/login");
        bean.setSuccessUrl("123456successPage");

        // 定义过滤器
        Map<String, Filter> filterMap = bean.getFilters();
        filterMap.put("authc", shiroLoginFilter());
        //filterMap.put("perms", new ShiroPermissionsFilter());

        // 配置访问权限
        LinkedHashMap<String, String> filterChainDefinitionMap = new LinkedHashMap<>();

        // 配置不同的URL采用的验证方式
        // anon表示可以匿名访问
        filterChainDefinitionMap.put("/login", "authc");
        filterChainDefinitionMap.put("/back**", "authc");// 表示需要认证才可以访问
        filterChainDefinitionMap.put("/back/**", "authc");// 表示需要认证才可以访问
//        filterChainDefinitionMap.put("/*.*", "authc,perms");
        bean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return bean;
    }

    // 配置自定义的权限登录器
    @Bean(name = "shiroRealm")
    public ShiroRealm shiroRealm() {
        ShiroRealm shiroRealm = new ShiroRealm();
        //userRealm.setCredentialsMatcher(matcher);
        return shiroRealm;
    }

    // 配置自定义的密码匹配比较器
    //@Bean(name = "credentialsMatcher")
    //public CredentialsMatcher credentialsMatcher() {
        //return new CredentialsMatcher();
    //

}