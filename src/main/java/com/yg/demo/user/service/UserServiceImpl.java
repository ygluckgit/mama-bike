package com.yg.demo.user.service;

import com.yg.demo.user.utils.AESUtil;
import com.yg.demo.user.utils.RSAUtil;

import java.util.Map;

/**
 * @ClassName UserServiceImpl
 * @Description TODO
 * @Author YGuang
 * @Date 2019/7/23 15:04
 * @Version 1.0
 **/


public class UserServiceImpl implements UserService{
    @Override
    //key为结果RSA加密的AES的key
    public String login(String data,String key) throws Exception {
        //获取RSA私钥
        Map<String, String> keyMap=RSAUtil.createKeys();
        String  privateKey = keyMap.get("privateKey");
        //得到经过RSA解密的密钥key
        String AESkey=RSAUtil.privateDecrypt(key,RSAUtil.getPrivateKey(privateKey));
        //得到AES解密后的数据
        String dedata=AESUtil.decrypt(data,AESkey);
        return dedata;
    }


}
