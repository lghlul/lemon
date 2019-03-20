package com.demo.common;

/**
 * @ClassName PageQuery
 * @Description 分页
 * @Auther lgh_l
 * @Date 2019/3/20 9:39
 * @Version 1.0
 **/
public class PageQuery {
    /**
     * 页码
     */
    private Integer limit;
    /**
     * 起始位置
     */
    private Integer offset;

    //排序字段

    //排序类型

    //是否需要分页


    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }
}
