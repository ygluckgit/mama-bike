package com.yg.demo.user.entity;

import lombok.Data;

/**
 * @ClassName LoginInfo
 * @Description TODO
 * @Author YGuang
 * @Date 2019/7/23 15:55
 * @Version 1.0
 **/

@Data
public class LoginInfo {
    //加密后的密文
    private String data;
    //加密的AES的密钥
    private String AESkey;

}
