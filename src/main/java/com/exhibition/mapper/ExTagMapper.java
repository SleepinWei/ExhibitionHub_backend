package com.exhibition.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.exhibition.entity.ExTag;
import java.util.List;

public interface ExTagMapper extends BaseMapper<ExTag> {
    List<Integer> selectAllTagids(Integer ex_id);
}
