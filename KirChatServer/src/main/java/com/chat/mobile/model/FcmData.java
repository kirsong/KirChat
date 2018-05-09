package com.chat.mobile.model;

public class FcmData {

    private String name;//보낸 사람 이름
    private String content;//채팅 내용
    private String date;//날짜

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
