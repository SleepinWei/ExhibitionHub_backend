package com.exhibition.controller;

import com.exhibition.entity.Exhibition;
import com.exhibition.entity.Tag;
import com.exhibition.mapper.ExMapper;
import com.exhibition.service.IExService;

import org.apache.catalina.connector.Response;
import org.junit.Test;
import com.exhibition.entity.ExhibitionReview;
import com.exhibition.entity.ExhibitionUncheckedAdmin;
import com.exhibition.entity.Exhibitionchecked;
import com.exhibition.entity.UserExRelation;
import com.exhibition.mapper.ExToBeReviewedMapper;
import com.exhibition.mapper.UserExRelMapper;
import com.exhibition.service.IExService;
import com.exhibition.service.IExToBeReviewedService;

import com.exhibition.util.CookieUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.exhibition.mapper.ExMapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.Date;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.BeanUtils;

@RestController
@RequestMapping()
public class ExhibitionController {
    @Autowired
    private IExService exService;

    @Autowired
    private ExMapper exMapper;

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

    @GetMapping("/searchTagById")
    public List<Tag> searchTagById(@RequestParam(name = "ex_id") Integer ex_id) {
        List<Tag> result = exMapper.findTagByExId(ex_id);
        return result;
    }

    @Autowired
    private IExToBeReviewedService exToBeReviewedService;

    @Autowired
    private ExToBeReviewedMapper exReviewMapper;

    @Autowired
    private UserExRelMapper userExRelMapper;

    @PostMapping("/addEx") // 增加展览信息
    public String addEx(HttpServletRequest request, HttpServletResponse response,
            @RequestBody ExhibitionReview exhibitionReview) {
        // add a new exhibition
        exToBeReviewedService.save(exhibitionReview);
        Integer ex_review_id = exReviewMapper.getNextId();
        Integer user_id = Integer.parseInt(CookieUtil.getCookies(request).get("cookieAccount"));
        UserExRelation newRelation = new UserExRelation(user_id, -1, ex_review_id, new Date(System.currentTimeMillis()),
                false, "unfinished", "新增");
        userExRelMapper.insert(newRelation);

        return "success";
    }

    @PostMapping("/alterExInfo")
    public String alterExInfo(HttpServletRequest request, HttpServletResponse response,
            @RequestBody ExhibitionReview exhibitionReview) {
        System.out.println("alterExInfo");
        Integer ex_id = exhibitionReview.getId();
        exhibitionReview.setId(0);
        exReviewMapper.insert(exhibitionReview);

        Integer ex_review_id = exReviewMapper.getNextId();
        Integer user_id = Integer.parseInt(CookieUtil.getCookies(request).get("cookieAccount"));
        Date current = new Date(System.currentTimeMillis());
        UserExRelation newreview = new UserExRelation(user_id, ex_id, ex_review_id, current, Boolean.FALSE,
                "unfinished", "修改");
        userExRelMapper.insert(newreview);

        return "success";
    }

    @GetMapping("/audit/pass")
    public String auditExPass(@RequestParam(name = "id") Integer ex_review_id) {
        // 此id为ex_review_id
        ExhibitionReview exPassTmp = exToBeReviewedService.getById(ex_review_id);
        UserExRelation relation = userExRelMapper.selectById(ex_review_id);
        Integer user_id = relation.getUser_id();
        Integer ex_id = relation.getEx_id();
        Exhibition exPass = new Exhibition();
        BeanUtils.copyProperties(exPassTmp, exPass);

        // 修改relation 状态
        relation.setIs_done(Boolean.TRUE);
        relation.setResult("pass");
        userExRelMapper.updateById(relation);

        if (ex_id == -1) {
            // 新增
            exPass.setId(0);
            exMapper.insert(exPass);
        } else {
            // 修改
            exPass.setId(ex_id);
            exMapper.updateById(exPass);
        }
        return "success";
    }

    // TODO : 空处理
    @GetMapping("/audit/refuse")
    public String auditExRefuse(@RequestParam(name = "id") Integer ex_review_id) {
        UserExRelation relation = userExRelMapper.selectById(ex_review_id);
        relation.setIs_done(Boolean.TRUE);
        relation.setResult("refuse");
        userExRelMapper.updateById(relation);

        return "success";
    }

    @GetMapping("/audit/view")
    public ExhibitionReview auditView(@RequestParam(name = "id") Integer ex_review_id) {
        ExhibitionReview result = exReviewMapper.selectById(ex_review_id);
        return result;
    }

    // TO DO
    @GetMapping("/delete")
    public int deleteEx(@RequestParam(name = "id") Integer ex_id) {
        int ret = exMapper.deleteById(ex_id);
        return ret;
    }

    @GetMapping("/getCheckedEx")
    public List<Exhibitionchecked> getCheckedEx(@RequestParam(name = "id") Integer user_id) {
        List<Exhibitionchecked> ret = exService.selectchecked(user_id);
        return ret;
    }

    @GetMapping("/getUncheckedEx") // 获取未审核的展览
    public List<ExhibitionUncheckedAdmin> selExhibitionUncheckeds(@RequestParam(name = "id") Integer user_id) {
        List<ExhibitionUncheckedAdmin> searchResult = exReviewMapper.getUnchecked(user_id);
        return searchResult;
    }

    @GetMapping("/admin/getUnchecked")
    public List<ExhibitionUncheckedAdmin> getUnCheckedEx() {
        List<ExhibitionUncheckedAdmin> ret = exReviewMapper.getUncheckedAdmin();
        return ret;
    }

    @GetMapping("/admin/getChecked")
    public List<Exhibitionchecked> adminGetChecked() {
        List<Exhibitionchecked> ret = exService.selectCheckedAdmin();
        return ret;
    }

    // @GetMapping("/audit/view/{id}") // 获取未审核的展览
    // public ExhibitionToBeReviewed selExhibitionUncheckeds() {
    // ExhibitionToBeReviewed searchResult = exMapper.selectUnchecked();
    // return searchResult;
    // }
}
