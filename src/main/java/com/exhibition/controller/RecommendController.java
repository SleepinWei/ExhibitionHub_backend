package com.exhibition.controller;

import com.exhibition.entity.Exhibition;
import com.exhibition.entity.Tag;
import com.exhibition.mapper.ExMapper;
import com.exhibition.mapper.RecommendMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.lang.Integer.parseInt;

/**
 * @Author: JudyLou
 * @Date: 2023/5/20 20:26
 */
@Controller
@RestController
public class RecommendController {
    @Autowired
    private ExMapper exMapper;

    @Autowired
    private RecommendMapper recommendMapper;

    // 根据某一展览包含的所有tag，推荐至少含有这些tag之一的所有展览
    @GetMapping("/recommandEx/{ex_id}")
    public List<Exhibition> getAllRecommendations(@PathVariable Integer ex_id)
    {
//        Integer ex_id = parseInt(String.valueOf(requestBody.get("ex_id")));
//        System.out.println(ex_id);
        List<Tag> tags = exMapper.findTagByExId(ex_id); // 获取某一展览所有tag
//        System.out.println(tags);
        List<Integer> getAllExids = new ArrayList<>(); // 至少包含tags之一的所有展览
        for(Tag tag : tags){
            List<Integer> getExIds =  recommendMapper.getExIds(tag.getId());// 获取含有某tag的所有展览id
            for(Integer getExId : getExIds){
                if(!(getAllExids.contains(getExId)))
                    getAllExids.add(getExId);
            }
        }

        // 获取所有推荐展览
        List<Exhibition> recommendations = new ArrayList<>();
        for(Integer getAllExId : getAllExids){
            if(getAllExId!=ex_id)// 不推荐该展览本身
                recommendations.add(recommendMapper.getAllRecommendations(getAllExId));
        }
//        System.out.println("---------------------------------");
//        System.out.println(recommendations);
        return recommendations;
    }
}
