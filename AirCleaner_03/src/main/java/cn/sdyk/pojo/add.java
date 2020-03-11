package cn.sdyk.pojo;

import org.springframework.stereotype.Component;

import java.io.Serializable;
@Component(value="add")
public class add implements Serializable {
    private int code;
    private String mac;
    private String nickname;
    private int sum;

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
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
}
