package com.exhibition.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;

@Data
@TableName("exhibition")
@AllArgsConstructor
public class Exhibition {
    @TableId
    private Integer id; // 主键id

    @TableField
    private String name; // 展览名称

    @TableField
    private String organizer; // 主办方

    @TableField
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date begin_date; // 开始日期

    @TableField
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date end_date; // 结束日期

    @TableField
    private String location; // 地点

    @TableField
    private String introduction; // 图文内容简介

    //TODO: a Table field for images

    @TableField
    private String link; // 官方链接
}
