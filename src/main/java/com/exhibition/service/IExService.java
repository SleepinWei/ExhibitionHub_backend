package com.exhibition.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.exhibition.entity.Exhibition;
import com.exhibition.entity.response_type.ExhibitionTag;

public interface IExService extends IService<Exhibition> {
    public ExhibitionTag selectAllInfoById(Integer ex_id);
}
