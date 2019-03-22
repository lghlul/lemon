package com.demo.model;

import java.util.Date;

/**
 * @ClassName Clazz
 * @Description 表 Clazz
 * @date 2019年3月18日
 * @Auther ll
 **/
public class Clazz {
    /**
     * 主键
     */
    private Integer clazzNum;
    /**
     * 课程号
     */
    private String clazzId;
    /**
     * 课程名称
     */
    private String clazzName;
    /**
     * 创建时间
     */
    private Date createTime;


    public Integer getClazzNum() {
        return clazzNum;
    }

    public void setClazzNum(Integer clazzNum) {
        this.clazzNum = clazzNum;
    }

    public String getClazzId() {
        return clazzId;
    }

    public void setClazzId(String clazzId) {
        this.clazzId = clazzId;
    }

    public String getClazzName() {
        return clazzName;
    }

    public void setClazzName(String clazzName) {
        this.clazzName = clazzName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
