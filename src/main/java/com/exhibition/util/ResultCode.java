package com.exhibition.util;

/**
 * @Author: JudyLou
 * @Date: 2023/4/18 15:46
 */
public enum ResultCode {
    // 自定义枚举内容
    SUCCESS(200,"SUCCESS"),
    REGULAR_USER(300, "登录成功！即将跳转到普通用户界面..."),
    ADMINISTRATOR(400,"登录成功！即将跳转到管理员界面..."),
    MUSEUM(600,"登录成功！即将跳转到博物馆界面..."),
    ERROR(-100, "Error");


    private Integer code;
    private String msg;

    ResultCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
