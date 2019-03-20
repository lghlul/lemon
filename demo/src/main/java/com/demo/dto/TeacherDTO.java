package com.demo.dto;

/**
 * @ClassName TeacherDTO
 * @Description 添加教师传输对象
 * @Auther ll
 **/
public class TeacherDTO {
    /**
     * 主键
     */
    private Integer teacherId;
    /**
     * 教师编号
     */
    private String teacherNumber;
    /**
     * 教师名称
     */
    private String teacherName;
    /**
     * 教师等级
     */
    private int teacherLevel;

    /**
     * 录入时间
     */
    private long createTime;


    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
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

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }
}
