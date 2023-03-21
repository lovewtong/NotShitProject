package com.Zlibrary.entity.chat;

import lombok.Data;
import org.springframework.http.HttpMethod;

/**
 * @className: ExecuteRet
 * @description: TODO 类描述
 * @author: sy
 * @date: 2023-03-21
 **/
@Data
public class ExecuteRet {

    // 是否成功
    private final boolean success;
    // 返回的内容
    private final String respStr;
    // 请求的地址
    private final HttpMethod method;
    // 状态码
    private final int statesCode;


}
