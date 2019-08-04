package com.yg.demo.user.service;

import com.yg.demo.user.utils.AESUtil;
import com.yg.demo.user.utils.RSAUtil;

public interface UserService {
    String login(String data,String key) throws Exception;

}
