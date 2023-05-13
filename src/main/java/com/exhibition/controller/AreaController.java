package com.exhibition.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping
public class AreaController {

    @GetMapping("/location/province")
    List<String> getProvinceList(){
        return null;
    }

    @GetMapping("/location/city_list")
    List<String> getCityList(@RequestParam(name = "province") String province){
        return null;
    }

    @GetMapping("/location/section_list")
    List<String> getSectionList(@RequestParam(name="city") String city){
        return null;
    }
}
