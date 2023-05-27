package com.exhibition.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.exhibition.entity.ExhibitionReview;
import com.exhibition.entity.response_type.ExhibitionReviewTag;

public interface IExToBeReviewedService extends IService<ExhibitionReview> {
    public ExhibitionReviewTag selectFullInfoById(Integer ex_review_id);
}
