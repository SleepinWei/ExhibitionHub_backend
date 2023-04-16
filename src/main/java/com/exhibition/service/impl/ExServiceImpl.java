package com.exhibition.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.exhibition.entity.Exhibition;
import com.exhibition.mapper.ExMapper;
import com.exhibition.service.IExService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExServiceImpl extends ServiceImpl<ExMapper, Exhibition> implements IExService {

}
