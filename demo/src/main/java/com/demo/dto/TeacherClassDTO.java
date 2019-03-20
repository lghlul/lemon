package com.demo.dto;

/**
 * @ClassName TeacherClassDTO
 * @Description 添加教师课程信息
 * @Auther ll
 **/
public class TeacherClassDTO {
    /**
     * 自增主键
     */
    private Integer teacherClassId;
    /**
     * 课程主键
     */
    private Integer classId;
    /**
     * 教师主键
     */
    private Integer teacherId;
    /**
     * 录入时间
     */
    private Long createTime;
    /**
     * 学年
     */
    private Integer term;


    public Integer getTeacherClassId() {
        return teacherClassId;
    }

    public void setTeacherClassId(Integer teacherClassId) {
        this.teacherClassId = teacherClassId;
    }

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
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
