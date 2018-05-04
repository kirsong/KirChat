package com.chat.mobile.mapper;

import com.chat.mobile.model.ResultInDataModel;
import com.chat.mobile.vo.RoomListVO;
import com.chat.mobile.vo.RoomVO;
import org.apache.ibatis.annotations.Param;

/**
 * 방
 */
public interface RoomMapper {

    /**
     * 방 만들기
     * @param roomVO
     * @return
     */
    int createRoom(RoomVO roomVO);

    /**
     * 룸 리스트 검색.
     * -방 만든 사용자 ID,상대방 사용자 ID
     * 두 사용자간의 방을 검색하고 싶다면 targetUserId까지 사용하면 되고.
     * 만약 사용자 ID별로 검색하고 싶다면 createUserId로 검색 하면 된다.
     * @param createUserId:방 만든 사용자 ID
     * @param targetUserId:상대방(타겟) 사용자 ID
     * @return
     */
    RoomListVO roomListSearch(@Param("userId") String createUserId, @Param("targetUserId") String targetUserId);
}
