package gr.blog.mapper;

import gr.blog.model.BlogTag;

import java.util.List;

public interface BlogTagMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BlogTag record);

    int insertSelective(BlogTag record);

    BlogTag selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BlogTag record);

    int updateByPrimaryKey(BlogTag record);

    void insertByBatch(List<BlogTag> list);
}