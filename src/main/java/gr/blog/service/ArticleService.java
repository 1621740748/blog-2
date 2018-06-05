package gr.blog.service;

import gr.blog.model.Article;

import java.util.List;

public interface ArticleService {

    List<Article> findArticleList();

    Article get(int id);
}
