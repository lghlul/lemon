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
    private Integer studentNum;
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
    private Integer clazzNum;

    /**
     * 选课时间
     */
    private Date createTime;

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

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public Integer getClazzNum() {
        return clazzNum;
    }

    public void setClazzNum(Integer clazzNum) {
        this.clazzNum = clazzNum;
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