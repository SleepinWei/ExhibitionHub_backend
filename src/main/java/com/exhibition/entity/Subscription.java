package com.exhibition.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.sql.Date;

/**
 * @Author: JudyLou
 * @Date: 2023/4/29 12:10
 */
@Data
@TableName("subscription")
@NoArgsConstructor
@AllArgsConstructor
public class Subscription implements Serializable {
    @TableId(value = "user_id", type = IdType.AUTO)
    private Integer user_id; // 订阅某展览的用户id

    private Integer ex_id; // 订阅的展览id

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date date; // 用户选择的观展日期

    public Date getDate(){return date;}

    public Integer getExid(){return ex_id;}
}
