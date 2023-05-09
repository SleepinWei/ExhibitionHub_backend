package com.exhibition.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.exhibition.entity.Exhibition;
import com.exhibition.entity.ExhibitionToBeReviewed;
import com.exhibition.entity.ExhibitionUnchecked;
import com.exhibition.entity.derived.ExSearchResult;
import com.exhibition.service.IExService;
import com.exhibition.service.IExToBeReviewedService;
import com.fasterxml.jackson.core.sym.Name;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.exhibition.mapper.ExMapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;

import java.sql.Time;
import java.sql.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;

@RestController
@RequestMapping()
public class ExhibitionController {
    @Autowired
    private IExService exService;

    @GetMapping("/searchByKeyword")
    public List<Exhibition> searchByKeyword(@RequestParam(name = "querytext") String querytext) {
        List<Exhibition> searchResult = exService.searchByKeyword(querytext);

        return searchResult;
    }

    @GetMapping("/searchById")
    public Exhibition searchById(HttpServletRequest request, HttpServletResponse response,
            @RequestParam(name = "exId") Integer exId) {
        // test
        // Exhibition exInfo = new Exhibition(1,"2","3",
        // Date.valueOf("2001-01-01"),Date.valueOf("2001-03-03"),"Shanghai","sssssssssss\nsssssss","https://bilibili.com");
        Exhibition result = exService.getById(exId);
        if (result == null) {
            // The Exhibition is not found
            System.out.println("Exhibition Not Exist");

            response.setStatus(400);
            return new Exhibition();
        }

        System.out.print(result);
        return result;
    }

    @PostMapping("/addEx") // 增加展览信息
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

    @Autowired
    private IExToBeReviewedService exToBeReviewedService;

    @PostMapping("/alterExInfo")
    public String alterExInfo(@RequestBody ExhibitionToBeReviewed exhibitionToBeReviewed) {
        // TODO
        System.out.println("alterExInfo");
        System.out.println(exhibitionToBeReviewed);
        exToBeReviewedService.saveOrUpdate(exhibitionToBeReviewed);
        return "success";
    }

    @Autowired
    private ExMapper exMapper;

    @GetMapping("/getUncheckedEx") // 获取未审核的展览
    public List<ExhibitionToBeReviewed> selExhibitionUncheckeds() {
        List<ExhibitionToBeReviewed> searchResult = exToBeReviewedService.list();
        return searchResult;
    }

    @GetMapping("/getCheckedEx/{id}")
    public List<Exhibition> selExCheckedById(@RequestParam(name = "id") Integer exerId) {
        // List<Exhibition> result = exService.selectById(id);

    }

    @PostMapping("/audit/pass")
    public String auditExPass(Integer id) {
        ExhibitionToBeReviewed exPassTmp = exToBeReviewedService.getById(id);
        Exhibition exPass = new Exhibition();
        BeanUtils.copyProperties(exPassTmp, exPass);
        exService.saveOrUpdate(exPass);
        exToBeReviewedService.removeById(id);
        return "success";
    }

    // TODO : 空处理
    @PostMapping("/audit/refuse")
    public String auditExRefuse(Integer id) {
        if (exToBeReviewedService.removeById(id)) {
            System.out.println("refuse success");
        } else {
            System.out.println(id + ":refuse failed");
        }
        return "success";
    }

    // @GetMapping("/audit/view/{id}") // 获取未审核的展览
    // public ExhibitionToBeReviewed selExhibitionUncheckeds() {
    // ExhibitionToBeReviewed searchResult = exMapper.selectUnchecked();
    // return searchResult;
    // }
}
