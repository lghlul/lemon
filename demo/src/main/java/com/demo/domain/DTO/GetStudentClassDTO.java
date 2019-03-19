package com.demo.domain.DTO;

/**
 * @ClassName GetStudentClassDTO
 * @Description 单个查询学生课程对象
 * @Auther ll
 **/
public class GetStudentClassDTO {
    /**
     * 学生编号
     */
    private String studentNumber;
    /**
     * 教师课程关联
     */
    private Integer teacherClassId;

    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    public Integer getTeacherClassId() {
        return teacherClassId;
    }

    public void setTeacherClassId(Integer teacherClassId) {
        this.teacherClassId = teacherClassId;
    }
}
