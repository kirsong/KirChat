package com.chat.mobile;

import com.chat.mobile.dao.UserDao;
import com.chat.mobile.mapper.RoomMapper;
import com.chat.mobile.mapper.UserMapper;
import com.chat.mobile.model.FcmRequest;
import com.chat.mobile.model.FcmData;
import com.chat.mobile.model.FcmResult;
import com.chat.mobile.service.ChattingService;
import com.chat.mobile.service.RoomService;
import com.chat.mobile.util.FcmUtil;
import com.chat.mobile.util.Util;
import com.chat.mobile.vo.*;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.*;
import java.net.HttpURLConnection;
import java.text.SimpleDateFormat;
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
    @Autowired
    public RoomService roomService;

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

    @Test
    public void getResource(){

        Resource res=new ClassPathResource("FCMserviceAccountKey.json");
        try {
            FileInputStream file=new FileInputStream(res.getFile());
            file.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testFCM(){

        String tokenKey="cCN-vl_mjfM:APA91bH2OG4juZVfdJ6j0Zkb1JiLjwHFu9CJN5xlDtgzGunrvUx2Nsp-4mRuMeWcKpDp0nCa2tUpr3Jv4O-TPyAHwybYw9VAzeK63b5qcoDaM0m8XK74ECra0UFcWnb6eAHEe-zObrdq";
        FcmUtil fcmUtil=new FcmUtil();
        FcmRequest fcmRequest =new FcmRequest();
        fcmRequest.setTo(tokenKey);
        FcmData fcmData =new FcmData();
        fcmData.setName("홍길동");
        fcmData.setContent("내용");
        fcmData.setDate(Util.createDateFormat());
        fcmRequest.setData(fcmData);

        try {
            FcmResult fcmResult=fcmUtil.sendFcmPush(fcmRequest);
            System.out.println(fcmResult.getSuccess()>0?"보내기 성공":"보내기 실패");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
