package com.demo.dto;

/**
 * @ClassName TeacherClazzReportDTO
 * @Description 教师-学科平均成绩，最高分，最低分 DTO
 * @Auther lgh_l
 * @Date 2019/3/19 10:18
 * @Version 1.0
 **/
public class TeacherClazzReportDTO {
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
     * 课程名称
     */
    private String clazzName;
    /**
     * 教师名称
     */
    private String teacherName;
    /**
     * 课程主键
     */
    private Integer clazzNum;
    /**
     * 教师主键
     */
    private Integer teacherNum;


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

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public Integer getClazzNum() {
        return clazzNum;
    }

    public void setClazzNum(Integer clazzNum) {
        this.clazzNum = clazzNum;
    }

    public Integer getTeacherNum() {
        return teacherNum;
    }

    public void setTeacherNum(Integer teacherNum) {
        this.teacherNum = teacherNum;
    }

    public String getClazzName() {
        return clazzName;
    }

    public void setClazzName(String clazzName) {
        this.clazzName = clazzName;
    }
}
