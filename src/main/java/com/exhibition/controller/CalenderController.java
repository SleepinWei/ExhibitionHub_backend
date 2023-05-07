package com.exhibition.controller;

import com.exhibition.entity.CalendarCache;
import com.exhibition.entity.SubExhibition;
import com.exhibition.entity.SubExhibitionTemp;
import com.exhibition.mapper.ExTagMapper;
import com.exhibition.mapper.SubMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@EnableScheduling
@RequestMapping()
public class CalenderController {
    @Autowired
    private SubMapper subMapper;

    @Autowired
    private ExTagMapper exTagMapper;

    HashMap<Integer, CalendarCache> calendercaches = new HashMap<>();

    //按时间查询，传入用户Id，开始时间，结束时间。时间格式"yyyy-MM-dd"
    @GetMapping("/calendar/{userid}/{src}/{dst}")
    public List<SubExhibition> calendar(@PathVariable int userid, @PathVariable String src, @PathVariable String dst) {
        List<SubExhibitionTemp> subExhibitionTempList = subMapper.getUserSubscription(userid, src, dst);    //查表结果
        //合并同一展览的所有tag，存放在list中
        Map<Integer, SubExhibition> subExhibitionMap = new HashMap<>();
        for (SubExhibitionTemp temp : subExhibitionTempList) {
            Integer exid = temp.getEx_id();
            if (subExhibitionMap.containsKey(exid)) {
                subExhibitionMap.get(exid).getTags().add(temp.getTag_id());
            } else {
                subExhibitionMap.put(exid, new SubExhibition(temp));
            }
        }

        CalendarCache calendarCache = new CalendarCache();
        calendarCache.setDst(dst);
        calendarCache.setSrc(src);
        calendarCache.setSubExhibitions(new ArrayList<>(subExhibitionMap.values()));
        calendarCache.setTimestamp(System.currentTimeMillis());
        calendercaches.put(userid, calendarCache);
        return calendarCache.getSubExhibitions();
    }

    /*
        多选项联合筛选，要求传入所有参数，如果某项为空，请传入""
        此处可能传入多个tag，要求传入string类型，每个tag之间用空格隔开
     */
    @GetMapping("/calendarByOrganizer/{userid}/{src}/{dst}/{venue}/{tags}/{province}/{city}/{area}")
    public List<SubExhibition> calendarSelect(@PathVariable int userid,
                                              @PathVariable String src, @PathVariable String dst,
                                              @PathVariable String venue, @PathVariable String tags,
                                              @PathVariable String province, @PathVariable String city, @PathVariable String area) {
        System.out.println(userid+"  "+src+"  "+dst+"  "+tags+"***");
        if (calendercaches.isEmpty() || !calendercaches.containsKey(userid)) {
            calendar(userid, src, dst);
        }

        CalendarCache calendarCache = calendercaches.get(userid);
        if (!calendarCache.getSrc().equals(src) || !calendarCache.getDst().equals(dst)) {
            calendar(userid, src, dst);
            calendarCache = calendercaches.get(userid);
        }

        List<SubExhibition> subExhibitons = new ArrayList<>(calendarCache.getSubExhibitions());
        venue = (venue.equals("null")) ? "" : venue;
        province = (province.equals("null")) ? "" : province;
        city = (city.equals("null")) ? "" : city;
        area = (area.equals("null")) ? "" : area;
        tags = (tags.equals("-1")) ? "" : tags;
        //按不同条件进行筛选
        if (!venue.isEmpty()) {
            calendarByVenue(subExhibitons, venue);
        }
        if (!province.isEmpty() && !city.isEmpty() && !area.isEmpty()) {
            calendarByLocation(subExhibitons, province, city, area);
        }
        if (!tags.isEmpty()) {
            calendarByTag(subExhibitons, tags);
        }

        System.out.println(subExhibitons.size());
        return subExhibitons;
    }


    //按标签查询
    //注意：此处可能传入多个tag，要求传入string类型，每个tag之间用空格隔开
    private List<SubExhibition> calendarByTag(List<SubExhibition> subExhibitions, String tags) {
        Set<Integer> tagids = new HashSet<Integer>();
        for (String s : tags.split("00")) {
            tagids.add(Integer.parseInt(s));
            System.out.println("&&"+s+"**");
        }

        //查询每一个现有项是否符合条件
        for (int i = 0; i < subExhibitions.size(); i++) {
            SubExhibition exhibition = subExhibitions.get(i);
            List<Integer> Tags = exhibition.getTags();
            boolean remove = true;
            for (Integer tag : Tags) {
                if (tagids.contains(tag)) {
                    remove = false;
                    break;
                }
            }
            if (remove) {
                // 换到末尾进行删除，降低复杂度
                Collections.swap(subExhibitions, i, subExhibitions.size() - 1);
                subExhibitions.remove(subExhibitions.size() - 1);
                i--;
            }
        }
        return subExhibitions;
    }

    //按展馆查询
    private List<SubExhibition> calendarByVenue(List<SubExhibition> subExhibitions, String venue) {
        for (int i = 0; i < subExhibitions.size(); i++) {
            SubExhibition exhibition = subExhibitions.get(i);
            if (!exhibition.getVenue_name().contains(venue)) {
                Collections.swap(subExhibitions, i, subExhibitions.size() - 1);
                subExhibitions.remove(subExhibitions.size() - 1);
                i--;
            }
        }
        return subExhibitions;
    }

    //按省市查询
    private List<SubExhibition> calendarByLocation(List<SubExhibition> subExhibitions,
                                                   String province, String city, String area) {
        for (int i = 0; i < subExhibitions.size(); i++) {
            SubExhibition exhibition = subExhibitions.get(i);
            if (!exhibition.getProvince().equals(province) || !exhibition.getCity().equals(city) || !exhibition.getArea().equals(area)) {
                Collections.swap(subExhibitions, i, subExhibitions.size() - 1);
                subExhibitions.remove(subExhibitions.size() - 1);
                i--;
            }
        }
        return subExhibitions;
    }

    //当离开日历界面时，要求调用本函数，清除查询记录
    @GetMapping("/calendar/cleancache/{userid}")
    public void cleancache(@PathVariable int userid) {
        calendercaches.remove(userid);
    }

    //自动调用对于时间过长的查询记录进行定时清理
    @Scheduled(cron = "0 */30 * * * ?")     //每30分钟执行一次
    public void scheduleCleancache() {
        Iterator<Map.Entry<Integer, CalendarCache>> iterator = calendercaches.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, CalendarCache> entry = iterator.next();
            long pastTimestamp = entry.getValue().getTimestamp();
            long currentTimestamp = System.currentTimeMillis();
            long timeDifference = currentTimestamp - pastTimestamp;
            if (timeDifference <= 30 * 60 * 1000) {  //超过30分钟
                iterator.remove();
            }
        }
    }


}
