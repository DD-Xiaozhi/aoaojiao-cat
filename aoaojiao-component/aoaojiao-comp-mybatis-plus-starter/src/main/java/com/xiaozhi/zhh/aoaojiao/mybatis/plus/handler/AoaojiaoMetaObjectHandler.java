package com.xiaozhi.zhh.aoaojiao.mybatis.plus.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.xiaozhi.zhh.aoaojiao.constant.CommonConstant;
import com.xiaozhi.zhh.aoaojiao.enums.DeleteFlag;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * 自定义 mybatis-plus 填充内容
 *
 * @author DD
 * date    2024/10/30 21:38
 */
@Component
public class AoaojiaoMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject, CommonConstant.DB.CREATE_TIME, LocalDateTime.class, LocalDateTime.now());
        this.strictInsertFill(metaObject, CommonConstant.DB.UPDATE_TIME, LocalDateTime.class, LocalDateTime.now());
        this.strictInsertFill(metaObject, CommonConstant.DB.DELETE_FLAG, Integer.class, DeleteFlag.DELETE.getCode());
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject, CommonConstant.DB.UPDATE_TIME, LocalDateTime.class, LocalDateTime.now());
    }
}
