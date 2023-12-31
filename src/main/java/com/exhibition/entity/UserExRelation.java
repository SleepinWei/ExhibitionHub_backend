package com.exhibition.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Table;
import java.sql.Date;
import java.sql.Timestamp;

@Data
@TableName("user_ex_relation")
// user表，Ex表 和 待审核表 三表关联
@AllArgsConstructor
@NoArgsConstructor
public class UserExRelation {
    @TableField
    private Integer user_id;

    @TableField
    private Integer ex_id;

    @TableId
    private Integer ex_review_id;

    @TableField
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp date;

    @TableField
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp review_date;

    @TableField
    private Boolean is_done;

    @TableField
    private String result;

    @TableField
    private String type;
}
