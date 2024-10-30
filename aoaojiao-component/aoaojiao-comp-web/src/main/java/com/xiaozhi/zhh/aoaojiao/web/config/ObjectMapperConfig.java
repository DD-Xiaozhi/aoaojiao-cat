package com.xiaozhi.zhh.aoaojiao.web.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import com.xiaozhi.zhh.aoaojiao.toolkit.DateFormat;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * 配置 jackson
 * @author DD
 * date    2024/10/23 11:26
 */
@Configuration
public class ObjectMapperConfig {

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper omp = new ObjectMapper();

        // 注册时间转换器，处理Java8时间类型
        JavaTimeModule jtm = new JavaTimeModule();
        jtm.addSerializer(LocalTime.class, new LocalTimeSerializer(DateFormat.SIMPLE_TIME_FORMATER));
        jtm.addSerializer(LocalDate.class, new LocalDateSerializer(DateFormat.SIMPLE_DATE_FORMATER));
        jtm.addDeserializer(LocalDate.class, new LocalDateDeserializer(DateFormat.SIMPLE_DATE_FORMATER));
        jtm.addDeserializer(LocalTime.class, new LocalTimeDeserializer(DateFormat.SIMPLE_TIME_FORMATER));
        jtm.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateFormat.SIMPLE_DATE_TIME_FORMATER));
        jtm.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DateFormat.SIMPLE_DATE_TIME_FORMATER));

        // 其它类型处理
        SimpleModule sm = new SimpleModule();
        sm.addSerializer(Long.TYPE, ToStringSerializer.instance);
        sm.addSerializer(Long.class, ToStringSerializer.instance);
        sm.addSerializer(BigInteger.class, ToStringSerializer.instance);
        sm.addSerializer(BigDecimal.class, ToStringSerializer.instance);

        return omp.registerModules(jtm, sm);
    }
}
