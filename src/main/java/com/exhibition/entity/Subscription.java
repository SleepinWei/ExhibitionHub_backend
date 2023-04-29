package com.exhibition.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.io.Serializable;
/**
 * @Author: JudyLou
 * @Date: 2023/4/29 12:10
 */
@Data
@TableName("subscription")
public class Subscription implements Serializable {
    @TableId(value = "user_id", type = IdType.AUTO)
    private Integer user_id; // 订阅某展览的用户id

    private Integer ex_id; // 订阅的展览id

    private String date; // 用户选择的观展日期

    public Subscription(){
    }
    public Subscription(Integer user_id, Integer ex_id, String date) {
        this.user_id = user_id;
        this.ex_id = ex_id;
        this.date = date;
    }

}
