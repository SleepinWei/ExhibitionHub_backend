package com.exhibition.service.impl;

import com.exhibition.entity.User;
import com.exhibition.mapper.UserMapper;
import com.exhibition.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.exhibition.util.CookieUtil;
import com.exhibition.util.NumericUtil;
import lombok.RequiredArgsConstructor;
import org.mockito.internal.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 
 * @since 2023-04-08
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public String loginService(String idOrEmail,String password// ){
            , HttpSession session, HttpServletRequest request, HttpServletResponse response) {

        // 判断输入是否为空
        if ("".equals(idOrEmail) || "".equals(password)) {
            return "请输入账号或密码！";
        }
        else{
            User user;
            if(NumericUtil.isNumeric(idOrEmail)){// 用户登录时输入的是id
                user = userMapper.searchById(Integer.valueOf(idOrEmail));
            }
            else{// 用户登录时输入的是邮箱
                user= userMapper.searchByEmail(idOrEmail);
            }
            // 判断账号是否存在
            if (null == user) {
                return "账号不存在，请检查后重试！";
            } else {
                // 验证密码是否正确
                if (!password.equals(user.getPassword())) {
                    return "账号或密码错误，请检查后重试！";
                } else {
                    // 设置cookie
                    int expire = 3 * 24 * 60 * 60; // cookie过期时间为3天
                    CookieUtil.setCookie(response, "cookieAccount", user.getId().toString(), expire);
                    CookieUtil.setCookie(response, "cookieName", user.getUsername(), expire);
                    // 判断用户角色，返回前端跳转不同页面
                    if ("普通用户".equals(user.getRole())) {
                        return "Success!This is a regular user.";
                    } 
                    else if ("管理员".equals(user.getRole())) {
                        return "Success!This is an administrator.";
                    } 
                    else if ("博物馆".equals(user.getRole())) {
                        return "Success!This is a museum.";
                    } 
                    else {
                        return "SUCCESS";
                    }
                }
            }
        }

    }

    @Override
    public String logoutService(HttpServletRequest request, HttpServletResponse response) {
        CookieUtil.clear(request, response);
        return "SUCCESS";
    }
}
