package com.chat.mobile.service;

import com.chat.mobile.dao.UserDao;
import com.chat.mobile.vo.PushVO;
import com.chat.mobile.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@Service
public class UserService {

    @Autowired
    public UserDao userDao;

    /**
     * 사용자 만들기
     * @param id:아이디
     * @param name:이름
     * @return: -1 계정 이미 있음,0 만들 기 실패,1 만들기 성공,
     */
    public int createUser(String id,String name,String deviceOS){

        UserVO userVO=userSelect(id);

        //만들었던 계정 없음.신청 가능
        if (userVO==null){
            return userDao.createUser(id,name,deviceOS);
        }else{
            return -1;
        }
    }

    /**
     * 사용자 디바이스 정보 업데이트
     * @param userId:사용자 아이디
     * @param deviceOs:os 정보(android,ios)
     * @return
     */
    public int deviceOsUpdateUser(String userId,String deviceOs){
        return userDao.deviceOsUpdateUser(userId,deviceOs);
    }

    //사용자 검색
    public UserVO userSelect(String userId){
        return userDao.userSelect(userId);
    }

    //전체 사용자 리스트
    public ArrayList<UserVO> userList(){
        return userDao.userList();
    }

    /**
     * 푸시 검색
     * @param userId:사용자 아이디
     * @return
     */
    public PushVO pushSearch(String userId){
        return userDao.pushSearch(userId);
    }

    /**
     * 푸시 생성
     * @param pushVO
     * @return
     */
    public int pushCreate(PushVO pushVO){
        pushVO.setUpdateDate(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date()));
        return userDao.pushCreate(pushVO);
    }

    /**
     * 푸시 업데이트
     * @param pushVO
     * @return
     */
    public int pushUpdate(PushVO pushVO){
        pushVO.setUpdateDate(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date()));
        return userDao.pushUpdate(pushVO);
    }
}
