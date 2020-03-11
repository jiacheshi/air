package cn.sdyk.pojo;

import org.springframework.stereotype.Component;

@Component(value="status")
public class status {
   //设备mac
   private String mac;
   //状态码
   private String code;

   public String getMac() {
      return mac;
   }

   public void setMac(String mac) {
      this.mac = mac;
   }

   public String getCode() {
      return code;
   }

   public void setCode(String code) {
      this.code = code;
   }
}
