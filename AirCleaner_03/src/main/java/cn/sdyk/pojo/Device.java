package cn.sdyk.pojo;

import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * 设备表
 */
@Component(value="device")
public class Device implements Serializable {

    //设备唯一标识
    private String mac;
    //设备昵称
    private String nickname;
    //设备标识_三元组
    private String productKey;
    private String deviceName;
    private String deviceSecret;
    //管理员密码
    private String adminpwd;
    //位置
    private String location;
    //绑定的用户
    private String binding;

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

    public String getProductKey() {
        return productKey;
    }

    public void setProductKey(String productKey) {
        this.productKey = productKey;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getDeviceSecret() {
        return deviceSecret;
    }

    public void setDeviceSecret(String deviceSecret) {
        this.deviceSecret = deviceSecret;
    }

    public String getAdminpwd() {
        return adminpwd;
    }

    public void setAdminpwd(String adminpwd) {
        this.adminpwd = adminpwd;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getBinding() {
        return binding;
    }

    public void setBinding(String binding) {
        this.binding = binding;
    }

    @Override
    public String toString() {
        return "Device{" +
                "mac='" + mac + '\'' +
                ", nickname='" + nickname + '\'' +
                ", productKey='" + productKey + '\'' +
                ", deviceName='" + deviceName + '\'' +
                ", deviceSecret='" + deviceSecret + '\'' +
                ", adminpwd='" + adminpwd + '\'' +
                ", location='" + location + '\'' +
                ", binding='" + binding + '\'' +
                '}';
    }
}
