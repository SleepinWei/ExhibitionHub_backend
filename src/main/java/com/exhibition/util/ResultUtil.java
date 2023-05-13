package com.exhibition.util;

/**
 * @Author: JudyLou
 * @Date: 2023/4/18 15:47
 */
public class ResultUtil {
    public static Result regularUser(Object object) {
        Result result = new Result();
        result.setCode(ResultCode.REGULAR_USER.getCode());
        result.setMsg(ResultCode.REGULAR_USER.getMsg());
        result.setData(object);
        return result;
    }
    public static Result administrator(Object object) {
        Result result = new Result();
        result.setCode(ResultCode.ADMINISTRATOR.getCode());
        result.setMsg(ResultCode.ADMINISTRATOR.getMsg());
        result.setData(object);
        return result;
    }
    public static Result museumUser(Object object) {
        Result result = new Result();
        result.setCode(ResultCode.MUSEUM.getCode());
        result.setMsg(ResultCode.MUSEUM.getMsg());
        result.setData(object);
        return result;
    }
    /**
     * 成功且带数据
     **/
    public static Result success(Object object) {
        Result result = new Result();
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMsg(ResultCode.SUCCESS.getMsg());
        result.setData(object);
        return result;
    }
    /**
     * 成功但不带数据
     **/
    public static Result success() {

        return success(null);
    }

    /**
     * 失败
     **/
    public static Result error(Object object) {
        Result result = new Result();
        result.setCode(ResultCode.ERROR.getCode());
        result.setMsg(ResultCode.ERROR.getMsg());
        result.setData(object);
        return result;
    }
}
