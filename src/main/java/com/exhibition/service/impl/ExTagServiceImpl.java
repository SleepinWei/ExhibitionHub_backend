package com.exhibition.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.exhibition.entity.ExTag;
import com.exhibition.mapper.ExTagMapper;
import com.exhibition.service.IExTagService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExTagServiceImpl extends ServiceImpl<ExTagMapper, ExTag> implements IExTagService {

}
