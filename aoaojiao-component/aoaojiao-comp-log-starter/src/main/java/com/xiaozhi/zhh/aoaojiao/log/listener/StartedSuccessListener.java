package com.xiaozhi.zhh.aoaojiao.log.listener;


import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ansi.AnsiColor;
import org.springframework.boot.ansi.AnsiOutput;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * 项目启动成功打印日志监听器
 *
 * @author DiZhiXu
 * date    2024/10/21 23:37
 */
@Slf4j
public class StartedSuccessListener implements ApplicationListener<ApplicationReadyEvent> {

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        // 获取服务端口号
        String port = event.getApplicationContext().getEnvironment().getProperty("server.port");
        // 服务url
        String serverUrl = String.format("http://%s:%s", "127.0.0.1", port);
        log.info(AnsiOutput.toString(AnsiColor.BRIGHT_BLUE, "your project server started at: ", serverUrl));
        // log.info(AnsiOutput.toString(AnsiColor.BRIGHT_BLUE, "your project server's doc started at:", serverUrl + "/doc.html"));
        log.info(AnsiOutput.toString(AnsiColor.BRIGHT_YELLOW, "your project server has started successfully!"));
    }
}