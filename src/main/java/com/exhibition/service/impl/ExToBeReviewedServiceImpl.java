package com.exhibition.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exhibition.entity.ExhibitionToBeReviewed;
import com.exhibition.mapper.ExToBeReviewedMapper;
import com.exhibition.service.IExToBeReviewedService;

@Service
public class ExToBeReviewedServiceImpl extends ServiceImpl<ExToBeReviewedMapper, ExhibitionToBeReviewed>
        implements IExToBeReviewedService {
    @Autowired
    ExToBeReviewedMapper exToBeReviewedMapper;
}
