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

    @Override
    public BlogCategory get(Integer id) {
        return categoryMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<BlogCategory> getTopCategorys() {
        return categoryMapper.getTopCategorys();
    }

    @Override
    public void addRecord(BlogCategory category) {
        categoryMapper.insertSelective(category);
    }

    @Override
    public void updateRecord(BlogCategory category) {
        categoryMapper.updateByPrimaryKeySelective(category);
    }

    @Override
    public String deleteBatch(int[] ids) {
        int deleteRows = categoryMapper.deleteBatch(ids);
        if (deleteRows != 0){
            return "1";
        }else{
            return "0";
        }
    }

}
