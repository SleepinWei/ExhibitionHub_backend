package com.exhibition.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.exhibition.entity.Exhibition;
import com.exhibition.entity.Subscription;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

/**
 * @Author: JudyLou
 * @Date: 2023/5/20 20:33
 */
public interface RecommendMapper extends BaseMapper<Subscription> {
    @Select("SELECT distinct ex_id " +
            "FROM exhibition_tag " +
            "WHERE tag_id = #{tag_id} ")
    List<Integer> getExIds(@Param("tag_id")Integer tag_id);

    @Select("SELECT * FROM exhibition WHERE id = #{ex_id}")
    Exhibition getAllRecommendations(@Param("ex_id")Integer ex_id);
}
