package com.demo.domain.DTO;

/**
 * @ClassName AddClassDTO
 * @Description 添加课程对象
 * @Auther ll
 **/
public class AddClassDTO {
    /**
     * 课程编号
     */
    private String classNumber;
    /**
     * 课程名称
     */
    private String className;
    /**
     * 录入时间
     */
    private long createTime;

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

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
