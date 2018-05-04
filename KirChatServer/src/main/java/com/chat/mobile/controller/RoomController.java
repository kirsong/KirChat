package com.chat.mobile.controller;

import com.chat.mobile.Define;
import com.chat.mobile.model.ResultCommon;
import com.chat.mobile.model.ResultInDataModel;
import com.chat.mobile.service.RoomService;
import com.chat.mobile.vo.RoomListVO;
import com.chat.mobile.vo.RoomVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.testng.annotations.IFactoryAnnotation;

@RestController
@RequestMapping("/mobile")
public class RoomController {

    @Autowired
    public RoomService roomService;

    /**
     * 방 만들기
     * 이미 만들어진 방이 있다면 해당 방을 반환하고
     * 그렇지 않으면 방을 새로 만들어 반환 한다
     * @param requestBody
     * @return
     */
    @RequestMapping(value = "createRoom",method = RequestMethod.POST)
    public ResultInDataModel createRoom(@RequestBody RoomVO requestBody){

        ResultInDataModel result=new ResultInDataModel();

        try{
            RoomListVO roomListVO=roomService.roomList(requestBody.getCreateUserId(),requestBody.getTargetUserId());
            if (roomListVO.getRoomList()!=null){
                //기존 방이 있을 때
                if (roomListVO.getRoomList().size()>0){
                    result.setCode(Define.SUCCESS_CODE);
                    result.setResult(roomListVO.getRoomList().get(0));
                    return result;
                }
            }

            //기존 방이 없을 때 방을 만든다
            RoomVO roomVO = roomService.createRoom(requestBody);
            if (roomVO!=null){
                result.setCode(Define.SUCCESS_CODE);
                result.setResult(roomVO);
            }else{
                result.setCode(Define.FAILD_CODE);
            }
        }catch (Exception e){result.setCode(Define.FAILD_CODE);}
        return result;
    }

    /**
     * 룸 리스트 검색.
     * -방 만든 사용자 ID,상대방 사용자 ID
     * 두 사용자간의 방을 검색하고 싶다면 targetUserId까지 사용하면 되고.
     * 만약 사용자 ID별로 검색하고 싶다면 createUserId로 검색 하면 된다.
     * @param createUserId:방 만든 사용자 ID
     * @param targetUserId:상대방(타겟) 사용자 ID
     * @return
     */
    @RequestMapping(value = "/roomList")
    public ResultInDataModel roomList(@RequestParam(value = "createUserId",required = false) String createUserId,@RequestParam(value = "targetUserId",required = false) String targetUserId){

        ResultInDataModel result=new ResultInDataModel<>();
        //필수 입력 값
        if (createUserId==null){
            result.setCode(Define.FAILD_CODE);
            result.setResult("사용자 id를 입력하여 주세요.");
        }else{
            RoomListVO roomListVO=roomService.roomList(createUserId,targetUserId);
            result.setResult(roomListVO);
        }
        return result;
    }

}
