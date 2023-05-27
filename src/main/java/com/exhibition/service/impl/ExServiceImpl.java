package com.exhibition.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.exhibition.entity.Exhibition;
import com.exhibition.entity.response_type.ExhibitionTag;
import com.exhibition.mapper.ExMapper;
import com.exhibition.mapper.ExTagMapper;
import com.exhibition.service.IExService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExServiceImpl extends ServiceImpl<ExMapper, Exhibition> implements IExService {
    @Autowired
    ExMapper exMapper;

    @Autowired
    ExTagMapper exTagMapper;
    @Override
    public ExhibitionTag selectAllInfoById(Integer ex_id){
        Exhibition exhibition = exMapper.selectById(ex_id);
        ExhibitionTag result = new ExhibitionTag();
        BeanUtils.copyProperties(exhibition,result);

        result.setTag_list(exMapper.findTagByExId(ex_id));
        return result;
    }
}
