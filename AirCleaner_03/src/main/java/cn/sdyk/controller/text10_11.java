package cn.sdyk.controller;

import cn.sdyk.pojo.Device;
import cn.sdyk.pojo.adjust;
import cn.sdyk.pojo.scale;
import cn.sdyk.service.IDeviceDataService;
import cn.sdyk.service.impl.DeviceDataService;
import cn.sdyk.service.impl.DeviceService;
import cn.sdyk.utils.IoTDemoPubSubDemo;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Date;

@Controller
public class text10_11 {
    @Autowired
    IoTDemoPubSubDemo ioTDemoPubSubDemo;
    @Autowired
    adjust a;
    @Autowired
    DeviceDataService deviceDataService;

    @Autowired
    Device device;
    @Autowired
    DeviceService deviceService;

    @RequestMapping("/show")
    public void Show(String mac,HttpServletRequest request, HttpServletResponse response)throws Exception{
        System.out.println("开始运行");
        //连接平台接收下发数据
        String jsonStr=IoTDemoPubSubDemo.text1;
        System.out.println(jsonStr);
        JSONObject jsonObject = JSON.parseObject(jsonStr);
        //从json字符串中取出devicename和data数据存储到数据库
        String devicename= String.valueOf(jsonObject.get("id"));
        System.out.println(devicename);
        String data= String.valueOf(jsonObject.get("params"));
        System.err.println(data);
        //存入数据库
        int i=deviceDataService.updateDate(devicename,data,new Date());
       // System.out.println(i);
        String da=deviceDataService.StrData(mac);
      //  System.out.println(mac+da);
        // String d = JSON.toJSONString(da);
        response.getWriter().write(da);
       // System.out.println("1666666666666"+da);


    }

    @RequestMapping("/low")
    public void Low(String mac,HttpServletRequest request, HttpServletResponse response)throws Exception{
        device=deviceService.selByMac(mac);
        String JOSN= ioTDemoPubSubDemo.monitor();
        a.setName("FAN");
        a.setDataStr("LOW");
        ioTDemoPubSubDemo.begin(a,device.getProductKey(),device.getDeviceName());
    }
    @RequestMapping("/mid")
    public void Mid(String mac,HttpServletRequest request, HttpServletResponse response)throws Exception{
        device=deviceService.selByMac(mac);
        String JOSN= ioTDemoPubSubDemo.monitor();
        a.setName("FAN");
        a.setDataStr("MID");
        ioTDemoPubSubDemo.begin(a,device.getProductKey(),device.getDeviceName());
    }
    @RequestMapping("/hig")
    public void Hig(String mac,HttpServletRequest request, HttpServletResponse response){
        device=deviceService.selByMac(mac);
        String JOSN= ioTDemoPubSubDemo.monitor();
        a.setName("FAN");
        a.setDataStr("HIG");
        ioTDemoPubSubDemo.begin(a,device.getProductKey(),device.getDeviceName());

    }

    @RequestMapping("/auto")
    public void Auto(String mac){
        device=deviceService.selByMac(mac);
        String JOSN= ioTDemoPubSubDemo.monitor();
        a.setName("MODE");
        a.setDataStr("AUTO");
        ioTDemoPubSubDemo.begin(a,device.getProductKey(),device.getDeviceName());
    }
    @RequestMapping("/shutdown")
    public void Shutdown(String mac){
        device=deviceService.selByMac(mac);
        String JOSN= ioTDemoPubSubDemo.monitor();
        a.setName("MODE");
        a.setDataStr("SHUTDOWN");
        ioTDemoPubSubDemo.begin(a,device.getProductKey(),device.getDeviceName());
    }

    @RequestMapping("/scale")
    @ResponseBody
    public void Scale(@RequestBody scale e){
        device=deviceService.selByMac(e.getMac());
        String JOSN= ioTDemoPubSubDemo.monitor();
        a.setName("SCALE");
        a.setDataStr(e.getSz());
        ioTDemoPubSubDemo.begin(a,device.getProductKey(),device.getDeviceName());

    }

}
