package com.exhibition.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.exhibition.entity.Exhibition;
import com.exhibition.entity.derived.ExSearchResult;
import com.exhibition.service.IExService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping()
public class ExhibitionController {
    @Autowired
    private IExService exService;

    @GetMapping("/searchByKeyword")
    public ExSearchResult searchByKeyword(@RequestParam(name = "querytext") String querytext) {
        Exhibition searchResult = exService.getOne(
                new QueryWrapper<Exhibition>()
                        .select("name,location,begin_date,end_date")
                        .eq("name", querytext)
        );
        ExSearchResult result = new ExSearchResult(
                searchResult.getName(),
                searchResult.getLocation(),
                searchResult.getBegin_date(),
                searchResult.getEnd_date()
        );
        return result;
    }

    @GetMapping("/searchById")
    public Exhibition searchById(HttpServletRequest request, HttpServletResponse response, @RequestParam(name = "exId") Integer exId) {
        // test
//        Exhibition exInfo = new Exhibition(1,"2","3", Date.valueOf("2001-01-01"),Date.valueOf("2001-03-03"),"Shanghai","sssssssssss\nsssssss","https://bilibili.com");
        Exhibition result = exService.getById(exId);
        if (result == null) {
//            The Exhibition is not found
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

    @PostMapping("/alterExInfo")
    public String alterExInfo(@RequestBody Exhibition exhibition) {
        // TODO
        System.out.println("alterExInfo");
        System.out.println(exhibition);
        exService.updateById(exhibition);
        return "success";
    }
}
