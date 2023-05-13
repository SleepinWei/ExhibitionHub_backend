package com.exhibition.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.sql.Date;

@Data
@TableName("ex_review")
public class ExhibitionUncheckedAdmin {
    @TableField
    private String name;

    @TableField
    private String organizer; // 主办方

    @TableField
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date date;

}
