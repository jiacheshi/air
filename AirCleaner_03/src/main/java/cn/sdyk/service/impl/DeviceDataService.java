package cn.sdyk.service.impl;

import cn.sdyk.config.StrSplit;
import cn.sdyk.mapper.DeviceDataMapper;
import cn.sdyk.mapper.DeviceMapper;
import cn.sdyk.mapper.UserMapper;
import cn.sdyk.pojo.User;
import cn.sdyk.pojo.status;
import cn.sdyk.service.IDeviceDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class DeviceDataService implements IDeviceDataService {

    @Autowired
    DeviceDataMapper deviceDataMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    StrSplit strSplit;
    @Override
    public int updateDate(String devicename, String data, Date finallyTime) {
        if(data.contains("WorkMode")){
       return deviceDataMapper.updateDate1(devicename,data,finallyTime);
        }else {
          return deviceDataMapper.updateDate2(devicename,data,finallyTime);
        }

    }

    @Override
    public String StrData(String mac) {
        return deviceDataMapper.StrData(mac);
    }

    /**
     * 判断用户在线状态
     * @param name
     * @return mac+状态码 0不在线 1在线
     */
    @Override
    public List<status> StrOnline(String name)throws Exception {
        List<status> DeviceStatus=new ArrayList<status>();
        //根据用户名查询出当前用户获得当前用户绑定mac列表
        User user=userMapper.getUserByUname(name);
        String[] a=strSplit.split(user.getMac());
        for (int i = 0; i < a.length; i++) {
            System.out.println("当前判断mac"+a[i]);
            //从数据库中根据mac查询数据更新时间
           String time=deviceDataMapper.StrTime(a[i]);
            SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            /*时间差*/
            //转换格式
            Date fromDate1 = simpleFormat.parse(time);
            //当前时间
            Date date=new Date();
            String ymd = simpleFormat.format(date);
            Date ymdDate = simpleFormat.parse(ymd);
            Date toDate1 = simpleFormat.parse(ymdDate.toLocaleString());
            //转化并比较
            long from1 = fromDate1.getTime();
            long to1 = toDate1.getTime();
            int minutes = (int) ((to1 - from1) / (1000 * 60));
            System.out.println("两个时间之间的分钟差为：" + minutes);
            if(minutes>=5){
                status statu=new status();
               statu.setMac(a[i]);
               statu.setCode("0");
                DeviceStatus.add(statu);
            }else {
                status statu=new status();
                statu.setMac(a[i]);
                statu.setCode("1");
                DeviceStatus.add(statu);
            }

        }
        return DeviceStatus;

    }
}
