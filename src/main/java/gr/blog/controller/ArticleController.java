package gr.blog.controller;

import com.github.pagehelper.PageInfo;
import gr.blog.exception.FontException;
import gr.blog.model.Article;
import gr.blog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public String list(ModelMap model) throws FontException {
        try {
            //PageHelper.startPage(1, 4);//分页默认是每页4个元素
            Map<String, Object> filter = new HashMap<>();
            filter.put("orderColumn", "click_count");
            filter.put("orderDir", "desc");
            List<Article> articleList = articleService.findArticleList(1, 4, filter);
            PageInfo<Article> info = new PageInfo<>(articleList);
            model.addAttribute("page", info);
            model.addAttribute("articleList", articleList);
        } catch (Exception e) {
            e.printStackTrace();
            throw new FontException(e.getMessage());
        }
        return "index";
    }

    /**
     * 分页
     * @param model
     * @param pageNum
     * @return
     */
    @RequestMapping(value = {"/page/{pageNum}"}, method = RequestMethod.GET)
    public String page(ModelMap model, @PathVariable(name = "pageNum") int pageNum) throws FontException {
        try {
            //PageHelper.startPage(pageNum, 4);//分页默认是每页4个元素
            Map<String, Object> filter = new HashMap<>();
            filter.put("orderColumn", "click_count");
            filter.put("orderDir", "desc");
            List<Article> articleList = articleService.findArticleList(pageNum, 4, filter);
            PageInfo<Article> info = new PageInfo(articleList);
            model.addAttribute("page", info);
            model.addAttribute("articleList", articleList);
            //int a = 1/0;
        } catch (Exception e) {
            //System.out.println("test");
            throw new FontException(e.getMessage());
        }
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
