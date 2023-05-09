package com.exhibition.controller;

import com.exhibition.entity.Tag;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.exhibition.entity.Exhibition;
import com.exhibition.service.ITagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: JudyLou
 * @Date: 2023/5/9 14:52
 */
@Controller
@RestController
@RequestMapping()
public class TagSelectionController {
    @Autowired
    private ITagService tagservice;

    // 获取标签名列表
    @GetMapping("/tagSelection/getAllTag")
    public List<Tag> getTagName(){
        List<Tag> tags=tagservice.getTagName();
        System.out.println(tags);
        return tags;
    }


}
