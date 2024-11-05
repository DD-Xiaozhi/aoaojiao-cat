package com.xiaozhi.zhh.aoaojiao.admin;

import com.xiaozhi.zhh.aoaojiao.constant.CommonConstant;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 后台应用启动器
 *
 * @author DD
 * date    2024/10/21 23:34
 */
@SpringBootApplication(scanBasePackages = CommonConstant.APP_SCAN_PACKAGE_PATH)
public class AdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdminApplication.class, args);
    }
}
