package gr.blog.controller;

import gr.blog.model.Article;
import gr.blog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @RequestMapping("/")
    public String list(ModelMap model){
        List<Article> articleList = articleService.findArticleList(1, 4);
        model.addAttribute("articleList", articleList);
        return "index";
    }
}
