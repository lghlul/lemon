package com.demo.dto;

import com.demo.constant.CommonConstant;

/**
 * @ClassName ListStudentDTO
 * @Description 分页查询学生参数
 * @Auther ll
 **/
public class ListStudentDTO {
    /**
     * 页码
     */
    private Integer limit;
    /**
     * 名称 模糊查询 可选参数
     */
    private String studentName;
    /**
     * 起始位置
     */
    private Integer offset;


    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }
}
