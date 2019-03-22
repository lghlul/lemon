package com.demo.model;

/**
 * @ClassName TeacherClazzReport
 * @Description 查询教师课程返回对象
 * @Auther lgh_l
 * @Date 2019/3/21 17:11
 * @Version 1.0
 **/
public class TeacherClazzReport {

    /**
     * 教师号
     */
    private String teacherId;
    /**
     * 最高分
     */
    private Double maxScore;
    /**
     * 最低分
     */
    private Double minScore;
    /**
     * 平均分
     */
    private Double avgScore;
    /**
     * 课程号
     */
    private String clazzId;
    /**
     * 课程主键
     */
    private Integer clazzNumber;
    /**
     * 教师主键
     */
    private Integer teacherNumber;


    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public Double getMaxScore() {
        return maxScore;
    }

    public void setMaxScore(Double maxScore) {
        this.maxScore = maxScore;
    }

    public Double getMinScore() {
        return minScore;
    }

    public void setMinScore(Double minScore) {
        this.minScore = minScore;
    }

    public Double getAvgScore() {
        return avgScore;
    }

    public void setAvgScore(Double avgScore) {
        this.avgScore = avgScore;
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

    public Integer getTeacherNumber() {
        return teacherNumber;
    }

    public void setTeacherNumber(Integer teacherNumber) {
        this.teacherNumber = teacherNumber;
    }
}
