package com.exhibition.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.exhibition.entity.ExhibitionUncheckedAdmin;
import com.exhibition.entity.ExhibitionReview;

import java.util.List;

public interface ExToBeReviewedMapper extends BaseMapper<ExhibitionReview> {
    Integer getNextId();

    List<ExhibitionUncheckedAdmin> getUncheckedAdmin();

    List<ExhibitionUncheckedAdmin> getUnchecked(Integer user_id);
}
