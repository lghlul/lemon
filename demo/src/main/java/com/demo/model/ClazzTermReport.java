package com.demo.model;

/**
 * @ClassName ClazzTermReport
 * @Description 按学年查询课程返回对象
 * @Auther lgh_l
 * @Date 2019/3/21 17:11
 * @Version 1.0
 **/
public class ClazzTermReport {

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
     * 课程号
     */
    private String clazzId;
    /**
     * 课程 主键
     */
    private Integer clazzNum;

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

    public String getClazzId() {
        return clazzId;
    }

    public void setClazzId(String clazzId) {
        this.clazzId = clazzId;
    }

    public Integer getClazzNum() {
        return clazzNum;
    }

    public void setClazzNum(Integer clazzNum) {
        this.clazzNum = clazzNum;
    }
}
