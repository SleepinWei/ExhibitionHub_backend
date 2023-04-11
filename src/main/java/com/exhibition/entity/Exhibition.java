package com.exhibition.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@TableName("exhibition")
public class Exhibition {
    @TableId
    private Integer id; // 主键id

    private String name; // 展览名称

    private String organizer; // 主办方

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date beginDate; // 开始日期

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate; // 结束日期

    private String location; // 地点

    private String introduction; // 图文内容简介
    // 如何存储图片?

    private String link; // 官方链接
}
