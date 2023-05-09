package com.exhibition.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import lombok.Data;
import java.sql.Date;

@Data
@Deprecated
public class ExhibitionUnchecked {
    @TableId(value = "id", type = IdType.INPUT)
    private Integer id; // 主键id

    @TableField
    private String name; // 展览名称

    @TableField
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date edit_time; // 上次修改时间
}