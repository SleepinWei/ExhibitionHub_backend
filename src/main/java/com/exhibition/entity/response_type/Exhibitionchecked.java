package com.exhibition.entity.response_type;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import lombok.Data;
import java.sql.Date;

@Data
@TableName("exhibition")
@Deprecated
public class Exhibitionchecked {
    @TableId(value = "id", type = IdType.INPUT)
    private Integer id; // 主键id

    @TableField
    private String name; // 展览名称

    @TableField
    private String venue_name; // 展览场地名称

    @TableField
    private String organizer; // 展览场地名称

    @TableField
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date begin_date; // 开始日期

    @TableField
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date end_date; // 结束日期

    @TableField
    private String status;
}