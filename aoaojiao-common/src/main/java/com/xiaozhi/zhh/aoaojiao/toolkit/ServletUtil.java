package com.xiaozhi.zhh.aoaojiao.toolkit;

import cn.hutool.json.JSONUtil;
import com.xiaozhi.zhh.aoaojiao.enums.GeneralErrorCode;
import com.xiaozhi.zhh.aoaojiao.exception.BaseException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.io.IOException;

/**
 * @author DD
 * date    2024/05/31 11:13
 */
@Slf4j
@UtilityClass
public class ServletUtil {

    public static void out(HttpServletResponse response, Object data)  {
        try {
            // 允许跨域
            response.setHeader("Access-Control-Allow-Origin", "*");
            // 允许自定义请求头token(允许head跨域)
            response.setHeader("Access-Control-Allow-Headers", "*");
            response.setHeader("Content-type", "application/json;charset=UTF-8");
            response.getWriter().print(JSONUtil.toJsonStr(data));
        } catch (IOException e) {
            log.error("{}", e.getMessage(), e);
            throw new BaseException(GeneralErrorCode.SYS_ERROR);
        }
    }

    /**
     * 获取 HttpServletRequest, 不支持异步获取
     * @return 成功返回 {@link HttpServletRequest}, 失败抛出异常
     */
    public static HttpServletRequest getRequest() {
        ServletRequestAttributes attributes = (ServletRequestAttributes)
                RequestContextHolder.getRequestAttributes();
        if (null == attributes) {
            throw new BaseException(GeneralErrorCode.INVALID_REQUEST);
        }

        return attributes.getRequest();
    }
}