package com.demo.dto;

import com.demo.constant.CommonConstant;

/**
 * @ClassName ListTeacherDTO
 * @Description 分页查询教师信息参数
 * @Auther ll
 **/
public class ListTeacherDTO {
    /**
     * 页码
     */
    private Integer limit;
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


    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
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

    public Integer getTeacherLevel() {
        return teacherLevel;
    }

    public void setTeacherLevel(Integer teacherLevel) {
        this.teacherLevel = teacherLevel;
    }
}
