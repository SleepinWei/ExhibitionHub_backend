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

    private String biography;

    public User(){
    }
    public User(Integer id,String username,String password,String email,String role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
        this.biography=biography;
    }

    public Integer getId() {
        return id;
    }

    public void setAccount(Integer account ) {
        this.id = account;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public void setBiography(String biography) {
        this.biography = biography;
    }
    public String getBiography(){return biography;}

    public String getEmail(){return email;}

    public  void setEmail(String email){this.email = email;}

    public String getRole(){return role;}

    public void setRole(String role){this.role = role;}


    //比较两个User是否相同
    public boolean compare(User u1){//string比较用equals
        if(u1.id==id&&u1.role.equals(role)&&u1.username.equals(username)&&u1.password.equals(password)&&
           u1.email.equals(email)&&u1.sex.equals(sex)&&u1.biography.equals(biography))
            return true;
        else
            return false;
    }
}
