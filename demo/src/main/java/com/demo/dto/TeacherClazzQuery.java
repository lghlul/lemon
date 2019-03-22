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
    private Integer teacherNum;

    public Integer getTeacherNum() {
        return teacherNum;
    }

    public void setTeacherNum(Integer teacherNum) {
        this.teacherNum = teacherNum;
    }
}
