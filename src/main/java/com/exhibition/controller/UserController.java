package com.exhibition.controller;


import com.exhibition.entity.User;
import com.exhibition.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.exhibition.service.MailVerCodeService;

import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 
 * @since 2023-04-08
 */
@RestController
@CrossOrigin//跨域访问
public class UserController {
    @Autowired
    private UserMapper userMapper;
    @GetMapping("/user/find/{id}")
    public User query(@PathVariable int id){//根据用户id查找用户信息
        User u=userMapper.selectById(id);
        System.out.println(u);
        return u;
    }

    @PutMapping("/user")//更新用户基本信息
    public boolean update(@RequestBody User user){
        User old=userMapper.selectById(user.getId());
        System.out.println("user"+user);
        System.out.println("old"+old);
        System.out.println(user.compare(old));
        if(user.compare(old)){
            return false;
        }
        else{
            userMapper.updateById(user);
            return true;
        }
    }

    @PutMapping("/user/change")//更新用户密码
    public User changepw(@RequestBody User user){
        int newuser=userMapper.updateById(user);
        return userMapper.selectById(newuser);
    }


    @Autowired
    private MailVerCodeService mailVerCodeServie;

    private String vercode="";
    @GetMapping("/user/sendVerCodeMail/{email}")
    public String sendVerCodeMail(@PathVariable String email){
        System.out.print(email);
        this.vercode=mailVerCodeServie.sendVerCodeMail(email);
        System.out.print("\nvercode is"+vercode);
        return vercode;
    }
}
