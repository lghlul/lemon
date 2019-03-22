package com.demo.common;

import java.util.List;

/**
 * @ClassName PageResult
 * @Description 返回的分页对象
 * @Auther lgh_l
 * @Date 2019/3/21 17:47
 * @Version 1.0
 **/
public class PageResult {
    /**
     * 分页数据列表
     */
    private List list;
    /**
     * 总记录数
     */
    private Long totalCount;
    /**
     * 总页数
     */
    private Integer totalPage;

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public Long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
    }
}
