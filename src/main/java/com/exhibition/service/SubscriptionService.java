package com.exhibition.service;

import com.exhibition.entity.Exhibition;
import com.exhibition.entity.Subscription;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
/**
 * @Author: JudyLou
 * @Date: 2023/4/29 12:12
 */
public interface SubscriptionService extends IService<Subscription>{
    public Integer addSubscription(Integer user_id,Integer ex_id, String date);// 添加订阅
    public Integer cancelSubscription(Integer user_id, Integer ex_id);// 取消订阅
    public List<Exhibition> viewSubscription(Integer user_id);// 查看订阅
}

