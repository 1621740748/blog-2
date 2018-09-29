package frameworkStudy.JdbcTemplates;

import gr.blog.model.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JdbcTempleteTest {

    @Autowired
    private JdbcTemplate jt;

    public Article getArticle(int id){
        List<Article> listArticle = jt.query("select * from blog_article where id= ?", new Object[]{id}, new BeanPropertyRowMapper<>(Article.class));
        if (listArticle != null && listArticle.size() > 0){
            return listArticle.get(0);
        }else {
            return null;
        }
    }
}
