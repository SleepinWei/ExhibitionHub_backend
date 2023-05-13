package com.exhibition.controller;

import com.exhibition.mapper.AreaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping
public class AreaController {

    @Autowired
    AreaMapper areaMapper;
    @GetMapping("/location/province")
    List<String> getProvinceList() {
        return areaMapper.getProvinceList();
    }

    @GetMapping("/location/city_list")
    List<String> getCityList(@RequestParam(name = "province") String province) {
        return areaMapper.getCityList(province);
    }

    @GetMapping("/location/area_list")
    List<String> getSectionList(@RequestParam(name = "city") String city) {
        return areaMapper.getSectionList(city);
    }
}
