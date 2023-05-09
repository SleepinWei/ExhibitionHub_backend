package com.exhibition.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.exhibition.entity.Organizer;
import com.exhibition.mapper.OrganizerMapper;
import com.exhibition.service.IOrganizerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: JudyLou
 * @Date: 2023/5/9 15:34
 */
@Service
@RequiredArgsConstructor
public class OrganizerServiceImpl extends ServiceImpl<OrganizerMapper, Organizer> implements IOrganizerService {
    @Autowired
    OrganizerMapper orgainzermapper;
    public List<String> getOrgainzerName(){
        return orgainzermapper.selectAllOrganizers();
    }
}
