package gr.blog.controller;

import com.github.pagehelper.PageInfo;
import gr.blog.exception.FontException;
import gr.blog.model.Article;
import gr.blog.model.BlogCategory;
import gr.blog.model.BlogTag;
import gr.blog.service.ArticleService;
import gr.blog.service.CategoryService;
import gr.blog.service.TagService;
import gr.blog.utils.IpAddressUtils;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class FrontstageArticleController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private TagService tagService;



    @ApiOperation("前台-首页列表展示")
    @RequestMapping(value = {"/","/index","/index/{pageNum}"}, method = RequestMethod.GET)
    public String list(ModelMap model,@PathVariable(value = "pageNum", required = false)Integer pageNum) throws FontException {
        try {
            Map<String, Object> filter = new HashMap<>();
            filter.put("orderColumn", "id");
            filter.put("orderDir", "desc");
            List<Article> articleList;
            if(null == pageNum) {
                articleList = articleService.findArticleList(1, 20, filter);
            }else{
                articleList = articleService.findArticleList(pageNum, 20, filter);
            }
            PageInfo<Article> info = new PageInfo<>(articleList);
            model.addAttribute("page", info);
            model.addAttribute("articleList", articleList);
            //获取页面需要的首部信息
            List<Article> header = articleService.getContentHeader();
            model.addAttribute("header", header);
            commonPageContent(model);
        } catch (Exception e) {
            e.printStackTrace();
            throw new FontException(e.getMessage());
        }
        return "frontstage/index";
    }

    @ApiOperation("前台-按照前台二级分类展示")
    @RequestMapping(value = {"/category/{categoryId}/{pageNum}", "/category/{categoryId}/"}, method = RequestMethod.GET)
    public String page(ModelMap model, @PathVariable(name = "categoryId") int categoryId, @PathVariable(name = "pageNum", required = false) Integer pageNum) throws FontException {
        try {
//            System.out.println("hello error!!");
            Map<String, Object> filter = new HashMap<>();
            filter.put("orderColumn", "id");
            filter.put("orderDir", "desc");
            filter.put("categoryId", categoryId);
            List<Article> articleList;
            if (pageNum == null) {
                articleList = articleService.findArticleList(0, 20, filter);
            }else{
                articleList = articleService.findArticleList(pageNum, 20, filter);
            }
            PageInfo<Article> info = new PageInfo(articleList);
            BlogCategory category = categoryService.get(categoryId);
            model.addAttribute("page", info);
            model.addAttribute("category", category);
            model.addAttribute("articleList", articleList);
            commonPageContent(model);
            //int a = 1/0;
        } catch (Exception e) {
//            System.out.println("test");
            throw new FontException(e.getMessage());
        }
        return "frontstage/list";
    }

    @ApiOperation("根据文章id查询文章详细信息")
    @RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
    public String detail(@PathVariable("id") int id, ModelMap model, HttpServletRequest request){
        Article article = articleService.get(id);
        articleService.addCkickCount(id, request.getRemoteAddr());//增加一个点击量

        int count = articleService.getLikeCount(id);
        model.addAttribute("likeNum", count);
        model.addAttribute("article", article);
        Article preArticle = articleService.getPre(id, article.getCategoryId());
        model.addAttribute("preArticle", preArticle);
        Article nextArticle = articleService.getNext(id, article.getCategoryId());
        model.addAttribute("nextArticle", nextArticle);
        List<BlogTag> listTag = tagService.getTagsByArticleId(id);
        model.addAttribute("tags", listTag);


        commonPageContent(model);
        return "frontstage/info";
    }

    @ApiOperation("根据文章id修改喜欢数 并将修改后的结果返回给前台")
    @RequestMapping(value = "/updateLike/{id}", method = RequestMethod.GET)
    @ResponseBody
    public String updateLike(@PathVariable("id") Integer id, HttpServletRequest request){
        boolean like = articleService.isLiked(id, request.getRemoteAddr());//like为false表示没有喜欢过，为true表示喜欢过
        if (like){
            return "-1";
        }else{
            articleService.addLike(id, request.getRemoteAddr());
            return Integer.toString(articleService.getLikeCount(id));
        }
    }

    /**
     * 公共页面信息查询 ，公共方法，需要引入公共界面的方法需要引入它
     * -- 每页首部  推荐的文章 按id倒序 取其中喜欢数最多的五篇文章
     * -- 特別推荐  就是推荐文章中点击量最大的三个
     * -- 推荐文章  就是正常推荐文章按id倒叙前五
     * -- 点击排行  按点击量排序
     * @param model
     */
    private void commonPageContent(ModelMap model){

        //获取页面需要的特别推荐信息
        List<Article> special = articleService.getSpecialRecommend();
        model.addAttribute("special", special);
        //获取推荐文章
        List<Article> recommend  = articleService.getRecommendArticle();
        model.addAttribute("recommend", recommend);
        //按点击排行获取信息
        List<Article> click = articleService.getArticleByClick();
        model.addAttribute("click", click);
        //标签云
        List<Map<String, Object>> tagsCloud = tagService.getCloud();
        model.addAttribute("tagsCloud", tagsCloud);
    }

    @ApiOperation("关于我")
    @RequestMapping(value = "/about", method = RequestMethod.GET)
    public String about(){
        return "frontstage/about";
    }

    @ApiOperation("时间轴")
    @RequestMapping(value = "/time", method = RequestMethod.GET)
    public String time(){
        return "frontstage/time";
    }

    @ApiOperation("留言")
    @RequestMapping(value = "/comment", method = RequestMethod.GET)
    public String comment(){
        return "frontstage/comment";
    }
}
