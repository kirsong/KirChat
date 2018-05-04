package com.chat.mobile.controller;

import com.chat.mobile.Define;
import com.chat.mobile.model.ResultCommon;
import com.chat.mobile.model.ResultInDataModel;
import com.chat.mobile.service.ChattingService;
import com.chat.mobile.vo.ChattingVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mobile")
public class ChattingController {

    @Autowired
    public ChattingService chattingService;

    //채팅 보내기
    @RequestMapping("/submitChatting")
    public ResultCommon submitChatting(@RequestBody ChattingVO chattingVO){

        ResultCommon result=new ResultCommon();
        try{
            if (chattingVO!=null){
                if (chattingService.submitChatting(chattingVO)>0){
                    result.setCode(Define.SUCCESS_CODE);
                    return result;
                }
            }
        }catch (Exception e){

        }
        result.setCode(Define.FAILD_CODE);
        return result;
    }

    @RequestMapping("/chattingHistory ")
    public ResultInDataModel chattingHistory(@RequestParam("roomId")String roomId,@RequestParam("lastStartIndex")String lastStartIndex,@RequestParam("itemSize")String itemSize){

        ResultCommon result=new ResultCommon();

        chattingService.selectChatting(roomId,Integer.valueOf(lastStartIndex),Integer.valueOf(itemSize));

    }

}
