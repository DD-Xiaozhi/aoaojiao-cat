package com.xiaozhi.zhh.aoaojiao.auth.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author DD
 * date    2024/11/5 13:56
 */
@Data
@Component
@ConfigurationProperties(prefix = "aoaojiao.auth")
public class AuthProperties {

    /**
     * Token 过期时间（单位：秒）
     */
    private Integer expireTime;

    /**
     * 排除路径列表
     */
    private List<String> excludePathPatterns;
}
