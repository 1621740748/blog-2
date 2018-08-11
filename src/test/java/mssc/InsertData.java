package mssc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class InsertData {

    public static void main(String[] args) throws ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        for (int i = 0; i< 20; i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/blog","root", "Jiangzy123@");
                        Statement statement = connection.createStatement();
                        for (int j = 0; j < 15000; j++){
                            statement.execute("INSERT INTO `blog`.`blog_article` (`title`, `description`, `content`, `click_count`, `is_recommend`, `date_publish`, `author`, `category_id`, `photo`) VALUES ('2018 面试总结', '面试总结', '<p>八月三日</p>\\r\\n\\r\\n<p>1 java 好在哪里？</p>\\r\\n\\r\\n<p>2 mysql limit性能优化，在数据量大的情况下，如何优化limit查询语句？</p>\\r\\n\\r\\n<p><strong>答：</strong>准备数据，插入了30万条记录，再使用limit结果如下：</p>\\r\\n\\r\\n<p><img alt=\\\"\\\" src=\\\"http://wx4.sinaimg.cn/mw690/0060lm7Tly1fu2fgtmoasj318707qjrv.jpg\\\" style=\\\"height:121px; width:690px\\\" /></p>\\r\\n\\r\\n<p>证明limit数据量大下，有效率问题。如何解决？</p>\\r\\n\\r\\n<p><strong>一 简单优化：利用自增主键，避免offset的使用</strong></p>\\r\\n\\r\\n<pre>\\r\\n<code class=\\\"language-sql\\\">SELECT * FROM blog.blog_article where id&gt;270000 limit 10;\\r\\n...\\r\\nSELECT * FROM blog.blog_article where id&gt;280000 limit 10;\\r\\nSELECT * FROM blog.blog_article where id&gt;290000 limit 10;</code></pre>\\r\\n\\r\\n<p>效果如下：</p>\\r\\n\\r\\n<p><img alt=\\\"\\\" src=\\\"http://wx4.sinaimg.cn/mw690/0060lm7Tly1fu3jpydgctj31850g5djr.jpg\\\" style=\\\"height:252px; width:690px\\\" /></p>\\r\\n\\r\\n<p>缺点是：通常不是按照自增主键的顺序逐一增加的，多表查询、条件查询、数据记录部分被删除过出现断层，主键值就不好判断了，我的表就是如此，缺少3000到30000的数据，所以这种优化只有少数情况下才可以使用。</p>\\r\\n\\r\\n<p>二&nbsp;建立临时表(含自增主键)、或延迟关联，建立临时表：</p>\\r\\n\\r\\n<pre>\\r\\n<code class=\\\"language-sql\\\">CREATE TEMPORARY TABLE tmp_id(\\r\\n `uid` bigint(20) NOT NULL AUTO_INCREMENT,\\r\\n `id` bigint(20) NOT NULL,\\r\\n PRIMARY KEY (`uid`)) </code></pre>\\r\\n\\r\\n<p>使用临时表需要将查询信息插入到临时表：</p>\\r\\n\\r\\n<pre>\\r\\n<code class=\\\"language-sql\\\">INSERT INTO tmp_id\\r\\nSELECT null,id FROM blog_article</code></pre>\\r\\n\\r\\n<p>最后使用中间表，并使用方法一种方法，和主表关联查询</p>\\r\\n\\r\\n<pre>\\r\\n<code class=\\\"language-sql\\\">SELECT id FROM tmp_id where uid &gt; 270000 LIMIT 10</code></pre>\\r\\n\\r\\n<p>看一下效果：</p>\\r\\n\\r\\n<p><img alt=\\\"\\\" src=\\\"http://wx4.sinaimg.cn/mw690/0060lm7Tly1fu3kxfwmn3j31810eydhh.jpg\\\" style=\\\"height:234px; width:690px\\\" /></p>\\r\\n\\r\\n<p>还是不错的。</p>\\r\\n\\r\\n<p>还有一种是使用延迟关联，原理同临时表，因为每次使用select * 中的*影响到了效率，如果只查询主键然后再和主表关联查询：</p>\\r\\n\\r\\n<pre>\\r\\n<code class=\\\"language-sql\\\">select id from blog_article limit 2700000,10;</code></pre>\\r\\n\\r\\n<p>查看一下效果：</p>\\r\\n\\r\\n<p><img alt=\\\"\\\" src=\\\"http://wx2.sinaimg.cn/mw690/0060lm7Tly1fu3le1rso9j318107w3yw.jpg\\\" /></p>\\r\\n\\r\\n<p>效率虽然不如建立临时表高效，但胜在处理方式简单。</p>\\r\\n\\r\\n<p>参考资料：</p>\\r\\n\\r\\n<p>&nbsp;<strong><a href=\\\"https://www.cnblogs.com/codeAB/p/6391607.html\\\" target=\\\"_blank\\\">mysql 大数据量分页优化</a></strong></p>\\r\\n\\r\\n<p><strong><a href=\\\"http://www.cnblogs.com/beynol/p/mysql-optimization-limit.html\\\" target=\\\"_blank\\\">[MySQL性能优化系列]LIMIT语句优化</a></strong></p>\\r\\n\\r\\n<p>3 说说你对Struts2的理解？</p>\\r\\n\\r\\n<hr />\\r\\n<p>&nbsp;</p>\\r\\n\\r\\n<p>八月八日</p>\\r\\n\\r\\n<p>1 hibernate lazy属性什么时候使用？什么业务场景下需要使用lazy属性？在大型项目中，如果业务足够复杂，无法判断是否使用lazy属性时，你会怎么解决？</p>\\r\\n\\r\\n<p>2 hibernate 关联关系都有哪些？</p>\\r\\n\\r\\n<p>3 String str = new String(&quot;abc&quot;);这条语句一共创建了多少个对象？</p>\\r\\n\\r\\n<p>4 jQuery 有几种传值方式？</p>\\r\\n\\r\\n<p>5 Struts2 传值方式和springmvc传值方式都是什么？ 有什么不同？</p>\\r\\n\\r\\n<p>6 springmvc 如何返回json串？Struts2又是如何返回json串？</p>\\r\\n\\r\\n<p>7 如何从一张&quot;成绩表&quot;中查询出男女生的个数？有几种方法，全部写出来。</p>\\r\\n\\r\\n<p>8 mybatis 有几种配置方式?默认情况下使用什么规则？</p>\\r\\n\\r\\n<hr />\\r\\n<p>&nbsp;</p>\\r\\n\\r\\n<p>八月九日</p>\\r\\n\\r\\n<p>1 spring 业务层处理业务的过程中，如果出现根据不同判定条件下，需要强制回滚或提交事务，如何处理？（注意不是在方法上加@Transaction注解来处理）</p>', '10', '1', '2018-08-03', 'superman', '13', 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1533291895094&di=11b174edc385b0dc265c0aa0d38cef66&imgtype=0&src=http%3A%2F%2Fwww.haorencai.net%2Fdata%2Fupload%2Fkindeditor%2Fimage%2F20170328%2F20170328111820_85516.jpg');\n");
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }

                }
            }).start();

        }
    }
}
