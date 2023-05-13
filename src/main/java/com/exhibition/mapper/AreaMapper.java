package com.exhibition.mapper;

import java.util.List;

public interface AreaMapper {
    List<String> getProvinceList();

    List<String> getCityList(String province);

    List<String> getSectionList(String city);
}
