package com.demo.domain.DTO;

/**
 * @ClassName ListTeacherClassScoreDTO
 * @Description 教师-学科平均成绩，最高分，最低分 返回对象
 * @Auther lgh_l
 * @Date 2019/3/19 10:18
 * @Version 1.0
 **/
public class ListTeacherClassScoreDTO {
    /**
     * 教师编号
     */
    private String teacherNumber;
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
     * 课程编号
     */
    private String classNumber;
    /**
     * 课程名称
     */
    private String className;
    /**
     * 教师名称
     */
    private String teacherName;


    public String getTeacherNumber() {
        return teacherNumber;
    }

    public void setTeacherNumber(String teacherNumber) {
        this.teacherNumber = teacherNumber;
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

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }
}
