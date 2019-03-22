package com.demo.dto;

/**
 * @ClassName StudentClazzQuery
 * @Description 查询学生课程列表对象
 * @Auther ll
 **/
public class StudentClazzQuery {
    /**
     * 学生编号
     */
    private Integer studentNumber;
    /**
     * 课程编号
     */
    private Integer clazzNumber;
    /**
     * 课程名称
     */
    private String clazzName;
    /**
     * 得分
     */
    private String score;
    /**
     * 学年
     */
    private Integer term;


    public Integer getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(Integer studentNumber) {
        this.studentNumber = studentNumber;
    }

    public Integer getClazzNumber() {
        return clazzNumber;
    }

    public void setClazzNumber(Integer clazzNumber) {
        this.clazzNumber = clazzNumber;
    }

    public String getClazzName() {
        return clazzName;
    }

    public void setClazzName(String clazzName) {
        this.clazzName = clazzName;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public Integer getTerm() {
        return term;
    }

    public void setTerm(Integer term) {
        this.term = term;
    }
}
