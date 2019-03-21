package com.demo.dto;

import com.demo.common.PageQuery;

/**
 * @ClassName ClazzQueryDTO
 * @Description 分页查询课程
 * @Auther ll
 **/
public class ClazzQueryDTO extends PageQuery {
    /**
     * 名称 模糊查询 可选参数
     */
    private String clazzName;

    public String getClazzName() {
        return clazzName;
    }

    public void setClazzName(String clazzName) {
        this.clazzName = clazzName;
    }
}
