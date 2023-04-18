package com.exhibition.controller;

import com.exhibition.entity.Exhibition;
import com.exhibition.entity.Tag;
import com.exhibition.service.IExService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class InfoController {

    @Autowired
    IExService exService;

    @PostMapping("/alterinfo") // 增加展览信息
    public void addInfo(@RequestBody Exhibition exhibition) {
        // add a new exhibition
        exService.save(exhibition);
    }

//    @PostMapping("/alterinfo/{id}")
//    public void alterInfo(@PathVariable Integer id,Exhibition exhibition){
//        // alter info
//
//    }
}
