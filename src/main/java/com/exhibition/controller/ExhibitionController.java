package com.exhibition.controller;

import com.exhibition.entity.*;
import com.exhibition.entity.request_type.ExhibitionChange;
import com.exhibition.entity.response_type.*;
import com.exhibition.mapper.*;
import com.exhibition.service.IExService;

import com.exhibition.entity.ExhibitionReview;
import com.exhibition.entity.UserExRelation;
import com.exhibition.service.IExToBeReviewedService;

import com.exhibition.util.CookieUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;

import java.sql.Timestamp;
import java.util.List;

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
        List<Exhibition> searchResult = exMapper.searchByKeyword(querytext);

        return searchResult;
    }

    @GetMapping("/searchById")
    public ExhibitionTag searchById(HttpServletRequest request, HttpServletResponse response,
            @RequestParam(name = "exId") Integer exId) {
        // test
        // Exhibition exInfo = new Exhibition(1,"2","3",
        // Date.valueOf("2001-01-01"),Date.valueOf("2001-03-03"),"Shanghai","sssssssssss\nsssssss","https://bilibili.com");
        ExhibitionTag result = exService.selectAllInfoById(exId);
        if (result == null) {
            // The Exhibition is not found
            System.out.println("Exhibition Not Exist");

            response.setStatus(400);
            return new ExhibitionTag();
        }

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
    private ExTagMapper exTagMapper;

    @Autowired
    private ExToBeReviewedMapper exReviewMapper;

    @Autowired
    private ExReTagMapper exReTagMapper;

    @Autowired
    private UserExRelMapper userExRelMapper;

    @PostMapping("/addEx") // 增加展览信息
    public String addEx(HttpServletRequest request, HttpServletResponse response,
            @RequestBody ExhibitionReviewTag exhibitionReview) {
        // add a new exhibition
        exToBeReviewedService.save(exhibitionReview);
        Integer ex_review_id = exReviewMapper.getNextId();
        Integer user_id = Integer.parseInt(CookieUtil.getCookies(request).get("cookieAccount"));
        UserExRelation newRelation = new UserExRelation(user_id, -1, ex_review_id, new Timestamp(System.currentTimeMillis()),
                new Timestamp(System.currentTimeMillis()),
                false, "unfinished", "新增");
        userExRelMapper.insert(newRelation);

        // add tag records
        List<Tag> tags = exhibitionReview.getTag_list();
        for (Tag tag: tags) {
            ExReTag relation = new ExReTag(0, ex_review_id, tag.getId());
            exReTagMapper.updateById(relation);
        }

        return "success";
    }

    @PostMapping("/alterExInfo")
    public String alterExInfo(HttpServletRequest request, HttpServletResponse response,
            @RequestBody ExhibitionReviewTag exhibitionTag) {
        System.out.println("alterExInfo");
        Integer ex_id = exhibitionTag.getId();
        ExhibitionReview exhibitionReview = new ExhibitionReview();
        exhibitionReview = exhibitionTag;
        exhibitionReview.setId(0);
        exReviewMapper.insert(exhibitionReview);

        Integer ex_review_id = exReviewMapper.getNextId();
        Integer user_id = Integer.parseInt(CookieUtil.getCookies(request).get("cookieAccount"));
        Timestamp current = new Timestamp(System.currentTimeMillis());
        UserExRelation newreview = new UserExRelation(user_id, ex_id, ex_review_id, current,current, Boolean.FALSE,
                "unfinished", "修改");
        userExRelMapper.insert(newreview);

        // add tag records
        List<Tag> tags = exhibitionTag.getTag_list();
        for (Tag tag: tags) {
            ExReTag relation = new ExReTag(0, ex_review_id, tag.getId());
            exReTagMapper.insert(relation);
        }

        return "success";
    }

    @GetMapping("/audit/pass")
    public String auditExPass(@RequestParam(name = "id") Integer ex_review_id) {
        System.out.println("/audit/pass");
        // 此id为ex_review_id
        ExhibitionReviewTag exPassTmp = exToBeReviewedService.selectFullInfoById(ex_review_id);

        UserExRelation relation = userExRelMapper.selectById(ex_review_id);

        Integer user_id = relation.getUser_id();
        Integer ex_id = relation.getEx_id();
        Exhibition exPass = new Exhibition();
        BeanUtils.copyProperties(exPassTmp, exPass);

        // 修改relation 状态
        relation.setIs_done(Boolean.TRUE);
        relation.setResult("pass");
        relation.setReview_date(new Timestamp(System.currentTimeMillis()));
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

        // 修改 exhibition_tag 关系表
        // 删除原有连接
        exTagMapper.deleteAllByExId(ex_id);
        // 添加新连接
        for(Tag tag:exPassTmp.getTag_list()){
            exTagMapper.insert(new ExTag(0,ex_id,tag.getId()));
        }

        return "success";
    }

    // TODO : 空处理
    @GetMapping("/audit/refuse")
    public String auditExRefuse(@RequestParam(name = "id") Integer ex_review_id) {
        UserExRelation relation = userExRelMapper.selectById(ex_review_id);
        relation.setIs_done(Boolean.TRUE);
        relation.setResult("refuse");
        relation.setReview_date(new Timestamp(System.currentTimeMillis()));
        userExRelMapper.updateById(relation);

        return "success";
    }

    @GetMapping("/audit/view")
    public ExhibitionReviewTag auditView(@RequestParam(name = "id") Integer ex_review_id) {
        ExhibitionReviewTag res = exToBeReviewedService.selectFullInfoById(ex_review_id);
        return res;
    }

    // TO DO
    @GetMapping("/delete")
    public int deleteEx(@RequestParam(name = "id") Integer ex_id) {
        int ret = exMapper.deleteById(ex_id);
        return ret;
    }

    @GetMapping("/getCheckedEx")
    public List<ExhibitioncheckedStatusBool> getCheckedEx(@RequestParam(name = "id") Integer user_id) {
        List<ExhibitioncheckedStatusBool> ret = exMapper.selectchecked(user_id);
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
        System.out.println("/admin/getUnchecked");
        System.out.println(ret);
        return ret;
    }

    @GetMapping("/admin/getChecked")
    public List<ExhibitioncheckedStatusBool> adminGetChecked() {
        List<ExhibitioncheckedStatusBool> ret = exMapper.selectCheckedAdmin();
        return ret;
    }

    // @GetMapping("/audit/view/{id}") // 获取未审核的展览
    // public ExhibitionToBeReviewed selExhibitionUncheckeds() {
    // ExhibitionToBeReviewed searchResult = exMapper.selectUnchecked();
    // return searchResult;
    // }

    @GetMapping("/ExhibitionMap/getAllVenue")
    List<VenueInfo> getAllVenueInfo(){
        List<VenueInfo> infos = exMapper.getAllVenueInfo();
        return infos;
    }

    @GetMapping("/getExPoster")
    List<Exhibition> getPoster(){
        List<Exhibition> infos = exMapper.getExInfo();
        return infos;
    }

    @GetMapping("/getOwnerId")
    Integer getOwnerId(@RequestParam(name = "ex_id") Integer ex_id){
        Integer owner_id = userExRelMapper.getOwnerId(ex_id);
        return owner_id;
    }
}
