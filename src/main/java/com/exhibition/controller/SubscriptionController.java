package com.exhibition.controller;

import com.exhibition.entity.Exhibition;
import com.exhibition.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
        String date = (String) requestBody.get("date");
        Integer result = subscriptionService.addSubscription(user_id,ex_id,date);
        return result;
    }

    // 取消订阅
    @PostMapping("/subscribe/cancelUesrSub")
    public Integer cancelSubscription(@RequestBody Map<String, Object> requestBody){//@RequestParam Integer user_id,@RequestParam Integer ex_id){
        Integer user_id = (Integer) requestBody.get("user_id");
        System.out.println(user_id);
        Integer ex_id = (Integer) requestBody.get("ex_Id");


        Integer result = subscriptionService.cancelSubscription(user_id,ex_id);
        return result;
    }

    // 查看订阅
    @PostMapping("/subscription")
    public List<Exhibition> ViewSubscription(@RequestParam Integer user_id){
        List<Exhibition> allSubscription= subscriptionService.viewSubscription(user_id);
        return allSubscription;
    }
}
