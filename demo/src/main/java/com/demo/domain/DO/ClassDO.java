package com.demo.domain.DO;

/**
 * @ClassName ClassDO
 * @Description 表 class
 * @date 2019年3月18日
 * @Auther ll
 **/
public class ClassDO {
    /**
     * 课程编号
     */
    private String classNumber;
    /**
     * 课程名称
     */
    private String className;
    /**
     * 创建时间
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
