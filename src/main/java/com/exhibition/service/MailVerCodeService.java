package com.exhibition.service;

import org.springframework.stereotype.Service;


public interface MailVerCodeService {

    /**
     * 发送简单邮件
     *
     * @param to      收件人
     */
    String sendVerCodeMail(String to);
}
