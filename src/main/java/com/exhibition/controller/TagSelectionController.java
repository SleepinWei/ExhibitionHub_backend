package com.exhibition.controller;

import com.exhibition.entity.*;
import com.exhibition.mapper.ExTagMapper;
import com.exhibition.mapper.SubMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.exhibition.mapper.ExMapper;
import com.exhibition.mapper.TagMapper;
import com.exhibition.service.IExService;
import com.exhibition.service.ITagService;
import com.exhibition.util.NumericUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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
    private IExService exService;

    @Autowired
    private ExMapper exMapper;

    @Autowired
    private ExTagMapper exTagMapper;

    @Autowired
    private TagMapper tagMapper;

    @Autowired
    private SubMapper subMapper;

    @GetMapping("/getAllTags")
    public List<Tag> getAllTags(){
        return tagMapper.getAllTags();
    }

    // 获取主办方列表
    @GetMapping("/getAllOrganizer")
    public List<String> getAllOrganizer() {
        List<String> organizers = exMapper.getAllOrganizers();
        return organizers;
    }

    // 根据所选内容返回展览内容
    @PostMapping("/searchByData")
    public List<Exhibition> selectEx(/*
                                      * @PathVariable String query,
                                      * 
                                      * @PathVariable String src, @PathVariable String dst,
                                      * 
                                      * @PathVariable String organizer, @PathVariable String tags,
                                      * 
                                      * @PathVariable String province, @PathVariable String , @PathVariable String
                                      * area){
                                      */
            @RequestBody Map<String, Object> requestBody) {
        String query = (String) requestBody.get("query");
        String src = (String) requestBody.get("src");
        String dst = (String) requestBody.get("dst");
        String venue = (String) requestBody.get("venue");
        String tags = (String) requestBody.get("tags");
        String province = (String) requestBody.get("province");
        String city = (String) requestBody.get("city");
        String area = (String) requestBody.get("area");

        if (src == null || dst == null || venue == null || tags == null || province == null || city == null
                || area == null) {
            return new ArrayList<Exhibition>();
        }

        venue = (venue.equals("null")) ? "" : venue;
        province = (province.equals("null")) ? "" : province;
        city = (city.equals("null")) ? "" : city;
        area = (area.equals("null")) ? "" : area;
        tags = (tags.equals("-1")) ? "" : tags;

        List<searchScore> scoreList = new ArrayList<searchScore>();
        // 根据时间和关键词的筛选结果
        List<Exhibition> ExhibitionList = subMapper.getSearchResult(src, dst);

        if (query != "") {
            // 内容匹配相似度（按照score降序排列）
            scoreList = subMapper.getSearchScore(query);
            List<Exhibition> temp = new ArrayList<Exhibition>();

            for (int i = 0; i < scoreList.size(); i++) {
                searchScore ex_score = scoreList.get(i);
                if (ex_score.getScore() <= 0) {
                    break;
                } // 由于是降序排列，后面都是0不需要遍历了
                else {
                    int id = ex_score.getId();
                    for (int j = 0; j < ExhibitionList.size(); j++) {
                        if (ExhibitionList.get(j).getId() == id) {// 找到id对应的展览
                            temp.add(ExhibitionList.get(j));
                            break;
                        }
                    }
                    // temp.add(ExhibitionList.get(id - 1));
                } // 加到temp里
            }
            ExhibitionList = temp;
        }

        // 再按其它条件筛选
        if (!venue.isEmpty()) {// 按主办方
            selectByVenue(ExhibitionList, venue);
        }
        if (!province.isEmpty() && !city.isEmpty() && !area.isEmpty()) {// 按省市区
            selectByLocation(ExhibitionList, province, city, area);
        }
        if (!tags.isEmpty()) {// 按标签
            selectByTag(ExhibitionList, tags);
        }

        // System.out.println("resultsize"+subExhibitons.size());
        return ExhibitionList;
    }

    // 按标签查询
    // 注意：此处可能传入多个tag，要求传入string类型，每个tag之间用空格隔开
    private List<Exhibition> selectByTag(List<Exhibition> ExhibitionList, String tags) {
        // 分割多个标签
        Set<Integer> tagids = new HashSet<Integer>();
        for (String s : tags.split(" ")) {
            if (s != null && !s.equals("") && NumericUtil.isNumeric(s)) {
                tagids.add(Integer.parseInt(s));
            }
        }

        // 查询每一个现有项是否符合条件
        for (int i = 0; i < ExhibitionList.size(); i++) {
            Exhibition exhibition = ExhibitionList.get(i);
            Integer exId = exhibition.getId();
            List<Integer> Tags = exTagMapper.selectAllTagids(exId);// 获取某展览的所有tagid
            boolean remove = true;
            for (Integer tag : Tags) {
                if (tagids.contains(tag)) { 
                    remove = false;
                    break;
                }
            }
            if (remove) {
                // 换到末尾进行删除，降低复杂度
                Collections.swap(ExhibitionList, i, ExhibitionList.size() - 1);
                ExhibitionList.remove(ExhibitionList.size() - 1);
                i--;
            }
        }
        return ExhibitionList;
    }

    // 按展馆查询
    private List<Exhibition> selectByVenue(List<Exhibition> ExhibitionList, String venue) {
        for (int i = 0; i < ExhibitionList.size(); i++) {
            Exhibition exhibition = ExhibitionList.get(i);
            if (!exhibition.getVenue_name().contains(venue)) {
                Collections.swap(ExhibitionList, i, ExhibitionList.size() - 1);
                ExhibitionList.remove(ExhibitionList.size() - 1);
                i--;
            }
        }
        return ExhibitionList;
    }

    // 按省市区查询
    private List<Exhibition> selectByLocation(List<Exhibition> ExhibitionList,
            String province, String city, String area) {
        for (int i = 0; i < ExhibitionList.size(); i++) {
            Exhibition exhibition = ExhibitionList.get(i);
            if (!exhibition.getProvince().equals(province) || !exhibition.getCity().equals(city)
                    || !exhibition.getArea().equals(area)) {
                Collections.swap(ExhibitionList, i, ExhibitionList.size() - 1);
                ExhibitionList.remove(ExhibitionList.size() - 1);
                i--;
            }
        }
        return ExhibitionList;
    }
}
