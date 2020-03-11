package cn.sdyk.service;

import cn.sdyk.pojo.ServiceResponse;


public interface IphoneUserService {

    //登录
    public ServiceResponse login(String userName, String passWord);

    //登录获取列表


}
