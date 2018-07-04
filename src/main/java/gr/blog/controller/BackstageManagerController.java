package gr.blog.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import gr.blog.model.Article;
import gr.blog.service.ArticleService;
import gr.blog.utils.ImageUploadUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

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

    @RequestMapping(value = "/article",method = RequestMethod.GET)
    public String articlePage(){
        return "backstage/articleList";
    }

    @ApiOperation(value = "后台-文章列表展示", notes = "页面点击导航栏进入文章管理，文章以表格形式展示。")
    //@ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long")//这段是对参数进行解释
    @RequestMapping(value = {"/articlePage"}, method = {RequestMethod.POST})
    @ResponseBody
    public String articleList(String aoData){
        //这块去数据库中取出一定量的数据传递给前台用于显示
        String sEcho = null;
        int iDisplayStart = 0;//起始索引
        int iDisplayLength = 10;//每页显示的行数
        int count;
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
            }
        }
        articleList = articleService.findArticleList(iDisplayStart, iDisplayLength);

        count = articleService.getCount();

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("sEcho", sEcho);//前端传递的，只需获取然后原数返回即可
        jsonObject.put("iTotalDisplayRecords", count);//当前数据表中总的记录数
        jsonObject.put("iTotalRecords", articleList.size());//是当前页面要展示的记录数
        jsonObject.put("aaData", articleList);//返回列表的数据。
        return jsonObject.toJSONString();
    }

    @ApiOperation(value = "进入文章新增/修改页面")
    @RequestMapping(value = "/article/input",method = RequestMethod.GET)
    public String input(){ return "backstage/articleInput"; }

    @ApiOperation("后台管理-文章新增")
    @RequestMapping(value = {"/article/add"}, method = RequestMethod.POST)
    public String articleAdd(@ModelAttribute Article article){
        //System.out.println(article.toString());
        int changedCount = articleService.addRecord(article);
        //System.out.println("新增" + changedCount + "条记录");
        return "redirect:/back/article";
    }

    @RequestMapping(value = {"/article/update"}, method = RequestMethod.GET)
    public String updateArticle(){
        return "";
    }

    @ApiOperation("ckeditor编辑器文件上传处理")
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
