package gr.blog.service.impl;

import gr.blog.mapper.BlogTagMapper;
import gr.blog.model.BlogTag;
import gr.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service("tagService")
public class TagServiceImpl implements TagService {

    @Autowired
    private BlogTagMapper blogTagMapper;

    @Override
    public void addRecordsByIdAndTagname(Integer ArticleId, String[] tags) {
        List<BlogTag> list = new ArrayList<>();
        for(String tagName: tags){
            BlogTag tag = new BlogTag();
            tag.setArticleId(ArticleId);
            tag.setTagName(tagName);
            list.add(tag);
        }
        blogTagMapper.insertByBatch(list);
    }

    @Override
    public void updateRecordByIdAndTagname(Integer ArticleId, String[] tags) {
        //删除博客原有标签
        blogTagMapper.deleteByArticleKey(ArticleId);
        //添加新标签列表到博客
        List<BlogTag> list = new ArrayList<>();
        for(String tagName: tags){
            BlogTag tag = new BlogTag();
            tag.setArticleId(ArticleId);
            tag.setTagName(tagName);
            list.add(tag);
        }
        blogTagMapper.insertByBatch(list);
    }

    @Override
    public List<BlogTag> getTagsByArticleId(Integer ArticleId) {
        return blogTagMapper.selectByArticleId(ArticleId);
    }

    @Override
    public List<Map<String, Object>> getCloud() {
        return blogTagMapper.getCloud();
    }
}
