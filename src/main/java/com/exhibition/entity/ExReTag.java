package com.exhibition.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class ExReTag {
    @TableId
    private Integer id;

    private Integer exRe_id;

    private Integer tag_id;
}
