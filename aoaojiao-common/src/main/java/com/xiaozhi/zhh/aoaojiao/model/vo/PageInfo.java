package com.xiaozhi.zhh.aoaojiao.model.vo;

import lombok.Builder;
import lombok.Data;

import java.util.Collections;
import java.util.List;

/**
 * 分页查询返回类
 *
 * @author DD
 * date    2024/10/21 18:20
 */
@Data
@Builder
public class PageInfo <T> {

    /**
     * 当前页
     */
    private Integer pageNum;

    /**
     * 每页大小
     */
    private Integer pageSize;

    /**
     * 总页数
     */
    private Integer totalPages;

    /**
     * 总记录数
     */
    private Long total;

    private List<T> content;

    public Integer getPageNum() {
        return pageNum + 1;
    }

    public static <T> PageInfo<T> empty() {
        return PageInfo.<T>builder()
                .total(0L)
                .totalPages(0)
                .content(Collections.emptyList())
                .build();
    }

}
