package frameworkStudy.mybatis;


import gr.blog.mapper.ArticleMapper;
import gr.blog.model.Article;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * mybatis 通过xml文件的方式，学习源码
 */
public class MybatisInitByXml {

    public static void main(String[] args) throws IOException {
        String resource = "mybatis-config.xml";
        InputStream in = Resources.getResourceAsStream(resource);
        SqlSessionFactory ssf = new SqlSessionFactoryBuilder().build(in);
        SqlSession ss = ssf.openSession();
        List list = ss.selectList("");
        ArticleMapper mapper = ss.getMapper(ArticleMapper.class);
        Article article = mapper.selectByPrimaryKey(1);
        System.out.println(article);
    }
}
