package com.exhibition.controller;

import com.exhibition.entity.Exhibition;
import com.exhibition.entity.SubExhibition;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Calendar;
import java.util.List;

@RestController
@RequestMapping(value = "test")
public class TestController {

//http://localhost:8080/test?nickname=zhangsan
    //@RequestMapping("/calendar")
    @RequestMapping
    public void hello() {
        int userid=2;
        String src="2023-04-01";
        String dst="2024-04-30";
        String vuene="国家典籍";

        CalenderController controller=new CalenderController();
        List<SubExhibition> ex=controller.calendarByVenue(userid,src,dst,vuene);
        System.out.println(ex);
    }

}
