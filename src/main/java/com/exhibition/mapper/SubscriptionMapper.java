package com.exhibition.mapper;
import com.exhibition.entity.Exhibition;
import com.exhibition.entity.Subscription;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.exhibition.entity.User;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;
/**
 * @Author: JudyLou
 * @Date: 2023/4/29 12:11
 */
@Mapper
public interface SubscriptionMapper extends BaseMapper<Subscription> {
    Subscription deleteByExid(Integer user_id,Integer ex_id);// 取消订阅
    List<Subscription> searchExidByUserid(Integer user_id);// 查看指定user_id对应的所有ex_id

    List<Subscription> searchAllByUserid(Integer user_id);// 查看指定user_id的订所有阅信息（user_id、ex_id、date）

}
