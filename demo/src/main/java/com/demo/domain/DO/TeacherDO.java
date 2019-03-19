package com.demo.domain.DO;

/**
 * @ClassName TeacherDO
 * @Description 表 teacher
 * @date 2019年3月18日
 * @Auther ll
 **/
public class TeacherDO {
    /**
     * 教师编号
     */
    private String teacherNumber;
    /**
     * 教师名称
     */
    private String teacherName;
    /**
     * 教师等级  1普通教师  2教务主任
     */
    private int teacherLevel;

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

    public String getTeacherNumber() {
        return teacherNumber;
    }

    public void setTeacherNumber(String teacherNumber) {
        this.teacherNumber = teacherNumber;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }


    public int getTeacherLevel() {
        return teacherLevel;
    }

    public void setTeacherLevel(int teacherLevel) {
        this.teacherLevel = teacherLevel;
    }
}
