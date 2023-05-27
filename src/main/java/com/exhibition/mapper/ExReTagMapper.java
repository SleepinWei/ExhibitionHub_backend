package com.exhibition.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.exhibition.entity.ExReTag;
import com.exhibition.entity.Tag;

import java.util.List;

public interface ExReTagMapper extends BaseMapper<ExReTag> {
    List<Tag> selectTagById(Integer exRe_id);
}
