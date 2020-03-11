package cn.sdyk.pojo;

import java.io.Serializable;

public class scale implements Serializable {
    public String sz;
    public String mac;

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getSz() {
        return sz;
    }


    public void setSz(String sz) {
        this.sz = sz;
    }
}
