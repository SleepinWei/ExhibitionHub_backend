package com.exhibition.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.exhibition.entity.Tag;

import java.util.List;

public interface TagMapper extends BaseMapper<Tag> {
    public List<String> selectAllTags();
}
