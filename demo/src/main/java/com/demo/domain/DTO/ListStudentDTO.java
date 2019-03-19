package com.demo.domain.DTO;

import com.demo.constant.CommonConstant;

/**
 * @ClassName ListStudentDTO
 * @Description 分页查询学生参数
 * @Auther ll
 **/
public class ListStudentDTO {
    /**
     * 页数
     */
    private Integer pageNumber;
    /**
     * 页码
     */
    private Integer pageSize;
    /**
     * 名称 模糊查询 可选参数
     */
    private String studentName;
    /**
     * 数据库起始位置
     */
    private Integer offset;

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getPageNumber() {
        if (pageNumber == null) {
            return CommonConstant.DEFAULT_PAGE_NUMBER;
        }
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Integer getPageSize() {
        if (pageSize == null) {
            return CommonConstant.DEFAULT_PAGE_SIZE;
        }
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }
}
