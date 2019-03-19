package com.demo.dto;

/**
 * @ClassName UpdateTeacherDTO
 * @Description 更新教师传输对象
 * @Auther ll
 **/
public class UpdateTeacherDTO {
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
