package com.exhibition.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "test")
public class TestController {

    //http://localhost:8080/test?nickname=zhangsan
    @RequestMapping
    public String hello(String nickname ) {
        return "hello "+nickname;
    }

}
