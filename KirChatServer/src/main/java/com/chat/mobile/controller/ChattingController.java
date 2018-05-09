package com.chat.mobile.controller;

import com.chat.mobile.Define;
import com.chat.mobile.model.*;
import com.chat.mobile.service.ChattingService;
import com.chat.mobile.service.UserService;
import com.chat.mobile.util.FcmUtil;
import com.chat.mobile.util.Util;
import com.chat.mobile.vo.ChattingVO;
import com.chat.mobile.vo.PushVO;
import com.chat.mobile.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.testng.annotations.IFactoryAnnotation;

import java.util.List;

@RestController
@RequestMapping("/mobile")
public class ChattingController {

    @Autowired
    public ChattingService chattingService;
    @Autowired
    public UserService userService;

    private FcmUtil fcmUtil;

    //채팅 보내기
    @RequestMapping("/submitChatting")
    public ResultCommon submitChatting(@RequestBody ChattingVO chattingVO){

        if (fcmUtil==null){
            fcmUtil=new FcmUtil();
        }

        ResultCommon result=new ResultCommon();
        try{
            if (chattingVO!=null){

                //상대방 아이디의 정보를 가져온다
                UserVO userVO=userService.userSelect(chattingVO.getReceiveUserId());
                //상대방 아이디의 토큰을 가져온다
                PushVO pushVO=userService.pushSearch(chattingVO.getReceiveUserId());

                //채팅 푸시에 필요한 데이터
                FcmRequest fcmRequest=new FcmRequest();
                fcmRequest.setTo(pushVO.getPushToken());
                FcmData fcmData=new FcmData();
                fcmData.setName(userVO.getName());
                fcmData.setDate(chattingVO.getSubmitDate());
                fcmData.setContent(chattingVO.getContent());
                fcmRequest.setData(fcmData);
                FcmResult fcmResult=fcmUtil.sendFcmPush(fcmRequest);

                //채팅 저장
                if (chattingService.submitChatting(chattingVO)>0){
                    result.setCode(Define.SUCCESS_CODE);

                    //푸시 보내기 성공
                    if (fcmResult.getSuccess()>0){

                    }
                    //푸시 보내기 실패
                    else{
                        result.setCode(Define.PUSH_FAILD_CODE);
                    }

                    return result;
                }
            }
        }catch (Exception e){

        }
        result.setCode(Define.FAILD_CODE);
        return result;
    }

    /**
     * 채팅 히스토리 가져오기
     * @param roomId
     * @param lastStartIndex
     * @param itemSize
     * @return
     */
    @RequestMapping("/chattingHistory ")
    public ResultInDataModel chattingHistory(@RequestParam("roomId")String roomId,@RequestParam("lastStartIndex")String lastStartIndex,@RequestParam("itemSize")String itemSize){

        ResultInDataModel result=new ResultInDataModel();

        List<ChattingVO> chattingVOList= chattingService.selectChatting(roomId,Integer.valueOf(lastStartIndex),Integer.valueOf(itemSize));
        result.setResult(chattingVOList);
        return result;
    }

}
