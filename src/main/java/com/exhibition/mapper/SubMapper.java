package com.exhibition.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.exhibition.entity.Exhibition;
import com.exhibition.entity.SubExhibitionTemp;
import com.exhibition.entity.Subscription;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface SubMapper extends BaseMapper<Subscription> {
    @Select("SELECT DISTINCT subscription.ex_id,date,name,venue_name,organizer,begin_date,end_date,province,city,area,address,link,tag_id " +
            "FROM subscription,exhibition,exhibition_tag " +
            "WHERE user_id=#{userid} and subscription.ex_id=exhibition.id and exhibition.id=exhibition_tag.ex_id and date>=#{begin} and date<=#{end}")
    List<SubExhibitionTemp> getUserSubscription(@Param("userid") Integer userid, @Param("begin")String begin, @Param("end")String end);

    @Select("SELECT DISTINCT subscription.ex_id,date,name,venue_name,organizer,begin_date,end_date,province,city,area,address,link,tag_id " +
            "FROM exhibition " +
            "WHERE date>=#{begin} and date<=#{end}" +
            "and venue_name=#{venue_name} and province=#{province} and city=#{city} and area=#{area}" +
            "and name like CONCAT ('%', #{query,jdbcType=VARCHAR},'%'")
    List<Exhibition> getSearchResult(@Param("begin")String begin, @Param("end")String end,
                                     @Param("venue_name")String venue_name,@Param("province")String province,@Param("city")String city,
                                     @Param("area")String area,@Param("query")String query);

//    @Select("SELECT ex_id,visitdate,name,venue_name,organizer,begin_date,end_date,province,city,area,address,link " +
//            "FROM subscription,exhibition " +
//            "WHERE user_id=#{userid} and ex_id=id and visitdate>=#{begin} and visitdate<=#{end}")
//    select * from subscription,exhibition where user_id=? and ex_id=id and date>=<begin> and data<=<end>;
}
