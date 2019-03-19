package com.demo.dto;

/**
 * @ClassName UpdateStudentDTO
 * @Description 更新学生传输对象
 * @Auther ll
 **/
public class UpdateStudentDTO {
    /**
     * 学生编号
     */
    private String studentNumber;

    /**
     * 学生名称
     */
    private String studentName;


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
}
