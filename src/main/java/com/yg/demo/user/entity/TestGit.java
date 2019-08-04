package com.yg.demo.user.entity;

import javax.xml.crypto.Data;

/**
 * @ClassName TestGit
 * @Description TODO
 * @Author YGuang
 * @Date 2019/8/4 20:57
 * @Version 1.0
 **/

@lombok.Data
public class TestGit {
    private int id;
    private Data data;
    public String commit(){
        return "changged!!!";
    }
}
