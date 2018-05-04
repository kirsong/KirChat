package com.chat.mobile.service;

import com.chat.mobile.dao.ChattingDao;
import com.chat.mobile.mapper.ChattingMapper;
import com.chat.mobile.util.Util;
import com.chat.mobile.vo.ChattingVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.List;

@Service
public class ChattingService {

    @Autowired
    public ChattingDao chattingDao;

    public int submitChatting(ChattingVO chattingVO){
        chattingVO.setSubmitDate(Util.createDateFormat());
        return chattingDao.submitChatting(chattingVO);
    }

    public List<ChattingVO> selectChatting(String roomId, int lastStartIndex, int itemSize){
        return chattingDao.selectChatting(roomId,lastStartIndex,itemSize);
    }

}
