package com.exhibition.service;

import com.exhibition.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 
 * @since 2023-04-08
 */
public interface IUserService extends IService<User> {
    public String loginService(String idOrEmail, String password// );
            , HttpSession session, HttpServletRequest request, HttpServletResponse response);

    public String logoutService(HttpServletRequest request, HttpServletResponse response);

}
