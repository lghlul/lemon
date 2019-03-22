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
    private Integer studentNum;
    /**
     * 课程编号
     */
    private Integer clazzNum;
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


    public Integer getStudentNum() {
        return studentNum;
    }

    public void setStudentNum(Integer studentNum) {
        this.studentNum = studentNum;
    }

    public Integer getClazzNum() {
        return clazzNum;
    }

    public void setClazzNum(Integer clazzNum) {
        this.clazzNum = clazzNum;
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
