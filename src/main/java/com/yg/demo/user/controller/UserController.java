package com.yg.demo.user.controller;

import com.alibaba.fastjson.JSON;
import com.yg.demo.user.common.constants.Constants;
import com.yg.demo.user.common.resp.ApiResult;
import com.yg.demo.user.entity.LoginInfo;
import com.yg.demo.user.entity.User;
import com.yg.demo.user.service.UserService;
import com.yg.demo.user.service.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName UserController
 * @Description TODO
 * @Author YGuang
 * @Date 2019/6/28 0:21
 * @Version 1.0
 **/
@Slf4j
@RestController
public class UserController {
    @Qualifier("UserServiceImpl")
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * @ClassName
     * @Author Yguang
     * @Date 2019/7/23 15:48
     * @Description 因为和app交互，数据返回格式APIRESULT应该规范。使用一套模板前后端一致。
     */
    //要求数据是application_Json
    @RequestMapping(value = "/login",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiResult <String> login(@RequestBody LoginInfo loginInfo) {
        //--------是否可以使用注入API形式------------//
       ApiResult <String> resp = new ApiResult <>();
        try {
            String data=loginInfo.getData();
            String key=loginInfo.getAESkey();
            String token = userService.login(data,key);
            resp.setData(token);
        }catch (Exception e){
            log.error("Fail to login",e);
            resp.setCode(Constants.RESP_STATUS_INTERNAL_ERROR);
            resp.setMessage("server error");
        }


        return  resp;

    }

}
