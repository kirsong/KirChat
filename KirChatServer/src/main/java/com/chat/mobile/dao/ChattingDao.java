package com.chat.mobile.dao;

import com.chat.mobile.mapper.ChattingMapper;
import com.chat.mobile.vo.ChattingVO;
import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ChattingDao {

    @Autowired
    public SqlSessionTemplate sqlSessionTemplate;

    public int submitChatting(ChattingVO chattingVO){
        ChattingMapper chattingMapper=sqlSessionTemplate.getMapper(ChattingMapper.class);
        return chattingMapper.submitChatting(chattingVO);
    }

    public List<ChattingVO> selectChatting(String roomId, int lastStartIndex, int itemSize){
        ChattingMapper chattingMapper=sqlSessionTemplate.getMapper(ChattingMapper.class);
        return chattingMapper.selectChatting(roomId,lastStartIndex,itemSize);
    }

}
