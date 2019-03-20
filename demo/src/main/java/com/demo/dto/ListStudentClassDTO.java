package com.demo.dto;

/**
 * @ClassName ListStudentClassDTO
 * @Description 查询学生课程列表对象
 * @Auther ll
 **/
public class ListStudentClassDTO {
    /**
     * 学生编号
     */
    private Integer studentId;
    /**
     * 课程编号
     */
    private Integer classId;
    /**
     * 课程名称
     */
    private String className;
    /**
     * 得分
     */
    private String score;
    /**
     * 学年
     */
    private int term;

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public int getTerm() {
        return term;
    }

    public void setTerm(int term) {
        this.term = term;
    }
}
