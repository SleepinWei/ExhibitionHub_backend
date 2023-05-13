package com.exhibition.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.exhibition.entity.Exhibition;
import com.exhibition.entity.Exhibitionchecked;

import java.util.List;

public interface IExService extends IService<Exhibition> {
    List<Exhibition> searchByKeyword(String keyword);

    List<Exhibitionchecked> selectchecked(Integer org_id);

    List<Exhibitionchecked> selectCheckedAdmin();
}
