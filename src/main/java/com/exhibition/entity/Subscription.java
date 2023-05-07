package com.exhibition.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.sql.Date;

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
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date date; // 用户选择的观展日期

    public Subscription(){
    }
    public Subscription(Integer user_id, Integer ex_id, Date date) {
        this.user_id = user_id;
        this.ex_id = ex_id;
        this.date = date;
    }

}
