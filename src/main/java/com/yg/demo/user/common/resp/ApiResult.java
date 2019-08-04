package com.yg.demo.user.common.resp;

import com.yg.demo.user.common.constants.Constants;
import lombok.Data;

/**
 * @ClassName ApiResult
 * @Description TODO
 * @Author YGuang
 * @Date 2019/7/23 15:26
 * @Version 1.0
 **/

@Data
public class ApiResult<T> {
    private int code=Constants.RESP_STATUS_OK;
    private String message;
    private T data;
}
