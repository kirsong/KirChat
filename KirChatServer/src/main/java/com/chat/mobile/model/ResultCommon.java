package com.chat.mobile.model;

import com.chat.mobile.Define;

import java.io.Serializable;

/**
        * 결과 값 모듈
        */
public class ResultCommon implements Serializable {

    /**
     * 0000:성공
     * 0004:실패
     * 0101:계정 이미 있음
     */
    //디폴트는 성공으로 정의
    private String code= Define.SUCCESS_CODE;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
