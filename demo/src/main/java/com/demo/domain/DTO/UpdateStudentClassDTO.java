package com.demo.domain.DTO;

/**
 * @ClassName UpdateStudentClassDTO
 * @Description 更新学生课程(录入分数)
 * @Auther ll
 **/
public class UpdateStudentClassDTO {
    /**
     * 学生编号
     */
    private String studentNumber;

    /**
     * 得分
     */
    private Double score;

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

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public Integer getTeacherClassId() {
        return teacherClassId;
    }

    public void setTeacherClassId(Integer teacherClassId) {
        this.teacherClassId = teacherClassId;
    }
}
