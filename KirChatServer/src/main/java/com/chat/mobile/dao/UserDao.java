package com.chat.mobile.dao;

import com.chat.mobile.mapper.UserMapper;
import com.chat.mobile.vo.PushVO;
import com.chat.mobile.vo.UserVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@Repository
public class UserDao {

    @Autowired
    public SqlSessionTemplate sqlSessionTemplate;

    /**
     * 계정 만들기
     * @param id
     * @param name
     * @return
     */
    public int createUser(String id,String name,String deviceOS){

        UserMapper userMapper=sqlSessionTemplate.getMapper(UserMapper.class);
        UserVO userVO=new UserVO();
        userVO.setId(id);
        userVO.setName(name);
        userVO.setDeviceOS(deviceOS);
        Date date=new Date();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm");
        userVO.setCreateData(simpleDateFormat.format(date));
        return userMapper.createUser(userVO);
    }


    /**
     * 한개 계정 검색
     * @param userId
     * @return
     */
    public UserVO userSelect(String userId){
        UserMapper userMapper=sqlSessionTemplate.getMapper(UserMapper.class);
        return userMapper.userSelect(userId);
    }

    /**
     * 사용자 디바이스 정보 업데이트
     * @param userId
     * @param deviceOs
     * @return
     */
    public int deviceOsUpdateUser(String userId,String deviceOs){
        UserMapper userMapper=sqlSessionTemplate.getMapper(UserMapper.class);
        return userMapper.deviceOsUpdateUser(userId,deviceOs);
    }

    /**
     * 사용자 리스트
     * @return
     */
    public ArrayList<UserVO> userList(){
        UserMapper userMapper=sqlSessionTemplate.getMapper(UserMapper.class);
        return userMapper.userList();
    }

    /**
     * 푸시 검색
     * @param userId:사용자 아이디
     * @return
     */
    public PushVO pushSearch(String userId){

        PushVO pushVO=sqlSessionTemplate.selectOne("com.chat.mobile.mapper.UserMapper.selectPush",userId);
        return pushVO;
    }

    /**
     * 푸시 생성
     * @param pushVO
     * @return
     */
    public int pushCreate(PushVO pushVO){
        UserMapper userMapper=sqlSessionTemplate.getMapper(UserMapper.class);
        return userMapper.insertPush(pushVO);
    }

    /**
     * 푸시 업데이트
     * @param pushVO
     * @return
     */
    public int pushUpdate(PushVO pushVO){
        UserMapper userMapper=sqlSessionTemplate.getMapper(UserMapper.class);
        return userMapper.updatePush(pushVO);
    }
}
