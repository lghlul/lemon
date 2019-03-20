package com.demo.dto;

/**
 * @ClassName StudentDTO
 * @Description 添加学生传输对象
 * @Auther ll
 **/
public class StudentDTO {
    /**
     * 主键
     */
    private Integer studentId;
    /**
     * 学生编号
     */
    private String studentNumber;

    /**
     * 学生名称
     */
    private String studentName;
    /**
     * 录入时间
     */
    private long createTime;

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }
}
