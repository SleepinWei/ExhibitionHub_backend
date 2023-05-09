package com.exhibition.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.exhibition.entity.Tag;
import com.exhibition.mapper.TagMapper;
import com.exhibition.service.ITagService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements ITagService{
    @Autowired
    TagMapper tagmapper;
    public List<String> getTagName(){
        return tagmapper.selectAllTags();
    }
}
