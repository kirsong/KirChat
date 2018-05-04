package com.chat.mobile.dao;

import com.chat.mobile.mapper.RoomMapper;
import com.chat.mobile.mapper.UserMapper;
import com.chat.mobile.vo.RoomListVO;
import com.chat.mobile.vo.RoomVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

@Repository
public class RoomDao {

    @Autowired
    public SqlSessionTemplate sqlSessionTemplate;

    public RoomVO createRoom(RoomVO requestBody){

        RoomMapper roomMapper=sqlSessionTemplate.getMapper(RoomMapper.class);
        roomMapper.createRoom(requestBody);
        return requestBody;
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

        RoomMapper roomMapper=sqlSessionTemplate.getMapper(RoomMapper.class);
        RoomListVO roomListVO=roomMapper.roomListSearch(createUserId,targetUserId);

        return roomListVO;
    }
}
