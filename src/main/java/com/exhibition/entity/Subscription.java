package com.exhibition.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("subscription")
public class Subscription implements Serializable {
    @TableId
    private Integer user_id; // 订阅某展览的用户id

    private Integer ex_id; // 订阅的展览id

    private String visitdate; // 用户选择的观展日期
}

