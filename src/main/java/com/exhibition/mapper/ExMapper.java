package com.exhibition.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.exhibition.entity.Exhibition;

import java.util.List;

public interface ExMapper extends BaseMapper<Exhibition> {
    List<Exhibition> searchByKeyword(String keyword);
}
