package com.chat.mobile.vo;

import java.util.Date;
import java.util.List;

//사용자 정보 검색에 따른 사용자정보 + 해당 사용자 룸 리시트 정보
public class RoomListVO extends UserVO{

    private List<RoomVO> roomList;

    public List<RoomVO> getRoomList() {
        return roomList;
    }

    public void setRoomList(List<RoomVO> roomList) {
        this.roomList = roomList;
    }
}
