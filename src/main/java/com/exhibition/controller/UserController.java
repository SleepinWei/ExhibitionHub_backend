package com.exhibition.controller;

import com.exhibition.entity.User;
import com.exhibition.service.IUserService;
import com.exhibition.service.impl.UserServiceImpl;
import org.springframework.web.bind.annotation.RequestMapping;

import com.exhibition.mapper.UserMapper;
import com.exhibition.util.Result;
import com.exhibition.util.ResultUtil;
import com.exhibition.service.impl.UserServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.exhibition.service.MailVerCodeService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartFile;
import com.exhibition.util.FileUploadUtil;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author
 * @since 2023-04-08
 */
@Controller
@RestController
@CrossOrigin // 跨域访问
@ResponseBody
public class UserController {
    @Autowired
    IUserService userService; // = new UserServiceImpl();

    @PostMapping("/login")
    public Result login(@RequestParam String idOrEmail, @RequestParam String password// ){
            , HttpSession session, HttpServletRequest request, HttpServletResponse response) {
        // public Result login(@RequestParam String account, @RequestParam String
        // password){
        String msg = userService.loginService(idOrEmail, password, session, request, response);

        if (("Success!This is a regular user.").equals(msg)) {
            return ResultUtil.regularUser("登录成功！即将跳转到普通用户界面...");
        } 
        else if (("Success!This is an administrator.").equals(msg)) {
            return ResultUtil.administrator("登录成功！即将跳转到管理员界面...");
        }
        else if (("Success!This is a museum.").equals(msg)) {
            return ResultUtil.museum("登录成功！即将跳转到博物馆界面...");
        } 
        else {
            return ResultUtil.error(msg);
        }
    }

    @RequestMapping("/logout")
    public Result logout(HttpServletRequest request, HttpServletResponse response) {
        String msg = userService.logoutService(request, response);
        if (("SUCCESS").equals(msg)) {
            return ResultUtil.success("推出账户成功");
        } else {
            return ResultUtil.error(msg);
        }
    }

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/user/find/{id}")
    public User query(@PathVariable int id){//根据用户id查找用户信息
        User u=userMapper.selectById(id);
        return u;
    }

     @PutMapping("/user")//更新用户基本信息
    public boolean update(@RequestBody User user){
        User old=userMapper.selectById(user.getId());
        System.out.println("bug+"+user.compare(old));
        if(user.compare(old)){
            return false;
        } else {
            userMapper.updateById(user);
            return true;
        }
    }

    @PutMapping("/user/change") // 更新用户密码
    public User changepw(@RequestBody User user) {
        int newuser = userMapper.updateById(user);
        return userMapper.selectById(newuser);
    }

    @Autowired
    private MailVerCodeService mailVerCodeServie;

    private String vercode = "";

    @GetMapping("/user/sendVerCodeMail/{email}")
    public String sendVerCodeMail(@PathVariable String email) {
        System.out.print(email);
        this.vercode = mailVerCodeServie.sendVerCodeMail(email);
        System.out.print("\nvercode is" + vercode);
        return vercode;
    }

    @PostMapping("/test/upload")//上传头像
    public String upload(@RequestParam("file") MultipartFile multipartFile,@RequestParam("uid") int uid) {
        // replaceAll 用来替换windows中的\\ 为 /
        System.out.println("uid="+uid);
        return FileUploadUtil.upload(multipartFile,uid).replaceAll("\\\\", "/");
    }
    @GetMapping("/user/load_avatar/{id}")//根据Id加载头像，返回二进制数据
    public byte[] loadAvatar(@PathVariable int id){
        System.out.println("this+"+FileUploadUtil.load(id));
        return FileUploadUtil.load(id);
    }
}
