package cn.sdyk.service;

import cn.sdyk.pojo.Device;
import org.apache.ibatis.annotations.Update;


public interface IDeviceService {
    //通过mac值查询设备列表
    Device selByMac(String mac);

    //绑定设备参数方法，参数为设备mac值，管理员密码,
    int binding(String mac,String adminpwd);

    /**
     * 向设备中添加用户
     * @param uname 用户名
     */
    void deviceBinding(String uname,String mac);

    //删除绑定设备
    int delDeice(String uname,String mac);

    //修改设备昵称
    void updByMac(String nickname,String mac);



}
