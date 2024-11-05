package com.xiaozhi.zhh.aoaojiao.controller;

import com.xiaozhi.zhh.aoaojiao.entity.R;
import com.xiaozhi.zhh.aoaojiao.enums.DeleteFlag;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

/**
 * @author DiZhiXu
 * date    2024/10/21 23:26
 */
@RestController
@RequiredArgsConstructor
public class HelloWord {

    @GetMapping
    public R<Test> hello() {
        Test test = new Test();
        test.setTime(LocalDateTime.now());
        test.deleteFlag = DeleteFlag.DELETE;
        return R.success(test);
    }

    @GetMapping("test")
    public R<LocalDateTime> testLocalDateTime(@RequestParam String date) {
        return R.success(LocalDateTime.now());
    }

    @Data
    public static class Test {

        private LocalDateTime time;
        private DeleteFlag deleteFlag;
    }

}
