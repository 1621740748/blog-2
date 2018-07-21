package gr.blog.service.impl;

import gr.blog.mapper.BlogTagMapper;
import gr.blog.model.BlogTag;
import gr.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("tagService")
public class TagServiceImpl implements TagService {

    @Autowired
    private BlogTagMapper blogTagMapper;

    @Override
    public void addRecordsByIdAndTagname(Integer id, String[] tags) {
        List<BlogTag> list = new ArrayList<>();
        for(String tagName: tags){
            BlogTag tag = new BlogTag();
            tag.setArticleId(id);
            tag.setTagName(tagName);
            list.add(tag);
        }
        blogTagMapper.insertByBatch(list);
    }
}
