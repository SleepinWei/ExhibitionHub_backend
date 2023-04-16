package com.exhibition.controller;

import com.exhibition.entity.Exhibition;
import com.exhibition.service.IExService;
import com.exhibition.service.impl.ExServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;

@RestController
@RequestMapping("/exhibition")
public class ExhibitionController {
    @Autowired
    private IExService exService = new ExServiceImpl();

    @GetMapping("/{exId}")
    public Exhibition getExhibitionInfo(@PathVariable Integer exId){
        Exhibition exInfo = new Exhibition(1,"2","3", Date.valueOf("2001-01-01"),Date.valueOf("2001-03-03"),"Shanghai","sssssssssss\nsssssss","https://bilibili.com");
        System.out.println(exId);
        return exInfo;
    }
}
