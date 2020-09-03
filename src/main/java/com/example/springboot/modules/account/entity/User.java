package com.example.springboot.modules.account.entity;


import com.fasterxml.jackson.annotation.JsonFormat;

import javax.management.relation.Role;
import javax.persistence.*;
import java.util.Date;
import java.util.List;

//自动生成表 映射到数据库中
//@Entity 表明该类是个实体类   @Table 指定数据库表名
@Entity
@Table(name = "user")
public class User {

    //@Id 主键标识
    @Id
    //strategy 设置策略   IDENTITY 表示自增策略
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;
    private String userName;
    private String password;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createDate;

    //@Transient 在不需要序列化的属性前添加关键字
    @Transient
    private boolean rememberMe;
    @Transient
    private List<Role> roles;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public boolean isRememberMe() {
        return rememberMe;
    }

    public void setRememberMe(boolean rememberMe) {
        this.rememberMe = rememberMe;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", createDate=" + createDate +
                ", rememberMe=" + rememberMe +
                ", roles=" + roles +
                '}';
    }
}
