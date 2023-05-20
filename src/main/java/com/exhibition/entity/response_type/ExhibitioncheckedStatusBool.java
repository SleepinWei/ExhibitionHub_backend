package com.exhibition.entity.response_type;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.mockito.internal.verification.Times;
import org.springframework.format.annotation.DateTimeFormat;
import lombok.Data;
import java.sql.Date;
import java.sql.Timestamp;

@Data
public class ExhibitioncheckedStatusBool {
    private Integer id; // 主键id

    private Integer user_id;

    private String name; // 展览名称

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Timestamp date; // 开始日期

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Timestamp review_date; // 结束日期

    private String result;
    private String type;
}