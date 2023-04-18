package com.exhibition.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author 
 * @since 2023-04-08
 */
@Data
@TableName("user")
public class User implements Serializable {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String role;

    private String username;

    private String password;

    private String email;

    private String sex;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", role=" + role +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", sex='" + sex + '\'' +
                '}';
    }

    //比较两个User是否相同
    public boolean compare(User u1){//string比较用equals
        if(u1.id==id&&u1.role.equals(role)&&u1.username.equals(username)&&u1.password.equals(password)&&
           u1.email.equals(email)&&u1.sex.equals(sex))
            return true;
        else
            return false;
    }
}
