package gr.blog.service;

import gr.blog.model.Article;

import java.util.List;
import java.util.Map;

public interface ArticleService {

    List<Article> findArticleList(int pageNum, int pageSize, Map<String, Object> filter);

    Article get(int id);

    /**
     * 增加一次点击量
     * @param id
     */
    void addCkickCount(int id);

    /**
     * 新增一条记录
     * @param article
     */
    int addRecord(Article article);

    /**
     * 更新一条记录
     * @param article
     */
    int updateRecord(Article article);

    /**
     * 查询文章总数
     * @return
     * @param filter
     */
    int getCount(Map<String, Object> filter);
}

