package com.chat.mobile.service;

import com.chat.mobile.dao.UserDao;
import com.chat.mobile.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    public UserDao userDao;

    public int createUser(String id,String name){

        return userDao.createUser(id,name);
    }


}
