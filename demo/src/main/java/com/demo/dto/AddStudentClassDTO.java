package com.demo.dto;

/**
 * @ClassName AddStudentClassDTO
 * @Description 学生选课对象
 * @Auther ll
 **/
public class AddStudentClassDTO {


    /**
     * 自增主键
     */
    private int id;
    /**
     * 学生编号
     */
    private String studentNumber;
    /**
     * 关联教师课程
     */
    private int teacherClassId;
    /**
     * 选课时间
     */
    private long createTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    public int getTeacherClassId() {
        return teacherClassId;
    }

    public void setTeacherClassId(int teacherClassId) {
        this.teacherClassId = teacherClassId;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }
}