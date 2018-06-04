package gr.blog.controller;

import gr.blog.model.Article;
import gr.blog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @RequestMapping(value = {"/","/index"})
    public String list(ModelMap model){
        List<Article> articleList = articleService.findArticleList(1, 4);
        model.addAttribute("articleList", articleList);
        return "index";
    }

    /**
     * 根据文章id查询文章详细信息
     */
    @RequestMapping("/detail/{id}")
    public String detail(@PathVariable("id") int id, ModelMap model){
        Article article = articleService.get(id);
        model.addAttribute("article", article);
        return "detail";
    }
}
