package com.exhibition.entity;

import lombok.Data;

@Data
public class EmailVerification {
    private String email;
    private String code;
    private long timestamp;
    private boolean isVeri;

    public EmailVerification(String email, String vercode, long timestamp, boolean isVeri) {
        this.email=email;
        this.code=vercode;
        this.timestamp=timestamp;
        this.isVeri=isVeri;
    }
}
