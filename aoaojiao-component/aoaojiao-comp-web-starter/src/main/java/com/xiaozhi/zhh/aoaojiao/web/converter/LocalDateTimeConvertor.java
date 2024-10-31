package com.xiaozhi.zhh.aoaojiao.web.converter;

import com.xiaozhi.zhh.aoaojiao.toolkit.DateFormat;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @author DD
 * date    2024/10/23 14:54
 */
public class LocalDateTimeConvertor implements Converter<String, LocalDateTime> {

    @Override
    public LocalDateTime convert(String dateStr) {
        dateStr = dateStr.trim();
        if (StringUtils.isBlank(dateStr)) return null;

        if(dateStr.matches("^\\d{4}-\\d{1,2}$")){
            return LocalDateTime.parse(dateStr, DateFormat.YYYY_MM_FORMATER);
        }else if(dateStr.matches("^\\d{4}-\\d{1,2}-\\d{1,2}$")){
            return LocalDateTime.parse(dateStr, DateFormat.SIMPLE_DATE_FORMATER);
        }else if(dateStr.matches("^\\d{4}-\\d{1,2}-\\d{1,2} {1}\\d{1,2}:\\d{1,2}$")){
            return LocalDateTime.parse(dateStr, DateFormat.YYYY_MM_DD_HH_MM_FORMATER);
        }else if(dateStr.matches("^\\d{4}-\\d{1,2}-\\d{1,2} {1}\\d{1,2}:\\d{1,2}:\\d{1,2}$")){
            return LocalDateTime.parse(dateStr, DateFormat.SIMPLE_DATE_TIME_FORMATER);
        }else {
            return null;
        }
    }
}
