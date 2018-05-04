package com.chat.mobile.controller;

import com.chat.mobile.Define;
import com.chat.mobile.model.ResultInDataModel;
import com.chat.mobile.service.UserService;
import com.chat.mobile.model.ResultCommon;
import com.chat.mobile.vo.PushVO;
import com.chat.mobile.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping(value = "/mobile")
public class UserController {

    @Autowired
    public UserService userService;

    /**
     * 계정 만들 기
     * @param id
     * @param name
     * @return
     */
    @RequestMapping(value = "/createUser")
    public ResultCommon createUser(@RequestParam("id")String id, @RequestParam("name")String name,@RequestParam("deviceOS")String deviceOS){

        ResultCommon resultJsonVO=new ResultCommon();

        int result=userService.createUser(id,name,deviceOS);
        if (result<0){//계정 이미 있음
            resultJsonVO.setCode(Define.EXSTENT_CODE);
        }else if (result==0){//만들 기 실패
            resultJsonVO.setCode(Define.FAILD_CODE);
        }else{//만들 기 성공
            resultJsonVO.setCode(Define.SUCCESS_CODE);
        }

        return resultJsonVO;
    }

    /**
     * 로그인
     * @param id:아이디
     * @param pushToken : 푸시 토큰
     * @param deviceOs  : OS 명(ios,android)
     * @return
     */
    @RequestMapping(value = "/login")
    public ResultInDataModel login(@RequestParam("id")String id,@RequestParam("pushToken")String pushToken,@RequestParam("deviceOs")String deviceOs){

        ResultInDataModel userModel=new ResultInDataModel();

        try{
            PushVO pushVO=new PushVO();
            pushVO.setId(id);
            pushVO.setPushToken(pushToken);
            pushVO.setDeviceOS(deviceOs);
            //푸시 정보가 없을 시 만든다
            if (userService.pushSearch(id)==null){
                userService.pushCreate(pushVO);
            }
            //푸시 정보가 있을 시 업데이트 해 준다
            else{
                userService.pushUpdate(pushVO);
            }

            UserVO userVO=userService.userSelect(id);
            if (userVO==null){
                userModel.setCode(Define.FAILD_CODE);
            }else{
                //OS 업데이트 한다
                userService.deviceOsUpdateUser(id,deviceOs);
                userModel.setCode(Define.SUCCESS_CODE);
                userModel.setResult(userVO);
            }

        }catch (Exception e){
            userModel.setCode(Define.FAILD_CODE);
        }

        return userModel;
    }

    /**
     * 사용자 리스트
     * @return
     */
    @RequestMapping(value = "/userList")
    public ResultInDataModel userList(){
        ResultInDataModel<ArrayList<UserVO>> result=new ResultInDataModel<>();
        ArrayList<UserVO> voArrayList=userService.userList();
        result.setResult(voArrayList);
        return result;
    }

}
