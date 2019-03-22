package com.demo.model;

import java.util.Date;

/**
 * @ClassName Student
 * @Description 表 student
 * @date 2019年3月18日
 * @Auther ll
 **/
public class Student {
    /**
     * 主键
     */
    private Integer studentNum;
    /**
     * 学号
     */
    private String studentId;
    /**
     * 学生名称
     */
    private String studentName;
    /**
     * 录入时间
     */
    private Date createTime;

    public Integer getStudentNum() {
        return studentNum;
    }

    public void setStudentNum(Integer studentNum) {
        this.studentNum = studentNum;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
