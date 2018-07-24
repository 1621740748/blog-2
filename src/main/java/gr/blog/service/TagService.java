package gr.blog.service;

import gr.blog.model.BlogTag;

import java.util.List;
import java.util.Map;

public interface TagService {

    /**
     * 新增博客文章时；批量添加标签
     * @param id
     * @param tags
     */
    void addRecordsByIdAndTagname(Integer ArticleId, String[] tags);

    /**
     * 新增博客文章时；批量修改标签
     * @param id
     * @param tags
     */
    void updateRecordByIdAndTagname(Integer ArticleId, String[] tags);

    List<BlogTag> getTagsByArticleId(Integer ArticleId);

    List<Map<String, Object>> getCloud();
}
