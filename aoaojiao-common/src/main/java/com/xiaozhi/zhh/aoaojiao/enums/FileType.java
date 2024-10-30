package com.xiaozhi.zhh.aoaojiao.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author DD
 * date    2024/10/21 18:20
 */
@Getter
@AllArgsConstructor
public enum FileType {

    JPEG("jpeg", ".jpeg"),
    JPG("jpg", ".jpg"),
    PNG("png", ".png"),
    GIF("gif", ".gif"),
    AMR("amr", ".amr"),
    MP3("mp3", ".mp3"),
    MP4("mp4", ".mp4"),
    PDF("pdf", ".pdf"),
    EXCEL("excel", ".xlsx"),
    ;


    private final String code;
    private final String suffix;
}
