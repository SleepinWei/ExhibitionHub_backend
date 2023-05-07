package com.exhibition.controller;

import com.exhibition.entity.SubExhibition;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        String pro="北京市";
        String ci="北京市";
        String ar="东城区";
        String ve="";
        String tag="";

        CalenderController controller=new CalenderController();
        List<SubExhibition> ex=controller.calendarSelect(userid,src,dst,ve,tag,pro,ci,ar);
        System.out.println(ex);
    }

}
