package com.mvp.wangll.mvptest.demo.list.bean;

import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Entity;

import java.io.Serializable;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToOne;
import org.greenrobot.greendao.annotation.Unique;
import org.greenrobot.greendao.annotation.Generated;

/**
 * 用户实体类
 *
 * @author ZhongDaFeng
 */
@Entity(generateConstructors = false)
public class UserBean implements Serializable {
    private static final long serialVersionUID = 1L;
    //token	string	用户登录生成的token
    //uid	string	用户Id
    @Id(autoincrement = true)
    private Long id;
    @SerializedName("token")
    private String token;
    @Unique
    @SerializedName("uid")
    private String uid;
    public UserBean() {
    }


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    @Override
    public String toString() {
        return "token:"+token.toString()+","+"uid:"+uid.toString();
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
