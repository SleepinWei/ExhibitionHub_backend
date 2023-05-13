package com.exhibition.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.exhibition.entity.Exhibition;
import com.exhibition.entity.Exhibitionchecked;
import com.exhibition.entity.ExhibitioncheckedStatusBool;
import com.exhibition.entity.Tag;
import com.exhibition.entity.ExhibitionUnchecked;
import java.util.List;

public interface ExMapper extends BaseMapper<Exhibition> {
    List<Exhibition> searchByKeyword(String keyword);

    List<Tag> findTagByExId(Integer ex_id);

    List<ExhibitionUnchecked> selectUnchecked();

    List<ExhibitioncheckedStatusBool> selectchecked(Integer org_id);

    List<ExhibitioncheckedStatusBool> selectCheckedAdmin();
}
