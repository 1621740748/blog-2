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
    void addCkickCount(int id, String userIp);

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
     * 批量删除元素
     * @param ids 文章id列表
     * @return 1 删除成功， 0 删除失败
     */
    String deleteBatch(int[] ids);

    /**
     * 获取当前元素的上一个文章
     * @param id
     * @param categoryId
     * @return
     */
    Article getPre(int id, Integer categoryId);

    /**
     * 获取当前元素的下一个文章
     * @param id
     * @param categoryId
     * @return
     */
    Article getNext(int id, Integer categoryId);

    List<Article> getContentHeader();

    List<Article> getSpecialRecommend();

    List<Article> getRecommendArticle();

    List<Article> getArticleByClick();

    /**
     * 获得文章喜欢数
     * @param id
     * @return
     */
    int getLikeCount(int id);

    /**
     * 判断当前ip是否喜欢过当前文章了
     * @param ArticleId
     * @param ip
     * @return true 表示已经喜欢过了，false 表示没喜欢过
     */
    boolean isLiked(Integer ArticleId, String ip);

    /**
     * 增加一个喜欢
     * @param articleId
     * @param ip
     */
    void addLike(Integer articleId, String ip);

    /**
     * 获取页面基本统计信息，包括：浏览量、文章总数、ip总数、喜欢数
     * @return
     */
    Map<String,Object> getBaseStatisticMap();
}

