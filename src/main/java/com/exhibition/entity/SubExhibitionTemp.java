package com.exhibition.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.sql.Date;

@Data
public class SubExhibitionTemp {
    private Integer ex_id; // 订阅的展览id

    private String date; // 用户选择的观展日期

    private String name; // 展览名称

    private String venue_name; // 展馆名称

    private String organizer; // 主办方

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date begin_date; // 开始日期

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date end_date; // 结束日期

    private String province; // 省

    private String city; // 市

    private String area; // 区

    private String address; // 详细地址

    private String link; // 官方链接

    private Integer tag_id;
}
