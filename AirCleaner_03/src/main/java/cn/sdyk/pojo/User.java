package cn.sdyk.pojo;

import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * 用户表
 */
@Component(value="user")
public class User implements Serializable {
    //用户编号
    private int id;
    //用户账号
    private String uname;
    //用户密码
    private String upass;
    //用户电话
    private String phonenumber;
    //用户qq
    private String qq;
    //用户微信
    private String wechat;
    //用户E-Mail
    private String email;
    //用户绑定的设备的唯一标识符
    private String mac;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getUpass() {
        return upass;
    }

    public void setUpass(String upass) {
        this.upass = upass;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }
}
