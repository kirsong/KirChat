package com.chat.mobile.mapper;

import com.chat.mobile.vo.ChattingVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ChattingMapper {

    //채킹 보내기
    int submitChatting(ChattingVO chattingVO);

    /**
     * 채팅 검색
     * @param roomId:룸 아이디
     * @param lastStartIndex:최근순으로 시작되는 인덱스
     * @param itemSize:가져오는 사이즈
     * @return
     */
    List<ChattingVO> selectChatting(@Param("roomId")String roomId,
                                    @Param("lastStartIndex")int lastStartIndex,@Param("itemSize")int itemSize);

}
