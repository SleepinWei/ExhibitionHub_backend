package com.exhibition.controller;

import com.exhibition.entity.User;
import com.exhibition.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.util.DigestUtils;

import com.exhibition.service.MailVerCodeService;
import com.exhibition.entity.EmailVerification;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RestController
//@CrossOrigin(origins = "http://localhost:5173")
public class ResetPasswordController {
    @Autowired
    private MailVerCodeService mailVerCodeServie;

    @Autowired
    private UserMapper userMapper;

    //哈希表，email和EmailVerification进行对应
    private HashMap<String, EmailVerification> emailVerifications = new HashMap<>();

    @GetMapping("/reset/isExistByEmail/{email}")
    public boolean isExistByEmail(@PathVariable String email){//根据用户email查找用户信息
        User u=userMapper.searchByEmail(email);
        System.out.println(u);
        if(u==null)
            return false;
        return true;
    }


    /**
     * 发送验证码，将内容存储到哈希表中
     * @param email 收件方email
     */
    @GetMapping("/reset/sendVerCodeMail/{email}")
    public void sendVerCodeMail(@PathVariable String email){
        System.out.print(email);

        long timestamp = System.currentTimeMillis();//生成时间戳

        String vercode=mailVerCodeServie.sendVerCodeMail(email);//生成六位随机验证码并发送
        this.emailVerifications.put(email,new EmailVerification(email,vercode, timestamp, false));
    }

    /**
     * 查看验证码是否对应，包含查验验证码是否过期的功能
     * @param requestBody 有email和code
     * @return 验证码匹配   0
     *         验证码不匹配 -1
     *         验证码失效  -2
     */
    @PostMapping("/reset/isVerCodeRight")
    public int isVerCodeRight(@RequestBody Map<String, Object> requestBody)
    {
        String email = (String) requestBody.get("email");
        String code = (String) requestBody.get("code");

        EmailVerification emailVerification=this.emailVerifications.get(email);
        String rightcode=emailVerification.getCode();
        long pastTimestamp=emailVerification.getTimestamp();

        System.out.println(email);
        System.out.println(rightcode);
        System.out.println(code);

        //查看验证码是否正确
        if(rightcode!=null)
        {
            boolean isRight=rightcode.equals("404");
            boolean isEqual=code.equals(rightcode);
            if(isRight==false) {
                if(isEqual==true) {
                    long currentTimestamp = System.currentTimeMillis();
                    long timeDifference = currentTimestamp - pastTimestamp;
                    if (timeDifference <= 300000){
                        // 清除验证码和时间戳
                        emailVerification.setCode("404");
                        emailVerification.setTimestamp(0);
                        emailVerification.setVeri(true);
                        System.out.print("OK"+rightcode);
                        // 返回成功响应
                        return 0;
                    }

                    return -2;
                }
                return -1;
            }
            else {
                System.out.print("NO"+code);
                return -1;
            }
        }
        return -1;
    }

    /**
     * 重置密码
     * @param requestBody 有用户邮箱和用户重设的密码
     * @return 重设成功返回true，否则返回false
     */
    @PostMapping("/reset/resetPassword")
    public boolean resetPassword(@RequestBody Map<String, Object> requestBody)
    {
        String email = (String) requestBody.get("email");
        String password = (String) requestBody.get("password");

        EmailVerification emailVerification=this.emailVerifications.get(email);
        if(emailVerification.isVeri()){
            User u=userMapper.searchByEmail(email);
            if(u==null)
                return false;
            System.out.println(u);

            u.setPassword(password);
            int newuser=userMapper.updateById(u);
            System.out.println("  "+newuser);
            System.out.println(u);
            return true;
        }
        return false;
    }
}