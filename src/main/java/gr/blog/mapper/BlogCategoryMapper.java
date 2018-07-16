package gr.blog.mapper;

import gr.blog.model.BlogCategory;

import java.util.List;
import java.util.Map;

public interface BlogCategoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BlogCategory record);

    int insertSelective(BlogCategory record);

    BlogCategory selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BlogCategory record);

    int updateByPrimaryKey(BlogCategory record);

    List<BlogCategory> getAllCategory();

    List<BlogCategory> getCategoryList(Map<String,Object> filter);

    int getCount(Map<String,Object> filter);
}