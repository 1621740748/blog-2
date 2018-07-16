package gr.blog.service;

import gr.blog.model.Article;
import gr.blog.model.BlogCategory;

import java.util.List;
import java.util.Map;

public interface CategoryService {

    List<BlogCategory> getAllCategory();

    List<BlogCategory> findCategoryList(int pageNum, int pageSize, Map<String, Object> filter);
}
