package cn.sdyk.service.impl;

import cn.sdyk.config.StrSplit;
import cn.sdyk.mapper.DeviceMapper;
import cn.sdyk.pojo.Device;
import cn.sdyk.service.IDeviceService;
import cn.sdyk.utils.IoTDemoPubSubDemo;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeviceService implements IDeviceService {
    @Autowired
    DeviceMapper deviceMapper;
    @Autowired
    Device device;
    @Autowired
    StrSplit strSplit;
    @Autowired
    IoTDemoPubSubDemo ioTDemoPubSubDemo;

    @Override
    public Device selByMac(String mac) {
        return deviceMapper.selByMac(mac);
    }

    /**
     *
     * @param mac
     * @param adminpwd
     * @return 返回1表示设备mac和管理员密码错误，返回2表示设备绑定上限，返回0则正常绑定
     */
    @Override
    public int binding(String mac,String adminpwd) {
      device=deviceMapper.selByMacPwd(mac,adminpwd);

      if(device==null) {
          //返回1表示设备mac和管理员密码错误
          return 1;
      }else {
          if (strSplit.split(device.getBinding()).length >= 5) {
              System.out.println(device.getBinding().toString());
              //被大于五名用户绑定时不允许添加
              return 2;
          }else {
              return 0;
          }
      }

    }

    @Override
    public void deviceBinding(String uname,String mac) {

        deviceMapper.deviceBinding(uname,mac);
    }

    @Override
    public int delDeice(String uname, String mac) {


        return deviceMapper.delForDevice(uname,mac);
    }

    @Override
    public void updByMac(String nickname, String mac) {
        //根据mac修改设备昵称
        deviceMapper.updByMac(nickname,mac);
    }


}
