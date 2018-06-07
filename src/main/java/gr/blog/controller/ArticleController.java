package gr.blog.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import gr.blog.model.Article;
import gr.blog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    /**
     * 首页列表展示
     * @param model
     * @return
     */
    @RequestMapping(value = {"/","/index"}, method = RequestMethod.GET)
    public String list(ModelMap model){
        PageHelper.startPage(1, 4);//分页默认是每页4个元素
        List<Article> articleList = articleService.findArticleList();
        PageInfo<Article> info = new PageInfo<Article>(articleList);
        model.addAttribute("page", info);
        model.addAttribute("articleList", articleList);
        return "index";
    }

    /**
     * 分页
     * @param model
     * @param pageNum
     * @return
     */
    @RequestMapping(value = {"/page/{pageNum}"}, method = RequestMethod.GET)
    public String page(ModelMap model, @PathVariable("pageNum") int pageNum){
        PageHelper.startPage(pageNum, 4);//分页默认是每页4个元素
        List<Article> articleList = articleService.findArticleList();
        PageInfo<Article> info = new PageInfo<Article>(articleList);
        model.addAttribute("page", info);
        model.addAttribute("articleList", articleList);
        return "index";
    }

    /**
     * 根据文章id查询文章详细信息
     */
    @RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
    public String detail(@PathVariable("id") int id, ModelMap model){
        Article article = articleService.get(id);
        articleService.addCkickCount(id);//增加一个点击量
        model.addAttribute("article", article);
        return "detail";
    }
}
