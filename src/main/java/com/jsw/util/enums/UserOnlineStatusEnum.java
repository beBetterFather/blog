package com.jsw.util.enums;

public enum UserOnlineStatusEnum {

    ONLINE(1, "在线"),
    OFFLINE(0, "离线"),
    EXPIRE(2, "会话过期"),
    FAILURE(3, "登录失败"),
    FORCELOGOUT(4, "强制退出"),
    ;


    UserOnlineStatusEnum(int code, String name){
        this.code = code;
        this.name = name;
    }

    private int code;
    private String name;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
