package com.chat.mobile.mapper;

import com.chat.mobile.vo.UserVO;

import java.util.ArrayList;

public interface UserMapper {

    //사용자 만들기
    int createUser(UserVO userVO);

    ArrayList<UserVO> listSearch(UserVO userVO);

}
