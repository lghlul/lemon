package com.demo.dto;

import java.util.Date;

/**
 * @ClassName StudentClazzDTO
 * @Description 学生课程DTO
 * @Auther ll
 **/
public class StudentClazzDTO {

    /**
     * 学生主键
     */
    private Integer studentNumber;
    /**
     * 课程名称
     */
    private String clazzName;

    /**
     * 课程得分
     */
    private Double score;

    /**
     * 课程主键
     */
    private Integer clazzNumber;

    /**
     * 选课时间
     */
    private Date createTime;

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

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public Integer getClazzNumber() {
        return clazzNumber;
    }

    public void setClazzNumber(Integer clazzNumber) {
        this.clazzNumber = clazzNumber;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getClazzName() {
        return clazzName;
    }

    public void setClazzName(String clazzName) {
        this.clazzName = clazzName;
    }


    public Integer getTerm() {
        return term;
    }

    public void setTerm(Integer term) {
        this.term = term;
    }
}