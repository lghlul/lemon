package com.demo.model;

import java.util.Date;

/**
 * @ClassName TeacherConveter
 * @Description 表 teacher
 * @date 2019年3月18日
 * @Auther ll
 **/
public class Teacher {
    /**
     * 主键
     */
    private Integer teacherNum;
    /**
     * 教师号
     */
    private String teacherId;
    /**
     * 教师名称
     */
    private String teacherName;
    /**
     * 教师等级  1普通教师  2教务主任
     */
    private Integer teacherLevel;
    /**
     * 录入时间
     */
    private Date createTime;

    public Integer getTeacherNum() {
        return teacherNum;
    }

    public void setTeacherNum(Integer teacherNum) {
        this.teacherNum = teacherNum;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public Integer getTeacherLevel() {
        return teacherLevel;
    }

    public void setTeacherLevel(Integer teacherLevel) {
        this.teacherLevel = teacherLevel;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
