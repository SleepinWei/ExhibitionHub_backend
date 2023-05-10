package com.exhibition.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.exhibition.entity.ExhibitionReview;
import com.exhibition.entity.UserExRelation;

import java.util.List;

public interface UserExRelMapper extends BaseMapper<UserExRelation> {
    List<ExhibitionReview> getUncheckedEx();
}
