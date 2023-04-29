package com.exhibition.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Data
public class SubExhibition {
    private Integer ex_id; // 订阅的展览id

    private String visitdate; // 用户选择的观展日期

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

    private List<Integer> tags=new ArrayList<>();   // 该展览对应的标签

    public SubExhibition(SubExhibitionTemp temp){
        ex_id=temp.getEx_id();
        visitdate=temp.getVisitdate();
        name=temp.getName();
        venue_name=temp.getVenue_name();
        organizer=temp.getOrganizer();
        begin_date=temp.getBegin_date();
        end_date=temp.getEnd_date();
        province=temp.getProvince();
        city=temp.getCity();
        area=temp.getArea();
        address=temp.getAddress();
        link=temp.getLink();
        tags.add(temp.getTag_id());
    }
}
