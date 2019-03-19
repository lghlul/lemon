package com.demo.domain.DO;

/**
 * @ClassName StudentClassDO
 * @Description 表 studentClass
 * @date 2019年3月18日
 * @Auther ll
 **/
public class StudentClassDO {
    /**
     * 主键ID
     */
    private Integer id;
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


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
