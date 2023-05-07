package com.exhibition.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.exhibition.entity.Subscription;
import com.exhibition.mapper.SubscriptionMapper;
import com.exhibition.service.MailService;
import org.hibernate.mapping.Subclass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class PushMessageController {
    @Autowired
    private MailService mailService;

    @Autowired
    private SubscriptionMapper subMapper;

    @Scheduled(cron = "0/5 * * * * ? ")
    public void sendPushMessage(){
        List<Subscription> subs = subMapper.selectList((new QueryWrapper<Subscription>()).eq("user_id",1));
        for(Subscription sub : subs){
            System.out.println(sub.getEx_id());
            System.out.println(sub.getDate());
        }
    }
}
