package com.chat.mobile;

import com.chat.mobile.dao.UserDao;
import com.chat.mobile.mapper.RoomMapper;
import com.chat.mobile.mapper.UserMapper;
import com.chat.mobile.service.ChattingService;
import com.chat.mobile.service.UserService;
import com.chat.mobile.vo.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "file:/Users/kirsong/Desktop/Web/KirChat/KirChatServer/src/main/webapp/WEB-INF/applicationContext.xml")
public class TestJUnit {

    @Autowired
    public SqlSessionTemplate sqlSessionTemplate;

    @Autowired
    public UserDao userDao;
    @Autowired
    public ChattingService chattingService;

    //방 만들기
    @Test
    public void roomCreate(){

        RoomMapper roomMapper=sqlSessionTemplate.getMapper(RoomMapper.class);
        RoomVO roomVO=new RoomVO();
        roomVO.setCreateUserId("11");
        roomVO.setTargetUserId("45");
        roomVO.setRoomName("test1");
        roomVO.setCreateDate(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date()));
        int rresult=roomMapper.createRoom(roomVO);
        System.out.println(rresult);

    }

    /**
     * 룸 리스트 검색
     */
    @Test
    public void roomList(){
        RoomMapper roomMapper=sqlSessionTemplate.getMapper(RoomMapper.class);
        RoomListVO roomListVO=roomMapper.roomListSearch("11","22");
        System.out.println(roomListVO.getName());
    }

    //사용자 검색
    @Test
    public void searchUser(){

        UserMapper userMapper=sqlSessionTemplate.getMapper(UserMapper.class);
        UserVO userVO=userMapper.userSelect("11");
        if (userVO!=null){
            System.out.println(userVO.getName());
        }else{
            System.out.println("user no existent");
        }
    }

    //푸시 검색
    @Test
    public void pushSearch(){

        PushVO pushVO=userDao.pushSearch("11");
        System.out.println(pushVO.getId());
    }

    //채팅 하기
    @Test
    public void submitChatting(){

        String userid="11",receiveUserId="45";
        String roomId="a11";
        for (int i = 0; i < 10; i++) {
            ChattingVO chattingVO=new ChattingVO();
            chattingVO.setUserId(userid);
            chattingVO.setReceiveUserId(receiveUserId);
            chattingVO.setRoomId(roomId);
            chattingVO.setContent("chatting test :"+i);
            chattingService.submitChatting(chattingVO);
        }

        List<ChattingVO> chattingVOList= chattingService.selectChatting(roomId,0,7);
        for (int i = 0; i < chattingVOList.size(); i++) {
            System.out.println("index : "+chattingVOList.get(i).getNumber());
        }

    }

}
