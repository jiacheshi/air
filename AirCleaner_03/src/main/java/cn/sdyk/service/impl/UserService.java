package cn.sdyk.service.impl;

import cn.sdyk.mapper.UserMapper;
import cn.sdyk.pojo.User;
import cn.sdyk.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    User user;

    /**
     * 登录功能
     * @param uname
     * @param upass
     * @return 返回一个用户
     */
    @Override
    public User login(String uname, String upass) {
       user=userMapper.login(uname,upass);

        return user;
    }

    /**
     * 注册用户
     * @param user
     * @return
     */
    @Override
    public int register(User user) {
        int a=userMapper.register(user);
        return a;
    }

    @Override
    public void usersBinding(String mac,String uname) {

        userMapper.usersBinding(mac,uname);
    }

    @Override
    public int hintByUname(String uname) {

        if(uname.equals("")){
            return 2;
        }else{

            String h = userMapper.hintByUname(uname);
            if (h != null) {
                //表示用户名被占用
                return 1;
            } else {
                return 0;
            }
        }
    }

    @Override
    public User getUserByUname(String uname) {

        return userMapper.getUserByUname(uname);
    }

    @Override
    public int delFromU(String uname, String mac) {
       return userMapper.delForUser(uname,mac);

    }

}
