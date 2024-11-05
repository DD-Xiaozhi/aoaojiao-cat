package com.xiaozhi.zhh.aoaojiao.toolkit;

import lombok.experimental.UtilityClass;

import java.time.format.DateTimeFormatter;

/**
 * 日期格式化
 *
 * @author DD
 * date    2024/10/23 11:30
 */
@UtilityClass
public class DateFormat {

    public static final String YYYY_MM_PATTERN = "yyyy-MM";
    public static final String SIMPLE_TIME_PATTERN = "HH:mm:ss";
    public static final String SIMPLE_DATE_PATTERN = "yyyy-MM-dd";
    public static final String YYYY_MM_DD_HH_MM_PATTERN = "yyyy-MM-dd HH:mm";
    public static final String SIMPLE_DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
    public static final String YYYY_MM_DD_HH_MM_SS_SSS_PATTERN = "yyyyMMddHHmmssSSS";
    public static final String DATE_TIME_ISO_SSS_PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSSX";
    public static final String DATE_TIME_ISO_OOO_PATTERN = "yyyy-MM-dd'T'HH:mm:ss.000+0000";

    public static final DateTimeFormatter YYYY_MM_FORMATER = DateTimeFormatter.ofPattern(YYYY_MM_PATTERN);
    public static final DateTimeFormatter SIMPLE_DATE_FORMATER = DateTimeFormatter.ofPattern(SIMPLE_DATE_PATTERN);
    public static final DateTimeFormatter SIMPLE_TIME_FORMATER = DateTimeFormatter.ofPattern(SIMPLE_TIME_PATTERN);
    public static final DateTimeFormatter YYYY_MM_DD_HH_MM_FORMATER = DateTimeFormatter.ofPattern(YYYY_MM_DD_HH_MM_PATTERN);
    public static final DateTimeFormatter SIMPLE_DATE_TIME_FORMATER = DateTimeFormatter.ofPattern(SIMPLE_DATE_TIME_PATTERN);
    public static final DateTimeFormatter DATE_TIME_ISO_OOO_FORMATER = DateTimeFormatter.ofPattern(DATE_TIME_ISO_OOO_PATTERN);
    public static final DateTimeFormatter DATE_TIME_ISO_SSS_FORMATER = DateTimeFormatter.ofPattern(DATE_TIME_ISO_SSS_PATTERN);
    public static final DateTimeFormatter YYYY_MM_DD_HH_MM_SS_SSS_FORMATER = DateTimeFormatter.ofPattern(YYYY_MM_DD_HH_MM_SS_SSS_PATTERN);
}
