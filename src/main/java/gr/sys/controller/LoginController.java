package gr.sys.controller;

import gr.sys.model.SysUser;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {

    @ApiOperation("进入登录界面")
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String login(){
        return "backstage/login";
    }

    @ApiOperation("正式登录过程")
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login(SysUser user, ModelMap modelMap){
        System.out.println(user);
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUserName(), user.getPassword());
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
        }catch (UnknownAccountException e){
            modelMap.put("msg","账户名不正确");//前台输入的用户名为空或者用户名在数据库中不存在
        return "error/shiro";
    }catch (IncorrectCredentialsException e){
        modelMap.put("msg","密码不正确");
        return "error/shiro";
    }catch (AuthenticationException e){
        e.printStackTrace();
    }
        return "backstage/login";
    }
}
