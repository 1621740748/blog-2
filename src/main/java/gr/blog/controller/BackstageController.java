package gr.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 后台管理模块
 */
@Controller
public class BackstageController {

    @RequestMapping("/back")
    public String shouw(){

        return "backstage/index";
    }

}
