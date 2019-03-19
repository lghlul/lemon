package com.demo.domain.DO;

/**
 * @ClassName StudentDO
 * @Description 表 student
 * @date 2019年3月18日
 * @Auther ll
 **/
public class StudentDO {
    /**
     * 学生编号
     */
    private String studentNumber;
    /**
     * 学生名称
     */
    private String studentName;
    /**
     * 录入时间
     */
    private long createTime;


    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }
}
