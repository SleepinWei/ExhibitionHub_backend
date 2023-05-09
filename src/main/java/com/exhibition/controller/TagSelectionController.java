package com.exhibition.controller;

import com.exhibition.entity.Tag;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.exhibition.entity.Exhibition;
import com.exhibition.mapper.ExMapper;
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
@RequestMapping("/tagSelection")
public class TagSelectionController {
    @Autowired
    private ITagService tagservice;

    @Autowired
    private ExMapper exMapper;

    // 获取标签名列表
    @GetMapping("/getAllTag")
    public List<Tag> getTagName(){
        List<Tag> tags=tagservice.getTagName();
        System.out.println(tags);
        return tags;
    }

    @GetMapping("/getAllOrganizer")
    public List<String> getAllOrganizer(){
        List<String> organizers = exMapper.getAllOrganizers();
        return organizers;
    }

}
