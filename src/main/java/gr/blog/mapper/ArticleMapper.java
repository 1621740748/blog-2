package gr.blog.mapper;

import gr.blog.model.Article;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ArticleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Article record);

    int insertSelective(Article record);

    Article selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Article record);

    int updateByPrimaryKeyWithBLOBs(Article record);

    int updateByPrimaryKey(Article record);

    List<Article> getAllArticle(Map<String, Object> filter);

    void addClickCount(int id);

    int deleteBatch(int[] ids);

    Article getPre(@Param("id") int id, @Param("categoryId") Integer categoryId);

    Article getNext(@Param("id") int id, @Param("categoryId") Integer categoryId);
}