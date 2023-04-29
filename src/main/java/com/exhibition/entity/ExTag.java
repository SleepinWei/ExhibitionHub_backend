package com.exhibition.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("exhibition_tag")
public class ExTag {
    @TableId
    private Integer id;

    private Integer ex_id;

    private Integer tag_id;
}
