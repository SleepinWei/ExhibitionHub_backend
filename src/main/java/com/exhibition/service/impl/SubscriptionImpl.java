package com.exhibition.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.exhibition.entity.Exhibition;
import com.exhibition.entity.Subscription;
import com.exhibition.mapper.ExMapper;
import com.exhibition.mapper.SubscriptionMapper;
import com.exhibition.service.SubscriptionService;
import com.exhibition.service.impl.SubscriptionImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.spel.ast.NullLiteral;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
/**
 * @Author: JudyLou
 * @Date: 2023/4/29 12:12
 */
@Service
public class SubscriptionImpl extends ServiceImpl<SubscriptionMapper, Subscription> implements SubscriptionService {
    @Autowired
    SubscriptionMapper subscriptionMapper;

    @Autowired
    ExMapper exMapper;

    // 添加订阅
    public Integer addSubscription(Integer user_id,Integer ex_id,String date ){
        Exhibition exhibition = exMapper.selectById(ex_id);// 根据ex_id在exhibition表中查找对应展览

//        System.out.print(exhibition);
        Date _date = Date.valueOf(date); // 将string转成Date
//        System.out.println(_date);

        // 获得展览起止日期
        Date beginDate = exhibition.getBegin_date();
        Date endDate = exhibition.getEnd_date();
//        System.out.println(beginDate);
//        System.out.println(endDate);

        // 判断订阅日期是否落在起止日期之间
        boolean dateCorrect = beginDate.equals(_date) || endDate.equals(_date) ||
                beginDate.before(_date) && endDate.after(_date); //FIXME:是否够精确？

        if(dateCorrect){// 订阅日期合法
            Subscription new_subscription = new Subscription(user_id,ex_id,date);
            subscriptionMapper.insert(new_subscription); // 往订阅表中插入一条记录
            return 1; // 订阅成功
        }
        else{
            return -1;// 订阅日期不在展览日期中
        }
        // TODO: return 0 ; unreachable statement
    }

    // 取消订阅
    public Integer cancelSubscription(Integer user_id,Integer ex_id){

        Exhibition exhibition = exMapper.selectById(ex_id);// 根据ex_id在exhibition表中查找对应展览

        if(null != exhibition){// 存在订阅该展览的记录
            subscriptionMapper.deleteByExid(user_id,ex_id); // 往订阅表中删除一条记录
            return 1; // 取消订阅成功
        }
        else{
            return 0;// 取消订阅失败
        }
    }

    // 查看订阅
    public List<Exhibition> viewSubscription(Integer user_id){
        List<Subscription> allSub = subscriptionMapper.searchByUserid(user_id);
        List<Exhibition> allEx = new ArrayList<>();
        for(Subscription sub : allSub){
            Integer ex_id = sub.getEx_id();
            Exhibition ex = exMapper.selectById(ex_id);
            allEx.add(ex);
        }
        return allEx;
    }
}
