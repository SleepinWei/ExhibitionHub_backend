package com.exhibition.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.exhibition.entity.UserExRelation;
import com.exhibition.mapper.UserExRelMapper;
import com.exhibition.service.IUserExRelService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserExRelServiceImpl extends ServiceImpl<UserExRelMapper, UserExRelation> implements IUserExRelService {

    @Autowired
    UserExRelMapper userExRelMapper;
}
