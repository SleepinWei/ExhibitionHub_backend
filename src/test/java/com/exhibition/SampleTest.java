package com.exhibition;

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
import com.exhibition.entity.ExhibitionToBeReviewed;
import com.exhibition.entity.Exhibition;

import com.exhibition.service.IExToBeReviewedService;
import com.exhibition.service.IExService;

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

    @Autowired
    private IExToBeReviewedService exToBeReviewedService;

    public String alterExInfo(ExhibitionToBeReviewed exhibitionToBeReviewed) {
        // TODO
        System.out.println("alterExInfo");
        System.out.println(exhibitionToBeReviewed);
        if (exToBeReviewedService.saveOrUpdate(exhibitionToBeReviewed)) {
            System.out.println("true");
        } else {
            System.out.println("false");
        }
        return "success";
    }

    @Autowired
    private IExService exService;

    // @PostMapping("/addEx") // 增加展览信息
    public String addEx(@RequestBody Exhibition exhibition) {
        // add a new exhibition
        if (exhibition.getId() == 0) {
            System.out.println("addEx");
            System.out.println(exhibition);
            exService.save(exhibition);
        } else {
            System.out.println("id is not 0, Exhibition already exists");
        }

        return "success";
    }

    @Test
    public void testSecondTable() {
        Exhibition test = new Exhibition(4, "【北京】国际安徒生奖50周年世界插画大展", "北京王府井银泰in88 B2展厅",
                "300RMB",
                "不知道什么传媒", Date.valueOf("2023-04-28"), Date.valueOf("2023-04-28"), Date.valueOf("2023-10-31"),
                Time.valueOf("09:00:00"), Time.valueOf("20:00:00"), "北京市东城区王府井大街88号", "你说的对，但是原神是...",
                "https://bilibili.com", "images/2.webp", false);
        addEx(test);
        System.out.println("success");
    }
}
