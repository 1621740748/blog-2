package gr.blog.service.impl;

import com.github.pagehelper.PageHelper;
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
    public List<Article> findArticleList(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return articleMapper.getAllArticle();
    }

    @Override
    public Article get(int id) {
        return articleMapper.selectByPrimaryKey(id);
    }
}
