package com.demo.dto;

import java.util.Date;

/**
 * @ClassName TeacherDTO
 * @Description 添加教师传输对象
 * @Auther ll
 **/
public class TeacherDTO {
    /**
     * 主键
     */
    private Integer teacherNumber;
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
    private int teacherLevel;
    /**
     * 录入时间
     */
    private Date createTime;

    public Integer getTeacherNumber() {
        return teacherNumber;
    }

    public void setTeacherNumber(Integer teacherNumber) {
        this.teacherNumber = teacherNumber;
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

    public int getTeacherLevel() {
        return teacherLevel;
    }

    public void setTeacherLevel(int teacherLevel) {
        this.teacherLevel = teacherLevel;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
