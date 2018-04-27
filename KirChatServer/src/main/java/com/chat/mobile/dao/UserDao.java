package com.chat.mobile.dao;

import com.chat.mobile.mapper.UserMapper;
import com.chat.mobile.vo.UserVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {

    @Autowired
    public SqlSessionTemplate sqlSessionTemplate;

    public int createUser(String id,String name){

        UserMapper userMapper=sqlSessionTemplate.getMapper(UserMapper.class);
        UserVO userVO=new UserVO();
        userVO.setId(id);
        userVO.setName(name);
        return userMapper.createUser(userVO);
    }


}
