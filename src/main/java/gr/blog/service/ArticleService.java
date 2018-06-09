package gr.blog.service;

import gr.blog.model.Article;

import java.util.List;

public interface ArticleService {

    List<Article> findArticleList();

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
}
