package com.demo.domain.DO;

/**
 * @ClassName TeacherClassDO
 * @Description 表 teacherClass
 * @date 2019年3月18日
 * @Auther ll
 **/
public class TeacherClassDO {
    /**
     * 教师编号
     */
    private String teacherNumber;
    /**
     * 课程编号
     */
    private String classNumber;
    /**
     * 录入时间
     */
    private long createTime;
    /**
     * 学年
     */
    private int term;


    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public int getTerm() {
        return term;
    }

    public void setTerm(int term) {
        this.term = term;
    }

    public String getTeacherNumber() {
        return teacherNumber;
    }

    public void setTeacherNumber(String teacherNumber) {
        this.teacherNumber = teacherNumber;
    }

    public String getClassNumber() {
        return classNumber;
    }

    public void setClassNumber(String classNumber) {
        this.classNumber = classNumber;
    }
}
