package com.demo.dto;

import java.util.Date;

/**
 * @ClassName TeacherClazzDTO
 * @Description 添加教师课程信息
 * @Auther ll
 **/
public class TeacherClazzDTO {
    /**
     * 教师主键
     */
    private Integer teacherNumber;
    /**
     * 课程主键
     */
    private Integer clazzNumber;
    /**
     * 录入时间
     */
    private Date createTime;
    /**
     * 学年
     */
    private Integer term;

    /**
     * 课程名称
     */
    private String clazzName;
    /**
     * 教师名称
     */
    private String teacherName;


    public Integer getTeacherNumber() {
        return teacherNumber;
    }

    public void setTeacherNumber(Integer teacherNumber) {
        this.teacherNumber = teacherNumber;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getTerm() {
        return term;
    }

    public void setTerm(Integer term) {
        this.term = term;
    }


    public String getClazzName() {
        return clazzName;
    }

    public void setClazzName(String clazzName) {
        this.clazzName = clazzName;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public Integer getClazzNumber() {
        return clazzNumber;
    }

    public void setClazzNumber(Integer clazzNumber) {
        this.clazzNumber = clazzNumber;
    }
}
