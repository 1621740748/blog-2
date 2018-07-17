package gr.blog.service;

import gr.blog.model.BlogCategory;

import java.util.List;
import java.util.Map;

public interface CategoryService {

    List<BlogCategory> getAllCategory();

    List<BlogCategory> findCategoryList(int pageNum, int pageSize, Map<String, Object> filter);

    BlogCategory get(Integer id);

    /**
     * 查找所有顶级分类
     * @return
     */
    List<BlogCategory> getTopCategorys();

    void addRecord(BlogCategory category);

    void updateRecord(BlogCategory category);

    String deleteBatch(int[] ids);
}
