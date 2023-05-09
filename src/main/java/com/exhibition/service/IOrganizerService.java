package com.exhibition.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.exhibition.entity.Organizer;
import com.exhibition.entity.Tag;

import java.util.List;

/**
 * @Author: JudyLou
 * @Date: 2023/5/9 15:33
 */
public interface IOrganizerService extends IService<Organizer> {
    public List<String> getOrgainzerName();
}
