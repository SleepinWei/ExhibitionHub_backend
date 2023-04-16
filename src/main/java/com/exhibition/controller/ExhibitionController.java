package com.exhibition.controller;

import com.exhibition.entity.Exhibition;
import com.exhibition.service.IExService;
import com.exhibition.service.impl.ExServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;

@RestController
@RequestMapping("/exhibition")
public class ExhibitionController {
    @Autowired
    private IExService exService;

    @GetMapping("/{exId}")
    public Exhibition getExhibitionInfo(HttpServletRequest request, HttpServletResponse response, @PathVariable Integer exId){
        // test
//        Exhibition exInfo = new Exhibition(1,"2","3", Date.valueOf("2001-01-01"),Date.valueOf("2001-03-03"),"Shanghai","sssssssssss\nsssssss","https://bilibili.com");
        Exhibition result= exService.getById(exId);
        if(result == null){
//            The Exhibition is not found
            System.out.println("Exhibition Not Exist");

            response.setStatus(400);
            return new Exhibition();
        }

        System.out.print(result);
        return result;
    }
}
