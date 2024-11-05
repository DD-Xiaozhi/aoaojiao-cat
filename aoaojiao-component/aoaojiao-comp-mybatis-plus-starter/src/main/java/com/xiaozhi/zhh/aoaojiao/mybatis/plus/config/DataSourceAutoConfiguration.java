package com.xiaozhi.zhh.aoaojiao.mybatis.plus.config;

import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.xiaozhi.zhh.aoaojiao.mybatis.plus.properties.SnowflakeProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * MybatisPlus 配置类
 *
 * @author DD
 * date    2024/10/30 21:34
 */
@Configuration
@EnableConfigurationProperties(SnowflakeProperties.class)
public class DataSourceAutoConfiguration {

    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        // 乐观锁插件
        interceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor());
        // 分页插件 - 存在多个插件，分页要放在最后
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;
    }

    /**
     * 自定义组件生成策略 -> 使用 hutool 提供的雪花算法
     * <p> 后续可改用 Seata 改进的雪花算法 <p/>
     * @return 雪花id 生成器
     */
    @Bean
    public IdentifierGenerator identifierGenerator(SnowflakeProperties snowflakeProperties) {
        return entity -> IdUtil.getSnowflake(snowflakeProperties.getWorkId(), snowflakeProperties.getDataCenterId()).nextId();
    }

}
