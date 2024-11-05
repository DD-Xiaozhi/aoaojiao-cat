package com.xiaozhi.zhh.aoaojiao.mybatis.plus.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 雪花算法配置属性
 * @author DD
 * date    2024/11/5 15:12
 */
@Data
@ConfigurationProperties(prefix = "aoaojiao.id")
public class SnowflakeProperties {

    /**
     * 主机id
     */
    private Integer workId;

    /**
     * 数据中心id
     */
    private Integer dataCenterId;
}
