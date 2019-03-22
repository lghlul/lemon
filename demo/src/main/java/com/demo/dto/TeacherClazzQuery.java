package com.demo.dto;

import com.demo.common.PageQuery;

/**
 * @ClassName TeacherClazzQuery
 * @Description 分页查询教师课程信息
 * @Auther ll
 **/
public class TeacherClazzQuery extends PageQuery {
    /**
     * 教师主键  可选查询参数
     */
    private Integer teacherNumber;

    public Integer getTeacherNumber() {
        return teacherNumber;
    }

    public void setTeacherNumber(Integer teacherNumber) {
        this.teacherNumber = teacherNumber;
    }
}
