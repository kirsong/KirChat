package com.chat.mobile.vo;

import org.apache.ibatis.type.Alias;

import java.io.Serializable;
import java.util.Date;

public class UserVO implements Serializable{

    private String id;
    private String name;
    private String createData;
    private String deviceOS;

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

    public String getCreateData() {
        return createData;
    }

    public void setCreateData(String createData) {
        this.createData = createData;
    }

    public String getDeviceOS() {
        return deviceOS;
    }

    public void setDeviceOS(String deviceOS) {
        this.deviceOS = deviceOS;
    }
}
