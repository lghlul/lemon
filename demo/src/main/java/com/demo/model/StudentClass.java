package com.demo.model;

/**
 * @ClassName StudentClass
 * @Description 表 studentClass
 * @date 2019年3月18日
 * @Auther ll
 **/
public class StudentClass {
    /**
     * 主键ID
     */
    private Integer studentClassId;
    /**
     * 学生编号
     */
    private String studentNumber;

    /**
     * 关联教师课程
     */
    private int teacherClassId;
    /**
     * 课程得分
     */
    private Double score;

    /**
     * 选课时间
     */
    private long createTime;

    public Integer getStudentClassId() {
        return studentClassId;
    }

    public void setStudentClassId(Integer studentClassId) {
        this.studentClassId = studentClassId;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    public int getTeacherClassId() {
        return teacherClassId;
    }

    public void setTeacherClassId(int teacherClassId) {
        this.teacherClassId = teacherClassId;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }
}
