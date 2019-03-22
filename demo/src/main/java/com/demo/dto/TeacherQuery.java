package com.demo.dto;

import com.demo.common.PageQuery;

/**
 * @ClassName TeacherQuery
 * @Description 分页查询教师信息参数
 * @Auther ll
 **/
public class TeacherQuery extends PageQuery {
    /**
     * 教师名称 模糊查询  可选
     */
    private String teacherName;
    /**
     * 教师等级 可选
     */
    private Integer teacherLevel;



    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }


    public Integer getTeacherLevel() {
        return teacherLevel;
    }

    public void setTeacherLevel(Integer teacherLevel) {
        this.teacherLevel = teacherLevel;
    }
}
