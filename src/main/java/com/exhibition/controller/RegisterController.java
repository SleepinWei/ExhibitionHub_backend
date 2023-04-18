package com.exhibition.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.exhibition.entity.User;
import com.exhibition.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.*;

import com.exhibition.service.MailVerCodeService;
import com.exhibition.entity.EmailVerification;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 
 * @since 2023-04-08
 */

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class RegisterController {

    @Autowired
    private MailVerCodeService mailVerCodeServie;

    @Autowired
    private UserMapper userMapper;

    private HashMap<String, EmailVerification> emailVerifications = new HashMap<>();

    @GetMapping("/api/sendRisVerCodeMail/{email}")
    public String sendVerCodeMail(@PathVariable String email) {
        System.out.print(email);
        QueryWrapper queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("email",email);
        if(userMapper.selectCount(queryWrapper)>0){ //邮箱已注册，确保邮箱唯一
            return "400";
        }

        long timestamp = System.currentTimeMillis();//生成时间戳

        String vercode = mailVerCodeServie.sendVerCodeMail(email);//生成六位随机验证码并发送，若发送失败返回404
        this.emailVerifications.put(email, new EmailVerification(email, vercode, timestamp, false));
        return vercode;
    }

    /**
     * @param requestBody
     * @return 验证码匹配   0
     * 验证码不匹配 -1
     * 验证码失效  -2
     */
    public int isVerCodeRight(@RequestBody Map<String, Object> requestBody) {
        String email = (String) requestBody.get("email");
        String code = (String) requestBody.get("code");

        EmailVerification emailVerification = this.emailVerifications.get(email);
        String rightcode = emailVerification.getCode();
        long pastTimestamp = emailVerification.getTimestamp();

        System.out.println(email);
        System.out.println(rightcode);
        System.out.println(code);

        //查看验证码是否正确
        if (rightcode != null) {
            boolean isRight = rightcode.equals("404");
            boolean isEqual = code.equals(rightcode);
            if (isRight == false) {
                if (isEqual == true) {
                    long currentTimestamp = System.currentTimeMillis();
                    long timeDifference = currentTimestamp - pastTimestamp;
                    if (timeDifference <= 300000) {
                        // 清除验证码和时间戳
                        emailVerification.setCode("404");
                        emailVerification.setTimestamp(0);
                        emailVerification.setVeri(true);
                        System.out.print("OK" + rightcode);
                        // 返回成功响应
                        return 0;
                    }

                    return -2;
                }
                return -1;
            } else {
                System.out.print("NO" + code);
                return -1;
            }
        }
        return -1;
    }

    @PostMapping("/register")
    public boolean register(@RequestBody Map<String, Object> requestBody) {
        System.out.println(requestBody);
        String email = (String) requestBody.get("email");
        String username = (String) requestBody.get("username");
        String password = (String) requestBody.get("password");
//        User user = new User(username, password,);
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setRole("普通用户");
        user.setSex("男");
        int isright=isVerCodeRight(requestBody);
        EmailVerification emailVerification = this.emailVerifications.get(email);
        if (emailVerification.isVeri()) {
            userMapper.insert(user);
            return true;
        }
        return false;
    }
}

