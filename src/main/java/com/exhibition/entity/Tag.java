package com.exhibition.entity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("tag")
public class Tag {
    @TableId
    private Integer id;

    @TableField
    private String name;
}
