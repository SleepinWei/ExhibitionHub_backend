package com.exhibition.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.exhibition.entity.Exhibition;
import com.exhibition.entity.Tag;

import java.util.List;

public interface ExMapper extends BaseMapper<Exhibition> {
    List<Exhibition> searchByKeyword(String keyword);

    List<Tag> findTagByExId(Integer ex_id);
}
