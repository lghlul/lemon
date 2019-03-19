package com.demo.domain.DTO;

import com.demo.constant.CommonConstant;

/**
 * @ClassName ListTeacherDTO
 * @Description 分页查询教师信息参数
 * @Auther ll
 **/
public class ListTeacherDTO {
    /**
     * 页数
     */
    private Integer pageNumber;
    /**
     * 页码
     */
    private Integer pageSize;
    /**
     * 教师名称 模糊查询  可选
     */
    private String teacherName;
    /**
     * 数据库起始位置
     */
    private Integer offset;
    /**
     * 教师等级 可选
     */
    private Integer teacherLevel;

    public Integer getTeacherLevel() {
        return teacherLevel;
    }

    public void setTeacherLevel(Integer teacherLevel) {
        this.teacherLevel = teacherLevel;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }


    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

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


    public Integer getPageSize() {
        if (pageSize == null) {
            return CommonConstant.DEFAULT_PAGE_SIZE;
        }
        return pageSize;
    }
}
