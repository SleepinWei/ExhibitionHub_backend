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

import java.sql.Date;
import java.sql.Time;

@Data
@TableName("ex_review")
@AllArgsConstructor
@NoArgsConstructor
public class ExhibitionReview {

    // public ExhibitionToBeReviewed(Integer id,String name,String venue_name,String
    // ticket_info,String organizer,Date edit_time,
    // Date begin_date,Date end_date,Time begin_time,Time end_time,String
    // location,String introduction,String link,String poster_url,
    // Boolean is_checked){
    // ;
    // };

    @TableId(value = "id", type = IdType.INPUT)
    private Integer id; // 主键id

    @TableField
    private String name; // 展览名称

    @TableField
    private String venue_name; // 展馆名称

    @TableField
    private String ticket_info; // 票务信息

    @TableField
    private String organizer; // 主办方

    @TableField
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date begin_date; // 开始日期

    @TableField
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date end_date; // 结束日期

    @TableField
    @DateTimeFormat(pattern = "HH:mm:ss")
    @JsonFormat(pattern = "HH:mm:ss")
    private Time begin_time; // 开始时间

    @TableField
    @DateTimeFormat(pattern = "HH:mm:ss")
    @JsonFormat(pattern = "HH:mm:ss")
    private Time end_time; // 结束时间

    @TableField
    private String province; // 省

    @TableField
    private String city; // 市

    @TableField
    private String area; // 区

    @TableField
    private String address;


    @TableField
    private String introduction; // 图文内容简介

    // TODO: a Table field for images

    @TableField
    private String link; // 官方链接

    @TableField
    private String poster_url;

    @TableField
    private Boolean is_checked;// 审核
}
