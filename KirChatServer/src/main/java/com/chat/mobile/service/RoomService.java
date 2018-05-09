package com.chat.mobile.service;

import com.chat.mobile.dao.RoomDao;
import com.chat.mobile.mapper.RoomMapper;
import com.chat.mobile.model.ResultInDataModel;
import com.chat.mobile.vo.RoomListVO;
import com.chat.mobile.vo.RoomVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class RoomService {

    @Autowired
    public RoomDao roomDao;

    //방 만들기
    public RoomVO createRoom(RoomVO roomVO){

        if (roomVO!=null){
            roomVO.setCreateDate(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date()));
        }

        return roomDao.createRoom(roomVO);
    }


    /**
     * 룸 리스트 검색.
     * -방 만든 사용자 ID,상대방 사용자 ID
     * 두 사용자간의 방을 검색하고 싶다면 targetUserId까지 사용하면 되고.
     * 만약 사용자 ID별로 검색하고 싶다면 createUserId로 검색 하면 된다.
     * @param createUserId:방 만든 사용자 ID
     * @param targetUserId:상대방(타겟) 사용자 ID
     * @return
     */
    public RoomListVO roomList(String createUserId,String targetUserId){

        return roomDao.roomList(createUserId,targetUserId);
    }

}
