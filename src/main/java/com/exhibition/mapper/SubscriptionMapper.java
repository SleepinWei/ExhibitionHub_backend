package com.exhibition.mapper;
import com.exhibition.entity.Exhibition;
import com.exhibition.entity.PushMessage;
import com.exhibition.entity.Subscription;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.exhibition.entity.User;
import org.apache.ibatis.annotations.Mapper;
import java.sql.Date;
import java.util.List;
/**
 * @Author: JudyLou
 * @Date: 2023/4/29 12:11
 */
@Mapper
public interface SubscriptionMapper extends BaseMapper<Subscription> {
    Subscription deleteByExid(Integer user_id,Integer ex_id);// 取消订阅
    List<Subscription> searchByUserid(Integer user_id);// 查看订阅
    List<PushMessage> searchEmailByExDate(Date upper_bound_date, Date current_date);
}
