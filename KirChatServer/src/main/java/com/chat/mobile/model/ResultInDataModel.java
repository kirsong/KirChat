package com.chat.mobile.model;

/**
 * 데이터가 들어가 있는 모델
 */
public class ResultInDataModel<T> extends ResultCommon{

    private T result;

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}
