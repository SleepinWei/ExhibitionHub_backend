package com.exhibition.service.impl;

import com.exhibition.service.MailService;
import com.exhibition.service.MailVerCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Random;
@Service
public class MailVerCodeServiceImpl  implements MailVerCodeService {

    @Autowired
    private MailService mailService;

    private static final String SYMBOLS = "0123456789ABCDEFGHIGKLMNOPQRSTUVWXYZ";
    private static final Random RANDOM = new SecureRandom();
    //    生成 6 位数的随机数字
    private static String generateVerCode() {
        //	如果是六位，就生成大小为 6 的数组
        char[] numbers = new char[6];
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = SYMBOLS.charAt(RANDOM.nextInt(SYMBOLS.length()));
        }
        return new String(numbers);
    }

    /**
     *
     * @param to      收件人
     * @return "404"        邮箱没有发送成功
     *         邮箱六位验证码  邮箱发送成功
     */
    public String sendVerCodeMail(String to)
    {
        String vercode=generateVerCode();
        String content="尊敬的"+to+",您好:\n"
                + "\n本次请求的邮件验证码为:" + vercode + ",本验证码 5 分钟内效，请及时输入。（请勿泄露此验证码）\n"
                + "\n如非本人操作，请忽略该邮件。\n(这是一封通过自动发送的邮件，请不要直接回复）";
        int sevcode=mailService.sendSimpleMall(to, "邮箱验证码", content);
        return (sevcode==-1)?"404":vercode;
    }
}
