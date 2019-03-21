package com.demo.common;

import com.demo.constant.CommonConstant;

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

    /**
     * 排序字段
     */
    private String sort;

    /**
     * 排序类型
     */
    private String sortDir;

    /**
     * 是否需要分页
     */
    private Boolean paging;

    public Boolean getPaging() {
        if (paging == null) {
            return true;
        }
        return paging;
    }

    public void setPaging(Boolean paging) {
        this.paging = paging;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getSortDir() {
        if (sortDir == null) {
            return CommonConstant.DEFAULT_SORTDIR;
        }
        return sortDir;
    }

    public void setSortDir(String sortDir) {
        this.sortDir = sortDir;
    }


    public Integer getLimit() {
        if (limit == null) {
            return CommonConstant.DEFAULT_LIMIT;
        }
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getOffset() {
        if (offset == null) {
            return CommonConstant.DEFAULT_OFFSET;
        }
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }
}
