package com.exhibition.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.exhibition.entity.*;
import com.exhibition.entity.response_type.*;
import com.exhibition.mapper.*;
import com.exhibition.service.IExService;
import com.exhibition.service.IExToBeReviewedService;
import com.exhibition.util.Base64DecodedMultipartFile;
import com.exhibition.util.CookieUtil;
import com.exhibition.util.FileUploadUtil;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.naming.Name;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.util.Base64;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Base64.Decoder;
import org.springframework.util.ClassUtils;

import static java.lang.Integer.parseInt;

import java.io.*;

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

    private FileUploadUtil fileUploadUtil = new FileUploadUtil();

    @PostMapping("/addEx") // 增加展览信息
    public String addEx(HttpServletRequest request, HttpServletResponse response,
            @RequestBody Map<String, Object> requestBody) {

        Timestamp time = new Timestamp(System.currentTimeMillis());// 时间戳
        String savetime = "" + System.currentTimeMillis() / 1000;
        Integer user_id = Integer.parseInt(CookieUtil.getCookies(request).get("cookieAccount"));

        String file_base64 = (String) requestBody.get("file_base64");
        MultipartFile image = null;
        StringBuilder base64 = new StringBuilder("");
        if (file_base64 != null && !"".equals(file_base64)) {
            base64.append(file_base64);
            String[] baseStrs = base64.toString().split(",");
            Decoder decoder = Base64.getDecoder();
            byte[] b = new byte[0];
            b = decoder.decode(baseStrs[1]);
            for (int j = 0; j < b.length; ++j) {
                if (b[j] < 0) {
                    b[j] += 256;
                }
            }
            image = new Base64DecodedMultipartFile(b, baseStrs[0]);
        }

        String savepath = fileUploadUtil.upload(image, "static/images/" + savetime + "_", user_id).replaceAll("\\\\",
                "/");
        System.out.println(savepath);

        LinkedHashMap hashmap = (LinkedHashMap) requestBody.get("data");
        System.out.println(hashmap);

        hashmap.put("poster_url", "images/" + savetime + "_" + user_id + ".jpg");

        System.out.println(hashmap);

        String jSONstr = JSON.toJSONString(hashmap);
        System.out.println(jSONstr);

        ExhibitionReviewTag exhibitionReview = JSON.parseObject(jSONstr, ExhibitionReviewTag.class);
        System.out.println(exhibitionReview);

        // add a new exhibition
        exToBeReviewedService.save(exhibitionReview);
        Integer ex_review_id = exReviewMapper.getNextId();

        UserExRelation newRelation = new UserExRelation(user_id, -1, ex_review_id,
                time, time,
                false, "unfinished", "新增");
        userExRelMapper.insert(newRelation);

        // add tag records
        List<Tag> tags = exhibitionReview.getTag_list();
        if (tags.isEmpty()) {
            return "fail";
        }
        for (Tag tag : tags) {
            ExReTag relation = new ExReTag(0, ex_review_id, tag.getId());
            exReTagMapper.insert(relation);
        }

        return "success";
    }

    @PostMapping("/addEx/uploadPoster") // 上传海报
    public String upload(@RequestBody Map<String, Object> requestBody) {
        String file_base64 = (String) requestBody.get("file_base64");
        ExhibitionReviewTag exhibitionReview;
        Integer uid = parseInt(String.valueOf(requestBody.get("uid")));
        // replaceAll 用来替换windows中的\\ 为 /

        MultipartFile image = null;
        StringBuilder base64 = new StringBuilder("");
        if (file_base64 != null && !"".equals(file_base64)) {
            base64.append(file_base64);
            String[] baseStrs = base64.toString().split(",");
            Decoder decoder = Base64.getDecoder();
            byte[] b = new byte[0];
            b = decoder.decode(baseStrs[1]);
            for (int j = 0; j < b.length; ++j) {
                if (b[j] < 0) {
                    b[j] += 256;
                }
            }
            image = new Base64DecodedMultipartFile(b, baseStrs[0]);
        }
        return fileUploadUtil.upload(image, "static/images/", uid).replaceAll("\\\\", "/");
    }

    @PostMapping("/addEx/stash") // 文件暂存
    public String upload(@RequestParam("file") MultipartFile multipartFile) {
        // replaceAll 用来替换windows中的\\ 为 /
        return fileUploadUtil.upload(multipartFile, "static/images/", -1).replaceAll("\\\\", "/");
    }

    @PostMapping("/alterExInfo")
    public String alterExInfo(HttpServletRequest request, HttpServletResponse response,
            @RequestBody Map<String, Object> requestBody) {
        String savetime = "" + System.currentTimeMillis() / 1000;
        Integer user_id = Integer.parseInt(CookieUtil.getCookies(request).get("cookieAccount"));

        String file_base64 = (String) requestBody.get("file_base64");
        System.out.println("file_base64");
        System.out.println(file_base64);
        MultipartFile image = null;
        StringBuilder base64 = new StringBuilder("");
        if (file_base64 != null && !"null".equals(file_base64) && !"".equals(file_base64)) {
            System.out.println("not null");
            base64.append(file_base64);
            String[] baseStrs = base64.toString().split(",");
            Decoder decoder = Base64.getDecoder();
            byte[] b = new byte[0];
            b = decoder.decode(baseStrs[1]);
            for (int j = 0; j < b.length; ++j) {
                if (b[j] < 0) {
                    b[j] += 256;
                }
            }
            image = new Base64DecodedMultipartFile(b, baseStrs[0]);
        }
        LinkedHashMap hashmap = (LinkedHashMap) requestBody.get("data");

        // 上传文件
        if (image != null) {
            fileUploadUtil.upload(image, "static/images/" + savetime + "_", user_id).replaceAll("\\\\",
                    "/");
            hashmap.put("poster_url", "images/" + savetime + "_" + user_id + ".jpg");
        }

        String jSONstr = JSON.toJSONString(hashmap);
        System.out.println(jSONstr);

        ExhibitionReviewTag exhibitionTag = JSON.parseObject(jSONstr, ExhibitionReviewTag.class);
        System.out.println(exhibitionTag);

        System.out.println("alterExInfo");
        Integer ex_id = exhibitionTag.getId();
        Timestamp current = new Timestamp(System.currentTimeMillis());

        // 先查询是否有未通过审核的修改
        UserExRelation record = userExRelMapper.getExistingRecord(user_id, ex_id);
        if (record == null) {
            // 插入新纪录
            Integer ex_review_id = exReviewMapper.getNextId() + 1;
            UserExRelation newreview = new UserExRelation(user_id, ex_id, ex_review_id, current, current, Boolean.FALSE,
                    "unfinished", "修改");
            userExRelMapper.insert(newreview);

            ExhibitionReview exhibitionReview = new ExhibitionReview();
            exhibitionReview = exhibitionTag;
            exhibitionReview.setId(ex_review_id);
            exReviewMapper.insert(exhibitionReview);

            // add tag records
            List<Tag> tags = exhibitionTag.getTag_list();
            for (Tag tag : tags) {
                ExReTag relation = new ExReTag(0, ex_review_id, tag.getId());
                exReTagMapper.insert(relation);
            }
        } else {
            // 更新旧记录
            Integer ex_review_id = record.getEx_review_id();
            UserExRelation newreview = new UserExRelation(user_id, ex_id, ex_review_id, current, current, Boolean.FALSE,
                    "unfinished", "修改");

            newreview.setEx_review_id(record.getEx_review_id());
            userExRelMapper.updateById(newreview);

            ExhibitionReview exhibitionReview = new ExhibitionReview();
            exhibitionReview = exhibitionTag;
            exhibitionReview.setId(record.getEx_review_id());
            exReviewMapper.updateById(exhibitionReview);

            List<Tag> tags = exhibitionTag.getTag_list();
            // 删除原本的tag
            exReTagMapper.deleteTagByExReId(ex_review_id);
            for (Tag tag : tags) {
                ExReTag relation = new ExReTag(0, ex_review_id, tag.getId());
                exReTagMapper.insert(relation);
            }
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

        // System.out.println("这里出现了问题");
        // System.out.println(ex_id);

        if (ex_id == -1) {
            // 新增
            ex_id = exMapper.getNextId() + 1;
            exPass.setId(ex_id);
            exMapper.insert(exPass);
            // System.out.println(ex_id);
            // System.out.println(exPass);
        } else {
            // 修改
            exPass.setId(ex_id);
            exMapper.updateById(exPass);
        }

        // 修改 exhibition_tag 关系表
        // 删除原有连接
        exTagMapper.deleteAllByExId(ex_id);
        // 添加新连接
        for (Tag tag : exPassTmp.getTag_list()) {
            exTagMapper.insert(new ExTag(0, ex_id, tag.getId()));
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
    List<VenueInfo> getAllVenueInfo() {
        List<VenueInfo> infos = exMapper.getAllVenueInfo();
        return infos;
    }

    @GetMapping("/getExPoster")
    List<Exhibition> getPoster() {
        List<Exhibition> infos = exMapper.getExInfo();
        return infos;
    }

    @GetMapping("/getOwnerId")
    Integer getOwnerId(@RequestParam(name = "ex_id") Integer ex_id) {
        Integer owner_id = userExRelMapper.getOwnerId(ex_id);
        return owner_id;
    }

    @GetMapping("/ex/delete")
    void deleteByExId(@RequestParam(name = "ex_id") Integer ex_id){
        Integer status = exMapper.deleteById(ex_id);
    }
}
