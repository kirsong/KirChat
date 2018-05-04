package com.chat.mobile.vo;

/**
 * 퓨사 VO
 */
public class PushVO {

    private String id;//사용자 아이디
    private String pushToken;//푸시 토큰
    private String deviceOS;//OS(android,ios)
    private String updateDate;//업데이트 날짜

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPushToken() {
        return pushToken;
    }

    public void setPushToken(String pushToken) {
        this.pushToken = pushToken;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public String getDeviceOS() {
        return deviceOS;
    }

    public void setDeviceOS(String deviceOS) {
        this.deviceOS = deviceOS;
    }
}
