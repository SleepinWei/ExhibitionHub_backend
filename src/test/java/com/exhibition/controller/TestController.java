package com.exhibition.controller;

import org.springframework.beans.factory.annotation.Autowired;
import com.exhibition.service.IExService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

// @RestController
// @RequestMapping(value = "test")
// public class TestController {

// //http://localhost:8080/test?nickname=zhangsan
//     @RequestMapping
//     public String hello(String nickname ) {
//         return "hello "+nickname;
//     }

// }

@RestController
@RequestMapping(value = "/test", produces = "application/json; charset=UTF-8")
public class TestController {

    @Autowired
    private IExService exService;

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public String testGet() {
        return "success";
    }

}