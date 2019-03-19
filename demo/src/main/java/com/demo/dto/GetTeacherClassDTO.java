package com.demo.dto;

/**
 * @ClassName GetTeacherClassDTO
 * @Description 单个查询教师课程对象
 * @Auther ll
 **/
public class GetTeacherClassDTO {
    /**
     * 教师编号
     */
    private String teacherNumber;
    /**
     * 课程编号
     */
    private String classNumber;
    /**
     * 学年
     */
    private Integer term;
    /**
     * 教师课程关联id
     */
    private Integer teacherClassId;

    public Integer getTeacherClassId() {
        return teacherClassId;
    }

    public void setTeacherClassId(Integer teacherClassId) {
        this.teacherClassId = teacherClassId;
    }

    public Integer getTerm() {
        return term;
    }

    public void setTerm(Integer term) {
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
