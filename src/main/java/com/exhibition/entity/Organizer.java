package com.exhibition.entity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * @Author: JudyLou
 * @Date: 2023/5/9 15:28
 */
@TableName("organizer")
public class Organizer {
    @TableId
    private Integer id;

    @TableField
    private String name;
}
