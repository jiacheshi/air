package cn.sdyk.service;

import cn.sdyk.mapper.UserMapper;
import cn.sdyk.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;

public interface IUserService {
    //通过账号和密码查询用户
    User login(String uname,String upass);
    //注册用户
    int register(User user);
    //向用户中添加设备
    void usersBinding(String mac,String uname);

    //查询用户名是否重复
    int hintByUname(String uname);

    //根据用户名查询用户信息
    User getUserByUname(@Param("name") String uname);

    //删除设备
    int delFromU(String uname,String mac);
}
