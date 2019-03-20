package com.demo.dto;

/**
 * @ClassName StudentClassDTO
 * @Description 学生选课对象
 * @Auther ll
 **/
public class StudentClassDTO {


    /**
     * 自增主键
     */
    private Integer studentClassId;
    /**
     * 学生主键
     */
    private Integer studentId;
    /**
     * 关联教师课程
     */
    private Integer teacherClassId;
    /**
     * 选课时间
     */
    private long createTime;
    /**
     * 得分
     */
    private Double score;


    public Integer getStudentClassId() {
        return studentClassId;
    }

    public void setStudentClassId(Integer studentClassId) {
        this.studentClassId = studentClassId;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getTeacherClassId() {
        return teacherClassId;
    }

    public void setTeacherClassId(Integer teacherClassId) {
        this.teacherClassId = teacherClassId;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }
}