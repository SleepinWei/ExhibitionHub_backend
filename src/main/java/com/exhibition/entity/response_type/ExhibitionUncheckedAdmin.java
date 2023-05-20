package com.exhibition.entity.response_type;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.sql.Date;

@Data
public class ExhibitionUncheckedAdmin {
    private String name; // 展览名

    private Integer id; // 修改记录id

    private String user_id; // 修改人

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date date; // 修改日期

    private String type;
}
