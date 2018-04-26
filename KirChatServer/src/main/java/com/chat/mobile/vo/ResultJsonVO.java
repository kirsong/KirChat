package com.chat.mobile.vo;

import java.io.Serializable;

/**
 * 결과 값 모듈
 */
public class ResultJsonVO implements Serializable {

    /**
     * 0000:성공
     * 0004:실패
     */
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
