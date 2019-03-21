package com.demo.dto;

import com.demo.common.PageQuery;

/**
 * @ClassName StudentQueryDTO
 * @Description 分页查询学生参数
 * @Auther ll
 **/
public class StudentQueryDTO extends PageQuery {
    /**
     * 名称 模糊查询 可选参数
     */
    private String studentName;


    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }
}
