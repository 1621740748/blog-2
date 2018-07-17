package gr.blog.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import gr.blog.model.Article;
import gr.blog.model.BlogCategory;
import gr.blog.service.CategoryService;
import gr.blog.utils.StringUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 后台文章分类管理模块
 */
@RequestMapping("/back")
@Controller
public class BackstageCategoryController {

    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value = "/category",method = RequestMethod.GET)
    public String categoryPage(){
        return "backstage/categoryList";
    }

    @ApiOperation(value = "后台-文章分类列表分页", notes = "页面点击导航栏每页按多少展示的分页功能")
    @RequestMapping(value = {"/categoryPage"}, method = {RequestMethod.POST})
    @ResponseBody
    public String categoryList(String aoData){
        //这块去数据库中取出一定量的数据传递给前台用于显示
        String sEcho = null;
        int iDisplayStart = 0;//起始索引
        int iDisplayLength = 10;//每页显示的行数
        long count;

        String orderColumn ="";//默认排序列
        String orderDir = "asc";//默认排序方式为升序
        String sSearch = "";//默认搜索内容
        List<BlogCategory> categoryList;

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
        categoryList = categoryService.findCategoryList((iDisplayStart/iDisplayLength + 1), iDisplayLength, filter);
        count = new PageInfo<>(categoryList).getTotal();

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("sEcho", sEcho);//前端传递的，只需获取然后原数返回即可
        jsonObject.put("iTotalDisplayRecords", count);//当前数据表中总的记录数
        jsonObject.put("iTotalRecords", categoryList.size());//是当前页面要展示的记录数
        jsonObject.put("aaData", categoryList);//返回列表的数据。
        return jsonObject.toJSONString();
    }

    @ApiOperation(value = "进入分类（新增/修改）页面")
    @RequestMapping(value = "/category/input",method = RequestMethod.GET)
    public String input(ModelMap model, Integer id){
        if (id != null){//不为null说明 为修改操作
            BlogCategory category = categoryService.get(id);
            model.addAttribute("category", category);
        }
        List<BlogCategory> categoryList = categoryService.getTopCategorys();
        model.addAttribute("topCategory",categoryList);
        return "backstage/categoryInput";
    }

    @ApiOperation("后台管理-分类新增/修改")
    @RequestMapping(value = {"/category/add", "/category/update"}, method = RequestMethod.POST)
    public String categoryAdd(@ModelAttribute BlogCategory category){
        if(category.getId() == null){//新增
            categoryService.addRecord(category);
        }else{//更新记录
            categoryService.updateRecord(category);
        }
        return "redirect:/back/category";
    }

    @ApiOperation("后台管理-分类删除")
    @RequestMapping(value = {"/category/delete"}, method = RequestMethod.POST)
    @ResponseBody
    public String categoryDelete(int [] ids){
        String result = categoryService.deleteBatch(ids);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("result",result);
        return jsonObject.toJSONString();
    }

}
