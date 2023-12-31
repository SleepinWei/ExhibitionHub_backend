package com.exhibition.entity;

import lombok.Data;

import java.util.List;

@Data
public class CalendarCache {
    private String src;
    private String dst;
    private List<SubExhibition> subExhibitions;
    private long timestamp;

    public void setDst(java.lang.String dst) {
        this.dst = dst;
    }
}
