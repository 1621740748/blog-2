package gr.blog.service.impl;

import gr.blog.mapper.ArticleMapper;
import gr.blog.model.Article;
import gr.blog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "articleService")
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public List<Article> findArticleList() {

        return articleMapper.getAllArticle();
    }

    @Override
    public Article get(int id) {
        return articleMapper.selectByPrimaryKey(id);
    }

    @Override
    public void addCkickCount(int id) {
        articleMapper.addCkickCount(id);
    }
}
