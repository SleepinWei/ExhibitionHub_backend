package com.exhibition.mapper;

import com.exhibition.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 
 * @since 2023-04-08
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
    User searchById(Integer id);

    User searchByEmail(String email);
//查询所有用户


}
