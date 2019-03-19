package com.demo.domain.DTO;

/**
 * @ClassName AddTeacherClassDTO
 * @Description 添加教师课程信息
 * @Auther ll
 **/
public class AddTeacherClassDTO {
    /**
     * 课程编号
     */
    private String classNumber;
    /**
     * 教师编号
     */
    private String teacherNumber;
    /**
     * 录入时间
     */
    private Long createTime;
    /**
     * 学年
     */
    private Integer term;


    public String getClassNumber() {
        return classNumber;
    }

    public void setClassNumber(String classNumber) {
        this.classNumber = classNumber;
    }

    public String getTeacherNumber() {
        return teacherNumber;
    }

    public void setTeacherNumber(String teacherNumber) {
        this.teacherNumber = teacherNumber;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Integer getTerm() {
        return term;
    }

    public void setTerm(Integer term) {
        this.term = term;
    }
}
