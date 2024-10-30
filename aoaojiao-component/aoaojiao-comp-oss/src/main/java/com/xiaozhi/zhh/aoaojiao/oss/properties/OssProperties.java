package com.xiaozhi.zhh.aoaojiao.oss.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Oss 配置类
 *
 * @author DD
 * date    2024/10/30 22:38
 */
@Data
@Component
@ConfigurationProperties(prefix = "aoaojiao.oss")
public class OssProperties {


}
