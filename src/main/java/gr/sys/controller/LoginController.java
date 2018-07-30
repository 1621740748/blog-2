package gr.sys.controller;

import gr.sys.model.SysUser;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {

    @ApiOperation("进入登录界面")
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String login(){
        return "backstage/login";
    }

    @ApiOperation("实际登录过程，只需要处理错误就好，登录交给shiro完成")
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login(ModelMap modelMap, HttpServletRequest request){
        String errorClassName = (String)request.getAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME);

        if (errorClassName == null){
            //登录过，不用重复登录
            return "redirect:/back";
        } else if(UnknownAccountException.class.getName().equals(errorClassName)) {
            modelMap.put("error", "用户名/密码错误");
        } else if(IncorrectCredentialsException.class.getName().equals(errorClassName)) {
            modelMap.put("error", "用户名/密码错误");
        } else if(errorClassName != null) {
            modelMap.put("error", "未知错误：" + errorClassName);
        }
        return "backstage/login";
    }
}
