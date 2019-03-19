package com.demo.dto;

/**
 * @ClassName UpdateClassDTO
 * @Description 更新课程对象
 * @Auther ll
 **/
public class UpdateClassDTO {
    /**
     * 课程编号
     */
    private String classNumber;
    /**
     * 课程名称
     */
    private String className;


    public String getClassNumber() {
        return classNumber;
    }

    public void setClassNumber(String classNumber) {
        this.classNumber = classNumber;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
