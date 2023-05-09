package com.exhibition.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.exhibition.entity.PushMessage;
import com.exhibition.entity.Subscription;
import com.exhibition.entity.User;
import com.exhibition.mapper.SubscriptionMapper;
import com.exhibition.service.MailService;
import org.hibernate.mapping.Subclass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import java.sql.Date;
import java.util.List;

@Controller
public class PushMessageController {
    @Autowired
    private MailService mailService;

    @Autowired
    private SubscriptionMapper subMapper;

//     @Scheduled(cron = "0/20 * * * * ? ")
//     public void sendPushMessage(){
// //        List<Subscription> subs = subMapper.selectList((new QueryWrapper<Subscription>()).eq("user_id",1));
// //        for(Subscription sub : subs){
// //            System.out.println(sub.getEx_id());
// //            System.out.println(sub.getDate());
// //        }
//         System.out.println("Push Message");
//         Long time = System.currentTimeMillis();
//         Long upper_bound = time + 4 * 24 * 60 * 60 * 1000;
//         Date upper_bound_date = new Date(upper_bound);
//         Date current_date = new Date(time);

//         System.out.println(upper_bound_date);
//         System.out.println(current_date);

//         List<PushMessage> messages= subMapper.searchEmailByExDate(upper_bound_date,current_date);
//         for(PushMessage message : messages){
//             System.out.println(message.getEmail());
//             String content = "您预订于" + message.getBegin_date() + " 参观展览 " + message.getName() + " 将会在 " + message.getBegin_date();
//             mailService.sendHtmlMail(message.getEmail(),message.getName(),content);
//         }
//     }
}
