package cn.sdyk.controller;

import cn.sdyk.config.StrSplit;
import cn.sdyk.mapper.UserMapper;
import cn.sdyk.pojo.Device;
import cn.sdyk.pojo.User;
import cn.sdyk.service.impl.DeviceService;
import cn.sdyk.service.impl.UserService;
import com.alibaba.fastjson.JSON;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class textController {

    @Autowired
    UserService userService;
    @Autowired
    StrSplit strSplit;
    @Autowired
    DeviceService deviceService;
    @Autowired
    Device device;



    @PostMapping("/deviceList2")
    public void DeviceList(HttpServletRequest request, HttpServletResponse response) {


//        String accessToken = (String) redisTemplate.opsForValue().get(sesionid);
//        JSONObject jsonObject=JSONObject.parseObject(accessToken);
        //获取用户mac列表

        String macList = "";

            User user = userService.getUserByUname("a");

            macList = user.getMac();


        System.out.println(user.getMac());
        String[] alist = {"76A32CE97907","76A32CE97908"};
        List<Device> lists = new ArrayList<Device>();
        for (int i = 0; i < alist.length; i++) {

            device = deviceService.selByMac(alist[i]);

            lists.add(device);
        }


        String date = JSON.toJSONString(lists);
        try {

            sendJsonData(response, date);

        } catch (Exception e) {

            e.printStackTrace();

        }

    }

    protected void sendJsonData(HttpServletResponse response, String data) throws Exception {

        response.setContentType("text/html;charset=UTF-8");

        PrintWriter out = response.getWriter();

        out.println(data);

        out.flush();

        out.close();

    }
    @GetMapping("/mianPage")
    public String MianPage(){
        return "mianPage";

    }

    @GetMapping("/equipmentPage")
    public String EquipmentPage(){
        return "equipmentPage";

    }

    @GetMapping("/pageLogin")
    public String PageLogin(){
        return "pageLogin";

    }
}