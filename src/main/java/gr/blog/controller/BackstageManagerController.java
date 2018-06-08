package gr.blog.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 后台管理模块
 */
@RequestMapping("/back")
@Controller
public class BackstageManagerController {

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index(){

        return "backstage/index";
    }


    /**
     * 后台管理-文章管理-文章列表展示
     * @return
     */
    @ApiOperation(value = "后台-文章管理首页", notes = "页面点击导航栏进入文章管理，文章以表格形式展示。")
    //@ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long")//这段是对参数进行解释
    @RequestMapping(value = "/article", method = RequestMethod.GET)
    public String articleList(){
        //这块去数据库中取出一定量的数据传递给前台用于显示
        return "backstage/articleList";
    }

    @RequestMapping("/article/add")
    public String input(){
        return "backstage/articleInput";
    }
}
