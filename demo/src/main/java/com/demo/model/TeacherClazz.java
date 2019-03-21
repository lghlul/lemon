package com.demo.model;

import java.util.Date;

/**
 * @ClassName TeacherClazz
 * @Description 表 teacher_clazz
 * @date 2019年3月18日
 * @Auther ll
 **/
public class TeacherClazz {
    /**
     * 教师主键
     */
    private Integer teacherNumber;
    /**
     * 课程主键
     */
    private Integer clazzNumber;
    /**
     * 录入时间
     */
    private Date createTime;
    /**
     * 学年
     */
    private Integer term;


    public Integer getTeacherNumber() {
        return teacherNumber;
    }

    public void setTeacherNumber(Integer teacherNumber) {
        this.teacherNumber = teacherNumber;
    }

    public Integer getClazzNumber() {
        return clazzNumber;
    }

    public void setClazzNumber(Integer clazzNumber) {
        this.clazzNumber = clazzNumber;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getTerm() {
        return term;
    }

    public void setTerm(Integer term) {
        this.term = term;
    }
}
