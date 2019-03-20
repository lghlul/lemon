package com.demo.dto;

/**
 * @ClassName ListTeacherClassByTermDTO
 * @Description 教师本人每学年，学科平均成绩，最高分，最低分 返回对象
 * @Auther lgh_l
 * @Date 2019/3/19 10:05
 * @Version 1.0
 **/
public class ListTeacherClassByTermDTO {
    /**
     * 学年
     */
    private int term;
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
    private String className;
    /**
     * 课程编号
     */
    private String classNumber;
    /**
     * 课程 主键
     */
    private Integer classId;

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public int getTerm() {
        return term;
    }

    public void setTerm(int term) {
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

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getClassNumber() {
        return classNumber;
    }

    public void setClassNumber(String classNumber) {
        this.classNumber = classNumber;
    }
}
