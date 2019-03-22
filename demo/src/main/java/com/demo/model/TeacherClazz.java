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
    private Integer teacherNum;
    /**
     * 课程主键
     */
    private Integer clazzNum;
    /**
     * 录入时间
     */
    private Date createTime;


    public Integer getTeacherNum() {
        return teacherNum;
    }

    public void setTeacherNum(Integer teacherNum) {
        this.teacherNum = teacherNum;
    }

    public Integer getClazzNum() {
        return clazzNum;
    }

    public void setClazzNum(Integer clazzNum) {
        this.clazzNum = clazzNum;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
