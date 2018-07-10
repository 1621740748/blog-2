package gr.blog.service.impl;

import gr.blog.mapper.BlogCategoryMapper;
import gr.blog.model.BlogCategory;
import gr.blog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("categoryService")
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private BlogCategoryMapper categoryMapper;


    @Override
    public List<BlogCategory> getAllCategory() {
        return categoryMapper.getAllCategory();
    }
}
