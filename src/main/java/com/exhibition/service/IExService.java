package com.exhibition.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.exhibition.entity.Exhibition;

import java.util.List;

public interface IExService extends IService<Exhibition> {
    List<Exhibition> searchByKeyword(String keyword);
}
