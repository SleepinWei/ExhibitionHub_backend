package com.exhibition.controller;


import com.exhibition.entity.User;
import com.exhibition.service.IUserService;
import com.exhibition.service.impl.UserServiceImpl;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 
 * @since 2023-04-08
 */
@Controller
@RequestMapping("/exhibition/user")
public class UserController {
    IUserService service = new UserServiceImpl();

    public void test(){
        service.save(new User());
    }
}
