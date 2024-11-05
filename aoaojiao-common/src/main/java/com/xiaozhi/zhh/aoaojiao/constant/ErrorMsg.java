package com.xiaozhi.zhh.aoaojiao.constant;

import lombok.experimental.UtilityClass;

/**
 * 错误码常量类
 *
 * @author DD
 * date    2024/8/4 19:42
 */
@UtilityClass
public class ErrorMsg {

    public static final String SUCCESS = "00000";
    public static final String SERVER_BUSY = "服务器繁忙，请稍后再试";
    public static final String UNAUTHORIZED = "未授权";
    public static final String INVALID_TOKEN = "Invalid token";
    public static final String TOKEN_EXPIRED = "token 已过期";
    public static final String NOT_AUTHORIZED = "权限受限，请联系管理员";
    public static final String BAD_REQUEST = "无效请求";
    public static final String INVALID_PARAMS = "无效参数";
    public static final String NOT_ALLOW_OPERATE = "无权限操作";
    public static final String NOT_FOUND = "Not found";
    public static final String DATA_ERROR = "数据异常！！！";
    public static final String NOT_REPEAT = "请勿重复提交";

}
