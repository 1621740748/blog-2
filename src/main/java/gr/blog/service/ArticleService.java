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
    void addCkickCount(int id);

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
     * 查询文章总数
     * @return
     * @param filter
     */
    int getCount(Map<String, Object> filter);

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
}

