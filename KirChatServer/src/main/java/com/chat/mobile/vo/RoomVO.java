package com.chat.mobile.vo;

import java.util.Date;

//룸 정보
public class RoomVO {

    private String roomId;//룸 아이디
    private String roomName;//룸 명
    private String createUserId;//룸 생성한 사용자 ID
    private String targetUserId;//상대방 사용자 ID
    private String createDate;//생성 날짜

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

    public String getTargetUserId() {
        return targetUserId;
    }

    public void setTargetUserId(String targetUserId) {
        this.targetUserId = targetUserId;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }
}
