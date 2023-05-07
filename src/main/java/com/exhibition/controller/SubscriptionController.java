package com.exhibition.controller;

import com.exhibition.entity.Exhibition;
import com.exhibition.entity.Subscription;
import com.exhibition.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.sql.Date;
import java.util.Map;

import static java.lang.Integer.parseInt;

/**
 * @Author: JudyLou
 * @Date: 2023/4/29 12:09
 */
@Controller
@RestController
@RequestMapping()
public class SubscriptionController {
    @Autowired
    private SubscriptionService subscriptionService;

    // 添加订阅
    @PostMapping("/subscribe/postUesrSub")
    public Integer addSubscription(@RequestBody Map<String, Object> requestBody){//@RequestParam Integer user_id, @RequestParam Integer ex_id, @RequestParam String date ){
        Integer user_id = parseInt(String.valueOf(requestBody.get("user_id")));
        Integer ex_id = parseInt(String.valueOf(requestBody.get("ex_id")));
//        Date date = (Date) requestBody.get("date");
        String s_date = (String) requestBody.get("date");
        Date sqlDate = null ;
        try{
             java.util.Date utilDate = new SimpleDateFormat("yyyy-MM-dd").parse(s_date);
             sqlDate = new java.sql.Date(utilDate.getTime());
        }catch (ParseException e) {
            e.printStackTrace();
        }

        Integer result = subscriptionService.addSubscription(user_id,ex_id,sqlDate);
        return result;
    }

    // 取消订阅
    @PostMapping("/subscribe/cancelUesrSub")
    public Integer cancelSubscription(@RequestBody Map<String, Object> requestBody){//@RequestParam Integer user_id,@RequestParam Integer ex_id){
        Integer user_id = parseInt(String.valueOf(requestBody.get("user_id")));
        Integer ex_id = parseInt(String.valueOf(requestBody.get("ex_id")));
        Integer result = subscriptionService.cancelSubscription(user_id,ex_id);
        return result;
    }

    // 查看订阅
    @GetMapping("/subscribe/getAllSub/{user_id}")
    public List<Exhibition> ViewSubscription(@PathVariable String user_id){
        List<Exhibition> allSubscription= subscriptionService.viewSubscription(Integer.valueOf(user_id));
        return allSubscription;
    }

    // 查看用户订阅某展览的时间
    @PostMapping("/subscribe/getSubDate")
    public String ViewSubscriptionDate(@RequestBody Map<String, Object> requestBody){
        Integer user_id = parseInt(String.valueOf(requestBody.get("user_id")));
        Integer ex_id = parseInt(String.valueOf(requestBody.get("ex_id")));
        return subscriptionService.viewSubscriptionDate(user_id,ex_id);
    }

    // 查询某用户是否订阅某展览
    @PostMapping("/subscribe/isSub")
    public Integer isSubscribed(@RequestBody Map<String, Object> requestBody){
        Integer user_id = parseInt(String.valueOf(requestBody.get("user_id")));
        Integer ex_id = parseInt(String.valueOf(requestBody.get("ex_id")));
        return subscriptionService.isSubscribed(user_id,ex_id);
    }
}
