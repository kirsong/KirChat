package com.chat.mobile.mapper;

import com.chat.mobile.vo.PushVO;
import com.chat.mobile.vo.RoomListVO;
import com.chat.mobile.vo.UserVO;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;

public interface UserMapper {

    //사용자 만들기
    int createUser(UserVO userVO);

    int deviceOsUpdateUser(@Param("id")String userId,@Param("deviceOs")String deviceOs);

    //사용자 검색
    UserVO userSelect(@Param("id")String userId);

    //전체 사용자 리스트
    ArrayList<UserVO> userList();

    //푸시 넣기
    int insertPush(PushVO pushVO);

    //푸시 업데이트
    int updatePush(PushVO vo);

    //푸시 검색
    PushVO selectPush(@Param("id")String userId);
}
