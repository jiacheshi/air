package cn.sdyk.controller;

import cn.sdyk.config.StrSplit;
import cn.sdyk.pojo.Device;
import cn.sdyk.pojo.User;
import cn.sdyk.pojo.add;
import cn.sdyk.pojo.adjust;
import cn.sdyk.service.impl.DeviceDataService;
import cn.sdyk.service.impl.DeviceService;
import cn.sdyk.service.impl.UserService;
import cn.sdyk.utils.IoTDemoPubSubDemo;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

@Controller
@RequestMapping("/devices")
public class DeviceController {

    @Autowired
    DeviceService deviceService;
    @Autowired
    Device device;
    @Autowired
    StrSplit strSplit;
    @Autowired
    UserService userService;
    @Autowired
    adjust a;
    @Autowired
    IoTDemoPubSubDemo ioTDemoPubSubDemo;
    @Autowired
    DeviceDataService deviceDataService;
    @Autowired
    cn.sdyk.pojo.add add;

    /**
     * 改变设备三元组来收发数据，切换设备时调用
     *
     * @param mac
     */
    @GetMapping("/switcher")
    @ResponseBody
    public void switcher(String mac,HttpServletResponse response) {
        //根据mac获取三元组
       // System.err.println("ioTDemoPubSubDemo.deviceName0"+ioTDemoPubSubDemo.deviceName);
      //  System.err.println("(device.ge getDeviceName()"+device.getDeviceName());
       // System.out.println(mac);
        device=deviceService.selByMac(mac);
        System.out.println(device);

        ioTDemoPubSubDemo.deviceName=device.getDeviceName();
        ioTDemoPubSubDemo.deviceSecret=device.getDeviceSecret();
        ioTDemoPubSubDemo.productKey=device.getProductKey();
       // System.out.println("赋值线程："+Thread.currentThread().getName());
       // System.out.println(ioTDemoPubSubDemo.deviceName);
      //  System.err.println("switcher");
    //    System.out.println(6);
//	   try {
//		   ioTDemoPubSubDemo.mqttClient.unsubscribe("/" + ioTDemoPubSubDemo.productKey+ "/" + ioTDemoPubSubDemo.deviceName+ "/user/s_data");
//			} catch (MqttException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
        String JOSN= ioTDemoPubSubDemo.monitor();
      //  System.out.println(7);
        adjust a = new adjust();
        a.setName("MODE");
        a.setDataStr("1");
     //   ioTDemoPubSubDemo.begin(a);
    //    System.err.println("show="+JOSN.toString());


    }


    /**
     *
     * @param mac
     * @param adminpwd
     * @param request 返回1表示设备mac和管理员密码错误，返回2表示设备绑定上限，返回3表示用户绑定设备上限，返回0则正常绑定
     */
    @GetMapping("/binding")
    @Transactional
    public void binding(String mac,String adminpwd,String nickname,HttpServletRequest request, HttpServletResponse response)throws Exception{

        if(nickname==null){
            nickname="驭风者设备";
        }
        Integer a;
        String jsonStr;
        //判断绑定设备是否重复
        boolean b=true;
        // 获取用户信息
        String v = null;

        //查询coo
        String names="";
        Cookie[] cookies=request.getCookies();
        if(cookies==null){
        }else {
            for (Cookie cookie : cookies) {
                if (
                        cookie.getName().equals("uname")) {
                    names = cookie.getValue();
                }
            }
        }
        //  HttpSession session = request.getSession();


        //获取用户绑定设备字符串并解析

        User user=userService.getUserByUname(names);
        String uname=user.getUname();

        String macList=user.getMac();
        //用户绑定用户的集合
       String[] alist=null;
        if(macList!=null) {
            alist = strSplit.split(macList);
            // String uname=userInfo.getUname();
            //判断数据库中用户绑定设备不重复
            for (int i = 0; i < alist.length; i++) {
                if (alist[i].equals(mac)) {
                    b = false;
                }
            }

        }else {

            b=true;
        }

        if(b) {

            if (alist==null||alist.length <= 5) {
                a = deviceService.binding(mac,adminpwd);
                if (a == 0) {
                    System.out.println("开始存入数据库");
                    //添加事务处理
                    try{
                        //修改mac
                        deviceService.updByMac(nickname,mac);
                        //添加设备表中数据
                        deviceService.deviceBinding(uname + ",", mac);
                        //添加用户表中数据
                        userService.usersBinding(mac + ",", uname);
                        a=0;
                    }catch (Exception ex){
                        a=5;
                        //回滚事务
                        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();

                    }

                }
            } else {
                a = 3;
            }
        }else {
            a=4;
        }
//        if(a==0){
//          device=deviceService.selByMac(mac);
//
//            jsonStr="{code:"+a+",mac:"+mac+",nickname:"+device.getNickname()+"}";
//            device.getNickname();
//
//        }else {
//            jsonStr="{code:"+a+"}";
//        }
        String jsonstr=null;
        if(a==0) {
            device = deviceService.selByMac(mac);
            add.setCode(a);
            add.setMac(mac);
           add.setSum(alist.length+1);
            add.setNickname(device.getNickname());
         //   System.out.println(device.getNickname());
             jsonstr=JSONObject.toJSONString(add);
        }else {
           add.setCode(a);
            jsonstr=JSONObject.toJSONString(add);
        }

        try {

            sendJsonData(response,jsonstr);

        } catch (Exception e) {

            e.printStackTrace();

        }


    }

    /**
     * 删除指定设备  1是删除成功，2删除失败
     * @param mac
     * @param request
     */
    @GetMapping("/delDevie")
    @Transactional
    public void DelDevice(String mac,HttpServletRequest request,HttpServletResponse response)throws Exception{
       // System.out.println();
      //  String macs= mac.split("_a")[0].toString();
        //从cookie中获取当前用户名
        Integer a=0;
        String names="";
        Cookie[] cookies=request.getCookies();
        if(cookies==null){
        }else {
            for (Cookie cookie : cookies) {
                if (
                        cookie.getName().equals("uname")) {
                    names = cookie.getValue();
                }
            }
        }
      //  System.out.println("1111111111"+mac+names);
        try {
            userService.delFromU(names, mac+",");
            deviceService.delDeice(names+",", mac);
            System.out.println("执行成功");
            a=1;
        }catch (Exception ex) {
            a=2;
            //回滚事务
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        add.setCode(a);
       String jsonstr=JSONObject.toJSONString(add);

        try {

            sendJsonData(response,jsonstr);

        } catch (Exception e) {

            e.printStackTrace();

        }

    }

    //返回json的处理方法
    protected void sendJsonData(HttpServletResponse response, String data) throws Exception{

        response.setContentType("text/html;charset=UTF-8");

        PrintWriter out = response.getWriter();

        out.println(data);

        out.flush();

        out.close();

    }
}
