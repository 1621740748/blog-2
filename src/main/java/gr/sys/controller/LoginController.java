package gr.sys.controller;

import gr.sys.model.SysUser;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {

    @ApiOperation("进入登录界面")
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String loginPage(){
        return "backstage/login";
    }

    @ApiOperation("正式登录过程")
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login(SysUser user){
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUserName(), user.getPassword());
        Subject subject = SecurityUtils.getSubject();
        subject.login(token);
        return "backstage/login";
    }
}
