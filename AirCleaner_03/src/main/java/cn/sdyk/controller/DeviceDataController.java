package cn.sdyk.controller;

import cn.sdyk.config.StrSplit;
import cn.sdyk.mapper.DeviceDataMapper;
import cn.sdyk.pojo.status;
import cn.sdyk.service.impl.DeviceDataService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@Controller
public class DeviceDataController {
    @Autowired
    DeviceDataService deviceDataService;
    @Autowired
    StrSplit strSplit;
    @Autowired
    cn.sdyk.pojo.status status;

    /**
     * 判断设备是否在线的方法
     * @param request
     * @param response
     * @throws Exception
     */
    @PostMapping("deviceStatus")
    public void DeviceStatus(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //取出cookis中name的值
        String names = "";
        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
        } else {
            for (Cookie cookie : cookies) {
                if (
                        cookie.getName().equals("uname")) {
                    names = cookie.getValue();
                }
            }
        }
        List<status> data = deviceDataService.StrOnline(names);


        System.out.println(names);

        String jsonstr = JSONObject.toJSONString(data);
        sendJsonData(response, jsonstr);


    }

    //返回json的处理方法
    protected void sendJsonData(HttpServletResponse response, String data) throws Exception {

        response.setContentType("text/html;charset=UTF-8");

        PrintWriter out = response.getWriter();

        out.println(data);

        out.flush();

        out.close();

    }
}