package cn.sdyk.service;

import cn.sdyk.pojo.status;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface IDeviceDataService {
    //更新数据
    int updateDate(String devicename,String data,Date finallyTime);
    //查询数据
    String StrData(String mac);

    //查询设备在线状态
    List<status> StrOnline(String name)throws Exception;


}
