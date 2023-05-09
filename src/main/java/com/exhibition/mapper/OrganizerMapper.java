package com.exhibition.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.exhibition.entity.Organizer;

import java.util.List;


/**
 * @Author: JudyLou
 * @Date: 2023/5/9 15:35
 */
public interface OrganizerMapper extends BaseMapper<Organizer> {
    public List<String> selectAllOrganizers();
}
