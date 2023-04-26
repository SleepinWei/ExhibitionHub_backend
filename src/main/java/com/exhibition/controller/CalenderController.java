package com.exhibition.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.exhibition.entity.*;
import com.exhibition.entity.derived.ExSearchResult;
import com.exhibition.mapper.ExMapper;
import com.exhibition.mapper.ExTagMapper;
import com.exhibition.mapper.SubMapper;
import com.exhibition.service.IExService;
import kotlin.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.util.*;
import java.util.concurrent.Flow;

@RestController
@EnableScheduling
@RequestMapping()
public class CalenderController {
    @Autowired
    private SubMapper subMapper;

    @Autowired
    private ExTagMapper exTagMapper;

    HashMap<Integer, CalendarCache> calendercaches=new HashMap<>();

    //按时间查询，传入用户Id，开始时间，结束时间。时间格式"yyyy-MM-dd"
    @GetMapping("/calendar/{userid}/{src}/{dst}")
    public List<SubExhibition> calendar(@PathVariable int userid,@PathVariable String src,@PathVariable String dst){
        CalendarCache calendarCache=new CalendarCache();
        calendarCache.setDst(dst);
        calendarCache.setSrc(src);
        calendarCache.setSubExhibitions(subMapper.getUserSubscription(userid,src,dst));
        calendarCache.setTimestamp(System.currentTimeMillis());
        calendercaches.put(userid,calendarCache);
        return calendarCache.getSubExhibitions();
    }

    //按标签查询
    //注意：此处可能传入多个tag，要求传入string类型，每个tag之间用空格隔开
    @GetMapping("/calendarByTag/{userid}/{src}/{dst}/{tags}")
    public List<SubExhibition> calendarByTag(@PathVariable int userid,@PathVariable String src,@PathVariable String dst,@PathVariable String tags) {
        Set<Integer> tagids = new HashSet<Integer>();
        for (String s : tags.split(" ")) {
            tagids.add(Integer.parseInt(s));
        }

        if (calendercaches.isEmpty()||!calendercaches.containsKey(userid)) {
            calendar(userid, src, dst);
        }

        CalendarCache calendarCache = calendercaches.get(userid);
        if (!calendarCache.getSrc().equals(src)|| !calendarCache.getDst().equals(dst)) {
            calendar(userid, src, dst);
            calendarCache = calendercaches.get(userid);
        }

        List<SubExhibition> subExhibitons = new ArrayList<SubExhibition>();
        List<SubExhibition> origin=calendarCache.getSubExhibitions();
        for (SubExhibition exhibition : origin) {    //查询每一个现有项是否符合条件
            QueryWrapper<ExTag> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("ex_id", exhibition.getEx_id()).select("tag_id");
            List<ExTag> exTags = exTagMapper.selectList(queryWrapper);
            for (ExTag tag : exTags) {
                if (tagids.contains(tag.getTag_id())) {
                    subExhibitons.add(exhibition);
                    break;
                }
            }
        }
        //List<SubExhibiton> subExhibiton= subMapper.getUserSubscriptionWithTag(userid,src,dst,tagid);
        System.out.println(subExhibitons);
        return subExhibitons;
    }

    //按展馆查询
    @GetMapping("/calendarByOrganizer/{userid}/{src}/{dst}/{venue}")
    public List<SubExhibition> calendarByVenue(@PathVariable int userid,@PathVariable String src,@PathVariable String dst,@PathVariable String venue) {
        if (calendercaches.isEmpty()||!calendercaches.containsKey(userid)) {
            calendar(userid, src, dst);
        }

        CalendarCache calendarCache = calendercaches.get(userid);
        if (!calendarCache.getSrc().equals(src)|| !calendarCache.getDst().equals(dst)) {
            calendar(userid, src, dst);
            calendarCache = calendercaches.get(userid);
        }

        List<SubExhibition> subExhibitons = new ArrayList<SubExhibition>();
        List<SubExhibition> origin=calendarCache.getSubExhibitions();
        for (SubExhibition exhibition : origin) {
            if (exhibition.getVenue_name().contains(venue)) {
                subExhibitons.add(exhibition);
            }
        }
        System.out.println(subExhibitons);
        return subExhibitons;
    }

    //按省市查询
    @GetMapping("/calendarByOrganizer/{userid}/{src}/{dst}/{province}/{city}/{area}")
    public List<SubExhibition> calendarByLocation(@PathVariable int userid,@PathVariable String src,@PathVariable String dst,
                                                  @PathVariable String province,@PathVariable String city,@PathVariable String area) {
        if (calendercaches.isEmpty()||!calendercaches.containsKey(userid)) {
            calendar(userid, src, dst);
        }

        CalendarCache calendarCache = calendercaches.get(userid);
        if (!calendarCache.getSrc().equals(src)|| !calendarCache.getDst().equals(dst)) {
            calendar(userid, src, dst);
            calendarCache = calendercaches.get(userid);
        }

        List<SubExhibition> subExhibitons = new ArrayList<SubExhibition>();
        List<SubExhibition> origin=calendarCache.getSubExhibitions();
        for (SubExhibition exhibition : origin) {
            if (exhibition.getProvince().equals(province) && exhibition.getCity().equals(city) && exhibition.getArea().equals(area)) {
                subExhibitons.add(exhibition);
            }
        }
        System.out.println(subExhibitons);
        return subExhibitons;
    }

    //当离开日历界面时，要求调用本函数，清除查询记录
    @GetMapping("/calendar/cleancache/{userid}")
    public void cleancache(@PathVariable int userid){
        calendercaches.remove(userid);
    }

    //自动调用对于时间过长的查询记录进行定时清理
    @Scheduled(cron = "0 */30 * * * ?")     //每30分钟执行一次
    public void scheduleCleancache(){
        Iterator<Map.Entry<Integer, CalendarCache>> iterator = calendercaches.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, CalendarCache> entry = iterator.next();
            long pastTimestamp=entry.getValue().getTimestamp();
            long currentTimestamp = System.currentTimeMillis();
            long timeDifference = currentTimestamp - pastTimestamp;
            if (timeDifference <= 30*60*1000){  //超过30分钟
                calendercaches.remove(entry.getKey());
            }
        }
    }


}
