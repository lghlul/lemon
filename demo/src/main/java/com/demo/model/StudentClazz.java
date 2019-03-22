package com.demo.model;

import java.util.Date;

/**
 * @ClassName StudentClazz
 * @Description 表 student_clazz
 * @date 2019年3月18日
 * @Auther ll
 **/
public class StudentClazz {
    /**
     * 学生主键
     */
    private Integer studentNum;

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

    public Integer getTerm() {
        return term;
    }

    public void setTerm(Integer term) {
        this.term = term;
    }

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
}
