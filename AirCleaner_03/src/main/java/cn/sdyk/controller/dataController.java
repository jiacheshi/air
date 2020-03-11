package cn.sdyk.controller;

import cn.sdyk.utils.IoTDemoPubSubDemo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

@Controller
@RequestMapping("/data")
public class dataController {
    @Autowired
    IoTDemoPubSubDemo ioTDemoPubSubDemo;

    @RequestMapping("/show")
    public void Show(HttpServletResponse response)throws Exception{
       // String jsonStr=ioTDemoPubSubDemo.monitor();
       // response.getWriter().write(jsonStr);
        System.out.println("调用了show方法");
    }

    @RequestMapping("/test3")

    public String test(HttpServletResponse response){

//        User user = new User();
//
//        user.setId(1);
//
//        user.setSex("12");
//
//        user.setUsername("phpfzh-test3");
//
//        String date = JSON.toJSONString(user);

        try {

          //  sendJsonData(response, date);

        } catch (Exception e) {

            e.printStackTrace();

        }

        return null;

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
