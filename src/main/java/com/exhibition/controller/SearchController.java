package com.exhibition.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.exhibition.entity.Exhibition;
import com.exhibition.entity.derived.ExSearchResult;
import com.exhibition.service.IExService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class SearchController {
    @Autowired
    private IExService exService;

    @GetMapping("/search")
    public ExSearchResult search(@RequestParam(name = "querytext") String querytext){
        Exhibition searchResult = exService.getOne(
                new QueryWrapper<Exhibition>()
                        .select("name,location,begin_date,end_date")
                        .eq("name",querytext)
        );
        ExSearchResult result = new ExSearchResult(
            searchResult.getName(),
            searchResult.getLocation(),
            searchResult.getBegin_date(),
            searchResult.getEnd_date()
        );
        return result;
    }
}
