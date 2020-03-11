package cn.sdyk.pojo;

import org.springframework.stereotype.Component;

import java.io.Serializable;

//设备调节类
@Component(value="adjust")
public class adjust implements Serializable {
    //功能名
    private String name;
    //需要调节的值，上报String类型
    private String dataStr;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getDataStr() {
        return dataStr;
    }

    public void setDataStr(String dataStr) {
        this.dataStr = dataStr;
    }
}
