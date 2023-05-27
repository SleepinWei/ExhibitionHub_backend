package com.exhibition.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@TableName("exhibitionRe_tag")
public class ExReTag {
    @TableId
    private Integer id;

    @TableField
    private Integer exRe_id;

    @TableField
    private Integer tag_id;
}
