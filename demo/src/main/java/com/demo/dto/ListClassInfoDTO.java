package com.demo.dto;

import com.demo.common.PageQuery;

/**
 * @ClassName ListClassInfoDTO
 * @Description 分页查询课程
 * @Auther ll
 **/
public class ListClassInfoDTO extends PageQuery {
    /**
     * 名称 模糊查询 可选参数
     */
    private String className;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
