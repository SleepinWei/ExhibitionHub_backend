package com.exhibition.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author
 * @since 2023-04-11
 */
@TableName("schedule")
public class Schedule implements Serializable {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String date;

    private String content;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Content{" +
                "id=" + id +
                ", date=" + date +
                ", content=" + content +
                "}";
    }
}
