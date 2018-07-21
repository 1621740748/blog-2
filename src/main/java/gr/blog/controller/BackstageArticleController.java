package gr.blog.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import gr.blog.model.Article;
import gr.blog.model.BlogCategory;
import gr.blog.model.BlogTag;
import gr.blog.service.ArticleService;
import gr.blog.service.CategoryService;
import gr.blog.service.TagService;
import gr.blog.utils.ImageUploadUtil;
import gr.blog.utils.StringUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 后台文章管理模块
 */
@RequestMapping("/back")
@Controller
public class BackstageArticleController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private TagService tagService;

    @ApiOperation("后台首页")
    @RequestMapping(value = {"", "/index"}, method = RequestMethod.GET)
    public String index(){
        return "backstage/index";
    }

    @ApiOperation(value = "后台-文章列表展示", notes = "页面点击导航栏进入文章管理，文章以表格形式展示。")
    @RequestMapping(value = "/article",method = RequestMethod.GET)
    public String articlePage(){
        return "backstage/articleList";
    }

    @ApiOperation(value = "后台-文章列表分页", notes = "页面点击导航栏每页按多少展示的分页功能")
    //@ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long")//这段是对参数进行解释
    @RequestMapping(value = {"/articlePage"}, method = {RequestMethod.POST})
    @ResponseBody
    public String articleList(String aoData){
        //这块去数据库中取出一定量的数据传递给前台用于显示
        String sEcho = null;
        int iDisplayStart = 0;//起始索引
        int iDisplayLength = 10;//每页显示的行数
        long count;

        String orderColumn ="";//默认排序列
        String orderDir = "asc";//默认排序方式为升序
        String sSearch = "";//默认搜索内容
        List<Article> articleList;

        JSONArray jsonArray = JSONArray.parseArray(aoData);
        //System.out.println(jsonArray.toString());
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObject = (JSONObject)jsonArray.get(i);
            if (jsonObject.get("name").equals("sEcho")) {
                sEcho = jsonObject.get("value").toString();
            }else if (jsonObject.get("name").equals("iDisplayStart")){
                iDisplayStart = Integer.parseInt(jsonObject.get("value").toString());
            }else if (jsonObject.get("name").equals("iDisplayLength")){
                iDisplayLength = Integer.parseInt(jsonObject.get("value").toString());
            }else if (jsonObject.get("name").equals("iSortCol_0")){
                orderColumn = jsonObject.get("value").toString();
            }else if (jsonObject.get("name").equals("sSortDir_0")){
                orderDir = jsonObject.get("value").toString();
            }else if (jsonObject.get("name").equals("sSearch")){
                sSearch = jsonObject.get("value").toString();
            }
        }
        Map<String, Object> filter = new HashMap<>();
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObject = (JSONObject)jsonArray.get(i);
            if (jsonObject.get("name").equals("mDataProp_" + orderColumn)) {
                orderColumn = jsonObject.get("value").toString();
            }
            if (jsonObject.get("name").toString().contains("mDataProp_")){
                String tmp = jsonObject.get("value").toString();
                filter.put(tmp, tmp);
            }
        }
        filter.put("orderColumn", StringUtil.camel2Underline(orderColumn));
        filter.put("orderDir", orderDir);
        filter.put("sSearch", sSearch);
        articleList = articleService.findArticleList((iDisplayStart/iDisplayLength + 1), iDisplayLength, filter);
        count = new PageInfo<>(articleList).getTotal();

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("sEcho", sEcho);//前端传递的，只需获取然后原数返回即可
        jsonObject.put("iTotalDisplayRecords", count);//当前数据表中总的记录数
        jsonObject.put("iTotalRecords", articleList.size());//是当前页面要展示的记录数
        jsonObject.put("aaData", articleList);//返回列表的数据。
        return jsonObject.toJSONString();
    }

    @ApiOperation(value = "进入文章（新增/修改）页面")
    @RequestMapping(value = "/article/input",method = RequestMethod.GET)
    public String input(ModelMap model, Integer id){
        if (id != null){//不为null说明 为修改操作
            Article article = articleService.get(id);
            model.addAttribute("article", article);
            //获取博客相关标签，并把标签名以逗号分隔开，返回给页面
            List<BlogTag> listTag = tagService.getTagsByArticleId(id);
            StringBuffer buffer = new StringBuffer();
            for (BlogTag tag: listTag) {
                buffer.append(tag.getTagName() + ",");
            }
            model.addAttribute("tags", buffer);
        }
        List<BlogCategory> listCategory = categoryService.getAllCategory();
        model.addAttribute("listCategory", listCategory);
        return "backstage/articleInput";
    }

    @ApiOperation("后台管理-文章新增/修改")
    @RequestMapping(value = {"/article/add", "/article/update"}, method = RequestMethod.POST)
    public String articleAdd(@ModelAttribute Article article, String[] tags){
        //System.out.println(tags.length);
        if(article.getId() == null){//新增
            articleService.addRecord(article);
            tagService.addRecordsByIdAndTagname(article.getId(), tags);
        }else{//更新记录
            articleService.updateRecord(article);
            tagService.updateRecordByIdAndTagname(article.getId(),tags);
        }
        return "redirect:/back/article";
    }

    @ApiOperation("后台管理-文章删除")
    @RequestMapping(value = {"/article/delete"}, method = RequestMethod.POST)
    @ResponseBody
    public String articleDelete(int [] ids){
        String result = articleService.deleteBatch(ids);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("result",result);
        return jsonObject.toJSONString();
    }

    @ApiOperation("ckeditor编辑器文件上传处理,后期需要重新编写")
    @RequestMapping(value = "/image/fileUpload",method = RequestMethod.POST)
    @ResponseBody
    public String fileUpload(HttpServletRequest request, HttpServletResponse response){
        String path = "upload";
        try {
            ImageUploadUtil.ckeditor(request, response, path);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
