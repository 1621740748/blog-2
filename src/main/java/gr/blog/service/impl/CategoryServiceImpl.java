package gr.blog.service.impl;

import com.github.pagehelper.PageHelper;
import gr.blog.mapper.BlogCategoryMapper;
import gr.blog.model.BlogCategory;
import gr.blog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("categoryService")
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private BlogCategoryMapper categoryMapper;


    @Override
    public List<BlogCategory> getAllCategory() {
        return categoryMapper.getAllCategory();
    }

    @Override
    public List<BlogCategory> findCategoryList(int pageNum, int pageSize, Map<String, Object> filter) {
        PageHelper.startPage(pageNum, pageSize);
        List<BlogCategory> categoryList = categoryMapper.getCategoryList(filter);
        return categoryList;
    }

}
