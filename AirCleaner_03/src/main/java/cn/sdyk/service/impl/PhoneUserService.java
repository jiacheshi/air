package cn.sdyk.service.impl;

import cn.sdyk.mapper.UserMapper;
import cn.sdyk.pojo.ServiceResponse;
import cn.sdyk.pojo.User;
import cn.sdyk.service.IphoneUserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;

public class PhoneUserService implements IphoneUserService {
    @Autowired
     UserMapper userMapper;
    @Autowired
    User user;
    @Autowired
    ServiceResponse serviceResponse;

    ObjectMapper mapper = new ObjectMapper();
    ObjectNode nodes = mapper.createObjectNode();
    @Override
    public ServiceResponse login(String userName, String passWord) {
        user=userMapper.login(userName,passWord);
        if (user==null){
            //登录失败
            serviceResponse.setErrorCode(1);
            serviceResponse.setMessage("参数设置失败");
        }else {
            //登录成功
            serviceResponse.setErrorCode(0);
            serviceResponse.setMessage("参数设置成功");
            nodes.put("name",userName);
            serviceResponse.setResultNode(nodes);
        }
        return serviceResponse;
    }
}
