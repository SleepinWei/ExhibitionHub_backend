package com.exhibition.entity;

import java.util.List;
import lombok.Data;

@Data
public class CalendarCache {
    private String src;
    private String dst;
    private List<SubExhibition> subExhibitions;
    private long timestamp;
}
