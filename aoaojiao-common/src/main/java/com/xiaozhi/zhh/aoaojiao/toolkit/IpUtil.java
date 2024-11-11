package com.xiaozhi.zhh.aoaojiao.toolkit;

import cn.hutool.core.map.MapUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.net.InetAddress;
import java.util.Enumeration;
import java.util.Map;
import java.util.Objects;

/**
 * @author xiaozhi
 */
public class IpUtil {

    public static String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
            if ("127.0.0.1".equals(ip)) {
                // 根据网卡取本机配置的 IP
                InetAddress inet = null;
                try {
                    inet = InetAddress.getLocalHost();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (inet != null) {
                    ip = inet.getHostAddress();
                }
            }
        }
        // 多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        if (ip != null && ip.length() > 15) {
            if (ip.indexOf(",") > 0) {
                ip = ip.substring(0, ip.indexOf(","));
            }
        }
        if (ip == null) {
            return "127.0.0.1";
        }
        return ip;
    }

    /**
     *  获取请求头MAP
     *  @param request
     *  @return
     *
     */
    public static Map<String, String> getRequestHeaderMap(HttpServletRequest request) {
        Map<String, String> result = MapUtil.newHashMap();
        if (Objects.nonNull(request)) {
            Enumeration<String> headerNames = request.getHeaderNames();
            while (headerNames.hasMoreElements()) {
                String headerName = headerNames.nextElement();
                String headerValue = request.getHeader(headerName);
                result.put(headerName, headerValue);
            }
        }
        return result;
    }


    /**
     * 获取响应头MAP
     *
     * @param response
     * @return
     */
    public static Map<String, String> getResponseHeaderMap(HttpServletResponse response) {
        Map<String, String> result = MapUtil.newHashMap();
        if (Objects.nonNull(response)) {
            String contentType = response.getContentType();
            result.put("contentType", contentType);
        }
        return result;
    }
}
