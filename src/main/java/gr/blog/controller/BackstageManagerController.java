package gr.blog.controller;

import gr.blog.model.Article;
import gr.blog.service.ArticleService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * 后台管理模块
 */
@RequestMapping("/back")
@Controller
public class BackstageManagerController {

    @Autowired
    private ArticleService articleService;

    @ApiOperation("后台首页")
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index(){

        return "backstage/index";
    }

    @ApiOperation(value = "后台-文章列表展示", notes = "页面点击导航栏进入文章管理，文章以表格形式展示。")
    //@ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long")//这段是对参数进行解释
    @RequestMapping(value = "/article", method = RequestMethod.GET)
    public String articleList(){
        //这块去数据库中取出一定量的数据传递给前台用于显示
        return "backstage/articleList";
    }

    @ApiOperation(value = "进入文章新增/修改页面")
    @RequestMapping(value = "/article/input",method = RequestMethod.GET)
    public String input(){ return "backstage/articleInput"; }

    @ApiOperation("后台管理-文章新增/修改")
    @RequestMapping(value = {"/article/add"}, method = RequestMethod.POST)
    public String articleAdd(@ModelAttribute Article article){
        //System.out.println(article.toString());
        articleService.addRecord(article);
        return "redirect:/back/article";
    }
}
