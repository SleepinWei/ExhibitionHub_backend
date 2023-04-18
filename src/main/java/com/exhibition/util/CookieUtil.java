package com.exhibition.util;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.geom.RectangularShape;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @Author: JudyLou
 * @Date: 2023/4/18 15:38
 */
public class CookieUtil {
    //设置Cookie
    public static void setCookie(HttpServletResponse response, String key, String value, int expiry){
        Cookie cookie = new Cookie(key, value); //key 为cookie内容名字，value为cookie 类型
//        ResponseCookie cookie = ResponseCookie.from(key,value)
//                .sameSite("None")
//                .secure(true)
//                .httpOnly(false)
//                .maxAge(expiry)
//                .domain("localhost")
//                .build();
        cookie.setMaxAge(expiry);       //cookie过期时间
        cookie.setSecure(false);  //安全的cookie是仅可以通过加密的HTTPS连接发送到服务器的cookie。无法通过未加密的HTTP连接将cookie发送到服务器。也就是说，如果设置了setSecure(true)，该Cookie将无法在Http连接中传输，只能是Https连接中传输。
//        cookie.setHttpOnly(true);  //不能被js访问的Cookie
        response.addCookie(cookie);
//        response.addHeader("Set-Cookie",cookie.toString());
    }


    //获取所有Cookie
    public static Map<String, String> getCookies(HttpServletRequest request){
        Map<String, String> map = new HashMap<>();
        Cookie cookies[] = request.getCookies();
        if (cookies != null){
            for(int i = 0; i < cookies.length; i++){
                if(!"JSESSION".equals(cookies[i].getName()))
                    map.put(cookies[i].getName(), cookies[i].getValue());
            }
        }
        return map;
    }

    //清空所有的Cookie                                               点击退出按钮后
    public static void clear(HttpServletRequest request, HttpServletResponse response){
        Map<String, String> map = getCookies(request);
        Iterator<Map.Entry<String, String>> iter = map.entrySet().iterator();
        while(iter.hasNext()){
            Map.Entry<String, String> me = iter.next();
            Cookie cookie = new Cookie(me.getKey(), "");
            cookie.setMaxAge(0);
            response.addCookie(cookie);
        }
    }
}
