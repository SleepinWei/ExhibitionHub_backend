package com.exhibition.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.exhibition.entity.Tag;

import java.util.List;

public interface ITagService extends IService<Tag> {
    public List<String> getTagName();
}
