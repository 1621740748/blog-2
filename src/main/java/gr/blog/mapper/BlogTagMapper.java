package gr.blog.mapper;

import gr.blog.model.BlogTag;

import java.util.List;
import java.util.Map;

public interface BlogTagMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BlogTag record);

    int insertSelective(BlogTag record);

    BlogTag selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BlogTag record);

    int updateByPrimaryKey(BlogTag record);

    void insertByBatch(List<BlogTag> list);

    List<BlogTag> selectByArticleId(Integer ArticleId);

    void deleteByArticleKey(Integer articleId);

    List<Map<String, Object>> getCloud();
}