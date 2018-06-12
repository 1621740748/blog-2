package frameworkStudy.mybatis;


import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.mybatis.spring.SqlSessionFactoryBean;

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
    }
}
