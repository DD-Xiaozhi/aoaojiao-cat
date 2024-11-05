package com.xiaozhi.zhh.aoaojiao.model.dto;

import lombok.Data;

/**
 * 分页查询DTO
 *
 * @author DD
 * date    2024/10/21 18:20
 */
@Data
public class PageQuery {

    /**
     * 当前页
     */
    private Integer page = 1;

    /**
     * 每页大小
     */
    private Integer size = 10;

    /**
     * 搜索内容
     */
    private String searchValue;

    public Integer getPage() {
        return page - 1;
    }
}
