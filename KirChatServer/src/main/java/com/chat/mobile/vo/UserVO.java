package com.chat.mobile.vo;

import org.apache.ibatis.type.Alias;

import java.io.Serializable;

public class UserVO implements Serializable{

    private String id;
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
