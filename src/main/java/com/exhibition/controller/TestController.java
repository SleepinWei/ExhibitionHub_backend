package com.exhibition.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.exhibition.common.Result;
import com.exhibition.entity.Schedule;
import com.exhibition.entity.User;
import com.exhibition.service.IScheduleService;
import com.exhibition.service.IUserService;

@RestController
@RequestMapping(value = "test")
public class TestController {
    @Autowired
    private IUserService userService;

    @Autowired
    private IScheduleService scheduleService;

    @RequestMapping
    public String index() {
        return "hello world";
    }

    @GetMapping("list")
    public List<User> listUser() {
        return userService.list();
    }

    @GetMapping("schedule")
    public Result listSchedule() {
        List<Schedule> list = scheduleService.list();
        return Result.suc(list);
    }

}
