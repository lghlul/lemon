package com.demo.dto;

import java.util.Date;

/**
 * @ClassName StudentDTO
 * @Description 学生DTO
 * @Auther ll
 **/
public class StudentDTO {
    /**
     * 主键
     */
    private Integer studentNumber;
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

    public Integer getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(Integer studentNumber) {
        this.studentNumber = studentNumber;
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
