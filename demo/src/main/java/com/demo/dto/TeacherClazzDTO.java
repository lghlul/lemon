package com.demo.dto;

import java.util.Date;

/**
 * @ClassName TeacherClazzDTO
 * @Description 教师课程DTO
 * @Auther ll
 **/
public class TeacherClazzDTO {
    /**
     * 教师主键
     */
    private Integer teacherNum;
    /**
     * 课程主键
     */
    private Integer clazzNum;
    /**
     * 录入时间
     */
    private Date createTime;
    /**
     * 课程名称
     */
    private String clazzName;
    /**
     * 教师名称
     */
    private String teacherName;


    public Integer getTeacherNum() {
        return teacherNum;
    }

    public void setTeacherNum(Integer teacherNum) {
        this.teacherNum = teacherNum;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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

    public Integer getClazzNum() {
        return clazzNum;
    }

    public void setClazzNum(Integer clazzNum) {
        this.clazzNum = clazzNum;
    }
}
