package com.exhibition.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.exhibition.entity.Exhibition;
import com.exhibition.entity.Tag;
import com.exhibition.entity.response_type.ExhibitioncheckedStatusBool;
import com.exhibition.entity.response_type.VenueInfo;

import java.util.List;

public interface ExMapper extends BaseMapper<Exhibition> {
    List<Exhibition> searchByKeyword(String keyword);

    List<Tag> findTagByExId(Integer ex_id);

    List<ExhibitioncheckedStatusBool> selectchecked(Integer keyword);

    List<String> getAllOrganizers();

    List<ExhibitioncheckedStatusBool> selectCheckedAdmin();

    List<VenueInfo> getAllVenueInfo();

    List<Exhibition> getExInfo();

    Integer getNextId();
}
