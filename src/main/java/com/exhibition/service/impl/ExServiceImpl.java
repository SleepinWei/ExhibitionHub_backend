package com.exhibition.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.exhibition.entity.Exhibition;
import com.exhibition.entity.Exhibitionchecked;
import com.exhibition.entity.ExhibitioncheckedStatusBool;
import com.exhibition.mapper.ExMapper;
import com.exhibition.service.IExService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExServiceImpl extends ServiceImpl<ExMapper, Exhibition> implements IExService {

    @Autowired
    ExMapper exMapper;

    @Override
    public List<Exhibition> searchByKeyword(String keyword) {
        return exMapper.searchByKeyword(keyword);
    }

    @Override
    public List<Exhibitionchecked> selectchecked(Integer org_id) {
        List<ExhibitioncheckedStatusBool> tmp = exMapper.selectchecked(org_id);
        List<Exhibitionchecked> res = new ArrayList<>();
        for (ExhibitioncheckedStatusBool i : tmp) {
            Exhibitionchecked j = new Exhibitionchecked(i);
            res.add(j);
        }
        return res;
    }
}
