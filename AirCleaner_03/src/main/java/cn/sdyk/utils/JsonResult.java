package cn.sdyk.utils;

import org.apache.ibatis.annotations.Select;

public class JsonResult<T> {
    private String mac;
    private String nickname;
    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "JsonResult{" +
                "mac='" + mac + '\'' +
                ", nickname='" + nickname + '\'' +
                ", data=" + data +
                '}';
    }
}
