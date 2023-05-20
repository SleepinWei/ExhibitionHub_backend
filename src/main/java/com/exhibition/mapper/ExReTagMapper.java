package com.exhibition.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.exhibition.entity.ExReTag;
import java.util.List;

public interface ExReTagMapper extends BaseMapper<ExReTag> {
    List<String> selectTagById(Integer exRe_id);
}
