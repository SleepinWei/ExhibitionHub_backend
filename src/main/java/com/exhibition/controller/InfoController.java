package com.exhibition.controller;

import com.exhibition.entity.Exhibition;
import com.exhibition.entity.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

public class InfoController {
    @PostMapping("/alterinfo") // 增加展览信息
    public void addInfo(Exhibition exhibition) {
        ;
    }
}
