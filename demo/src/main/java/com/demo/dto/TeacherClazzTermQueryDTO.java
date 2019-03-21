package com.demo.dto;

/**
 * @ClassName TeacherClazzTermQueryDTO
 * @Description 教师本人每学年，学科平均成绩，最高分，最低分 返回对象
 * @Auther lgh_l
 * @Date 2019/3/19 10:05
 * @Version 1.0
 **/
public class TeacherClazzTermQueryDTO {
    /**
     * 学年
     */
    private Integer term;
    /**
     * 最高分
     */
    private double maxScore;
    /**
     * 最低分
     */
    private double minScore;
    /**
     * 平均分
     */
    private double avgScore;
    /**
     * 课程名称
     */
    private String clazzName;
    /**
     * 课程号
     */
    private String clazzId;
    /**
     * 课程 主键
     */
    private Integer clazzNumber;


    public Integer getTerm() {
        return term;
    }

    public void setTerm(Integer term) {
        this.term = term;
    }

    public double getMaxScore() {
        return maxScore;
    }

    public void setMaxScore(double maxScore) {
        this.maxScore = maxScore;
    }

    public double getMinScore() {
        return minScore;
    }

    public void setMinScore(double minScore) {
        this.minScore = minScore;
    }

    public double getAvgScore() {
        return avgScore;
    }

    public void setAvgScore(double avgScore) {
        this.avgScore = avgScore;
    }

    public String getClazzName() {
        return clazzName;
    }

    public void setClazzName(String clazzName) {
        this.clazzName = clazzName;
    }

    public String getClazzId() {
        return clazzId;
    }

    public void setClazzId(String clazzId) {
        this.clazzId = clazzId;
    }

    public Integer getClazzNumber() {
        return clazzNumber;
    }

    public void setClazzNumber(Integer clazzNumber) {
        this.clazzNumber = clazzNumber;
    }
}
