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
    private String studentNumber;
    /**
     * 课程编号
     */
    private String classNumber;
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


    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    public String getClassNumber() {
        return classNumber;
    }

    public void setClassNumber(String classNumber) {
        this.classNumber = classNumber;
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
