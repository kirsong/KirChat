package com.chat.mobile;

import com.chat.mobile.mapper.UserMapper;
import com.chat.mobile.service.UserService;
import com.chat.mobile.vo.UserVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "file:/Users/kirsong/Desktop/Web/KirChat/KirChatServer/src/main/webapp/WEB-INF/applicationContext.xml")
public class TestJUnit {

    @Autowired
    public SqlSessionTemplate sqlSessionTemplate;

    @Test
    public void test1(){

        UserMapper userMapper=sqlSessionTemplate.getMapper(UserMapper.class);
        UserVO userVO=new UserVO();
        userVO.setId("11");
        ArrayList<UserVO> userVOS=userMapper.listSearch(userVO);
        System.out.println("=========== select data Size : "+userVOS.size()+"===========");
    }

}
