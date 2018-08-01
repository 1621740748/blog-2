package gr.blog.service.impl;

import com.github.pagehelper.PageHelper;
import gr.blog.mapper.ArticleMapper;
import gr.blog.model.Article;
import gr.blog.service.ArticleService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service(value = "articleService")
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public List<Article> findArticleList(int pageNum, int pageSize, Map<String, Object> filter) {
        //下边是证明mybatis二级缓存证明，在声明周期内两次查询，第二次要比第一次短很多时间。
        //Date first = new Date();
        PageHelper.startPage(pageNum, pageSize);
        List<Article> list = articleMapper.getAllArticle(filter);
        //System.out.println("second level cache query costs " + (new Date().getTime()-first.getTime()) + "ms");
        return list;
    }

    @Override
//    @Transactional
    public Article get(int id) {
        //下边的代码用于证明mybatis的一级缓存是否生效，当不加@Transactional是不生效的，说明整合
        //spring 后mybatis一级缓存确实失效了。
//        Date first = new Date();
//        articleMapper.selectByPrimaryKey(id);
//        System.out.println("first query costs " + (new Date().getTime()-first.getTime()) + "ms");
//        Date second = new Date();
//        articleMapper.selectByPrimaryKey(id);
//        System.out.println("second query costs " + (new Date().getTime()-second.getTime()) + "ms");
        return articleMapper.selectByPrimaryKey(id);
    }

    @Override
    public void addCkickCount(int id, String userIp) {
        articleMapper.addClickCount(id, userIp);
    }

    @Override
    public int addRecord(Article article) {
        article.setDatePublish(new Date());
        Subject subject = SecurityUtils.getSubject();
        String userName = (String)subject.getPrincipal();
        article.setAuthor(userName);
        return articleMapper.insertSelective(article);
    }

    @Override
    @Transactional
    public int updateRecord(Article article) {
        return articleMapper.updateByPrimaryKeySelective(article);
    }

    @Override
    public String deleteBatch(int[] ids) {
        int deleteRows = articleMapper.deleteBatch(ids);
        if (deleteRows != 0){
            return "1";
        }else{
            return "0";
        }
    }

    @Override
    public Article getPre(int id, Integer categoryId) {
        return articleMapper.getPre(id, categoryId);
    }

    @Override
    public Article getNext(int id, Integer categoryId) {
        return articleMapper.getNext(id, categoryId);
    }

    @Override
    public List<Article> getContentHeader() {
        return articleMapper.getContentHeader();
    }

    @Override
    public List<Article> getSpecialRecommend() {
        return articleMapper.getSpecialRecommend();
    }

    @Override
    public List<Article> getRecommendArticle() {
        return articleMapper.getRecommendArticle();
    }

    @Override
    public List<Article> getArticleByClick() {
        return articleMapper.getArticleByClick();
    }

    @Override
    public int getLikeCount(int id) {
        return articleMapper.getLikeCount(id);
    }

    @Override
    public boolean isLiked(Integer ArticleId, String remoteAddr) {
        if(articleMapper.isLiked(ArticleId, remoteAddr) != 0){
            return true;
        }
        return false;
    }

    /**
     * 增加一个喜欢
     *
     * @param articleId
     * @param ip
     */
    @Override
    public void addLike(Integer articleId, String ip) {
        articleMapper.addLike(articleId, ip);
    }
}
