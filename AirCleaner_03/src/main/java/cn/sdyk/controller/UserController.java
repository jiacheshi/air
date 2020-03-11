package cn.sdyk.controller;

import cn.sdyk.config.StrSplit;
import cn.sdyk.mapper.UserMapper;
import cn.sdyk.pojo.Device;
import cn.sdyk.pojo.User;
import cn.sdyk.service.impl.DeviceService;
import cn.sdyk.service.impl.UserService;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    UserService userService;
    @Autowired
    DeviceService deviceService;
    @Autowired
    User users;
    @Autowired
    Device device;
    @Autowired
    StrSplit strSplit;

//    static String mac;
//    static String uname;

    /**
     * 登录逻辑
     * @param user 用户
     * @param request
     * @return
     */
    @PostMapping("/login")
    public String login(User user, HttpServletRequest request, HttpServletResponse response)throws Exception {

      //  System.out.println(user.getUname()+user.getUname());
       String info = null;
        // 登录认证，认证成功后将用户信息放到cookie中
        users=userService.login(user.getUname(),user.getUpass());

        if (users!=null) {
           // System.out.println("用户登录线程："+Thread.currentThread().getName());
            //将用户存放到Cookie中
         //   request.getSession().setAttribute("userInfo",users);
          // Cookie unamecookie=new Cookie("uname",user.getUname());

        //    System.out.println(users.getId());
            Cookie cookie=new Cookie("uname",users.getUname());
            cookie.setMaxAge(60*60*60*60);
            response.addCookie(cookie);
        //    mac=users.getMac();
       //     uname=users.getMac();
            info = "HomePage";
        } else {
            info = "error";

        }
        return info;

    }


//    @PostMapping("/loging")
//    public String login(User user,HttpServletRequest request,HttpServletResponse response)   {
//        HttpSession session=request.getSession();
//        users=userService.login(user.getUname(),user.getUpass());
//        if(users==null){
//           return "error";
//        }else {
//            System.out.println(session.getId());
//            sesionid=session.getId();
////            Cookie cookie=new Cookie("sessionId",session.getId());
////            cookie.setMaxAge(60*60*60*24);
////            response.addCookie(cookie);
//            redisTemplate.opsForValue().set(session.getId(), JSON.toJSONString(users));
////        AccountV accountVo= BeanCopy.of(accountDto,new AccountVo()).copy(BeanUtils::copyProperties).get();
////        accountVo.setAceptRegion(AcceptRegionEnum.getDescByValue(accountDto.getAceptRegion()));
//
//            return "HomePage";
//        }
//    }

    /**
     * 登出操作
     * @param request
     * @return
     */
    @GetMapping("/loginout")
    public String loginout(HttpServletRequest request,HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            return "login";
        }else {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("uname")) {
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);//重新响应
                }
            }
            return "login";
        }

    }

    /**
     * 注册用户
     * @param user
     * @return 成功后返回到登录界面
     */
    @PostMapping("/register")
    public String Register(User user){
       // System.out.println(user.getMac());
       // System.out.println(user.getEmail());
        int i=userService.register(user);
        if(i>0){
            return "login";
        }else {
            return "error";
        }


    }

    /**
     * 主界面调用该方法
     * @param request
     * @param response 返回绑定设备的昵称和MAC值
     * @throws Exception
     */
    @PostMapping("/deviceList")
    public void DeviceList(HttpServletRequest request, HttpServletResponse response){


//        String accessToken = (String) redisTemplate.opsForValue().get(sesionid);
//        JSONObject jsonObject=JSONObject.parseObject(accessToken);
        //获取用户mac列表

        String macList ="";
        String a="";
        Cookie[] cookies=request.getCookies();
        if(cookies==null){

        }else {
            for (Cookie cookie : cookies) {
                if (
                        cookie.getName().equals("uname")) {
                    a = cookie.getValue();
                }
            }
        }
        System.err.println(a);

      //  HttpSession session = request.getSession();



        //获取用户绑定设备字符串并解析
        if(a!=null) {

          User user=userService.getUserByUname(a);
                macList = user.getMac();


        }
        String[] alist={"暂无登录"};
        if(macList!=null) {
             alist = strSplit.split(macList);
        }
        List<Device> lists=new ArrayList<Device>();
        for (int i = 0; i < alist.length; i++) {

         device=deviceService.selByMac(alist[i]);

            lists.add(device);
        }


        String date = JSON.toJSONString(lists);
        try {

            sendJsonData(response,date);

        } catch (Exception e) {

            e.printStackTrace();

        }

    }

    /**
     * 异步验证用户名是否重复
     * @param
     */
    @PostMapping("/hint")
    public void HintForUname(String uname,HttpServletResponse response){
       // System.out.println(uname);
        int h=userService.hintByUname(uname);


        String date = JSON.toJSONString(h);
        try {

            sendJsonData(response,date);
            // System.out.println(date);

        } catch (Exception e) {

            e.printStackTrace();

        }

    }
    //返回json的处理方法




    //如已经登录直接跳转deviceList到主界面
    @GetMapping("/userloginpage")
    public static String page(HttpServletRequest request){
        String a="";
        Cookie[] cookies=request.getCookies();
        if(cookies==null){
            return "login";
        }
        for(Cookie cookie:cookies){
               if(cookie.getName().equals("uname")) {
               a=cookie.getValue();
           }
        }
if(a=="") {
    return "login";
}else {
    return "HomePage";
}

    }
    //跳转到主界面
    @GetMapping("/homePage")
    public String HomePage(){
        return "homePage";
    }

    //跳转到注册界面
    @GetMapping("/registerPage")
    public String registerPage(){
        return "register";
    }

    @GetMapping("/log")
    public String log(){
        return "login";
    }


    @GetMapping("getName")
    public void GetName(HttpServletRequest request,HttpServletResponse response){
        String a="";
        Cookie[] cookies=request.getCookies();
        if(cookies==null){
            a="暂无登录";

        }else {
            for (Cookie cookie : cookies) {
                if (
                        cookie.getName().equals("uname")) {
                    a = cookie.getValue();
                }
            }
        }
        String date = JSON.toJSONString(a);
        try {

            sendJsonData(response,date);
            // System.out.println(date);

        } catch (Exception e) {

            e.printStackTrace();

        }


    }
    @GetMapping("/downloadPage")
    public String DownloadPage(){
        return "downloadPage";

    }
    protected void sendJsonData(HttpServletResponse response, String data) throws Exception{

        response.setContentType("text/html;charset=UTF-8");

        PrintWriter out = response.getWriter();

        out.println(data);

        out.flush();

        out.close();

    }


}
