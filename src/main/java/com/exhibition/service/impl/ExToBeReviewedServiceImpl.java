package com.exhibition.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.exhibition.entity.Tag;
import com.exhibition.entity.response_type.ExhibitionReviewTag;
import com.exhibition.mapper.ExReTagMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exhibition.entity.ExhibitionReview;
import com.exhibition.mapper.ExToBeReviewedMapper;
import com.exhibition.service.IExToBeReviewedService;

import java.util.List;

@Service
public class ExToBeReviewedServiceImpl extends ServiceImpl<ExToBeReviewedMapper, ExhibitionReview>
        implements IExToBeReviewedService {
    @Autowired
    ExToBeReviewedMapper exToBeReviewedMapper;
    @Autowired
    ExReTagMapper exReTagMapper;

    @Override
    public ExhibitionReviewTag selectFullInfoById(Integer ex_review_id) {
        ExhibitionReview review = exToBeReviewedMapper.selectById(ex_review_id);
        ExhibitionReviewTag result = new ExhibitionReviewTag();
        BeanUtils.copyProperties(review,result);

        List<Tag> tags = exReTagMapper.selectTagById(ex_review_id);
        result.setTag_list(tags);

        return result;
    }
}