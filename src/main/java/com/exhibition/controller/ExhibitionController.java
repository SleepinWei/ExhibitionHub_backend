package com.exhibition.controller;

import com.exhibition.entity.Exhibition;
import com.exhibition.entity.ExhibitionReview;
import com.exhibition.service.IExService;
import com.exhibition.service.IExToBeReviewedService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.exhibition.mapper.ExMapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;
import java.util.HashMap;
import java.util.Map;

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

    @Autowired
    private IExToBeReviewedService exToBeReviewedService;

    @PostMapping("/addEx") // 增加展览信息
    public String addEx(@RequestBody Map<String, Object> exhibitionReview) {
        // add a new exhibition
        if (exhibitionReview.getId() == 0) {
            System.out.println("addEx");
            System.out.println(exhibitionReview);
            exToBeReviewedService.save(exhibitionReview);
        } else {
            System.out.println("id is not 0, Exhibition already exists");
        }

        Integer user_id = parseInt(String.valueOf(exhibitionReview.get("user_id")));

        return "success";
    }

    @PostMapping("/alterExInfo")
    public String alterExInfo(@RequestBody ExhibitionReview exhibitionReview) {
        // TODO
        System.out.println("alterExInfo");
        System.out.println(exhibitionReview);
        exToBeReviewedService.saveOrUpdate(exhibitionReview);
        return "success";
    }

    @Autowired
    private ExMapper exMapper;

    @GetMapping("/getUncheckedEx") // 获取未审核的展览
    public List<ExhibitionReview> selExhibitionUncheckeds() {
        List<ExhibitionReview> searchResult = exToBeReviewedService.list();
        return searchResult;
    }

    @GetMapping("/getCheckedEx")
    public List<Exhibition> getCheckedEx(@RequestParam(name = "id") Integer id) {
        // List<Exhibition> result = exService.selectById(id);

        return null;
    }

    @PostMapping("/audit/pass")
    public String auditExPass(@RequestParam(name = "id") Integer id) {
        ExhibitionReview exPassTmp = exToBeReviewedService.getById(id);
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
