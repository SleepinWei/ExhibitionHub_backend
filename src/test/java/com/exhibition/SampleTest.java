package com.exhibition;

import com.exhibition.controller.ExhibitionController;
import com.exhibition.service.IExToBeReviewedService;
import com.exhibition.mapper.UserMapper;
import com.exhibition.entity.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import java.sql.Time;
import java.sql.Date;
import com.exhibition.entity.ExhibitionReview;
import com.exhibition.entity.Exhibition;

import com.exhibition.service.IExToBeReviewedService;
import com.exhibition.service.IExService;
import org.springframework.beans.BeanUtils;

import org.springframework.web.bind.annotation.*;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class SampleTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void testSelect() {
        System.out.println("----------- method test --------------");
        List<User> userList = userMapper.selectList(null);
        Assert.assertEquals(2, userList.size());
        userList.forEach(System.out::println);
    }

    public String alterExInfo(ExhibitionReview exhibitionReview) {
        // TODO
        System.out.println("alterExInfo");
        System.out.println(exhibitionReview);
        if (exToBeReviewedService.saveOrUpdate(exhibitionReview)) {
            System.out.println("true");
        } else {
            System.out.println("false");
        }
        return "success";
    }

    @Autowired
    private IExToBeReviewedService exToBeReviewedService;

    @Autowired
    private IExService exService;

    public String addEx(@RequestBody ExhibitionReview exhibitionReview) {
        // add a new exhibition
        if (exhibitionReview.getId() == 0) {
            System.out.println("addEx");
            System.out.println(exhibitionReview);
            exToBeReviewedService.save(exhibitionReview);
        } else {
            System.out.println("id is not 0, Exhibition already exists");
        }

        return "success";
    }

    public String auditExPass(@RequestParam(name = "id") Integer id) {
        ExhibitionReview exPassTmp = exToBeReviewedService.getById(id);
        Exhibition exPass = new Exhibition();
        BeanUtils.copyProperties(exPassTmp, exPass);
        exService.saveOrUpdate(exPass);
        exToBeReviewedService.removeById(id);
        return "success";
    }

    @Test
    public void testSecondTable() {
        ExhibitionReview test = new ExhibitionReview(0, "【北京】国际安徒生奖50周年世界插画大展", "北京王府井银泰in88 B2展厅",
                "1000RMB",
                "不知道什么传媒", Date.valueOf("2023-04-28"), Date.valueOf("2023-04-28"),
                Time.valueOf("09:00:00"), Time.valueOf("20:00:00"), "北京市东城区王府井大街88号", "你说的对，但是原神是...",
                "https://bilibili.com", "images/2.webp", false);

        // ExhibitionController exhibitionController = new ExhibitionController();
        // exhibitionController.addEx(test);
        addEx(test);
        auditExPass(3);
        System.out.println("success");
    }
}
