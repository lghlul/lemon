package com.demo.dto;

import com.demo.constant.CommonConstant;

/**
 * @ClassName ListTeacherClassDTO
 * @Description 分页查询教师课程信息
 * @Auther ll
 **/
public class ListTeacherClassDTO {
    /**
     * 页码
     */
    private Integer limit;
    /**
     * 起始位置
     */
    private Integer offset;

    /**
     * 教师编号  用户返回信息
     */
    private String teacherNumber;
    /**
     * 教师名称  用于返回信息
     */
    private String teacherName;
    /**
     * 课程编号  用户返回信息
     */
    private String classNumber;
    /**
     * 课程名称 用户返回信息
     */
    private String className;


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

    public String getTeacherNumber() {
        return teacherNumber;
    }

    public void setTeacherNumber(String teacherNumber) {
        this.teacherNumber = teacherNumber;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getClassNumber() {
        return classNumber;
    }

    public void setClassNumber(String classNumber) {
        this.classNumber = classNumber;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
