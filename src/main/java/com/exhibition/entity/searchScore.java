package com.exhibition.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

@Data
@TableName("search_score")
public class searchScore {
    @TableId
    private Integer id; // 订阅的展览id

    @TableField
    private Float score;

    public Integer getId() {
        return id;
    }

    public Float getScore() {
        return score;
    }
}
