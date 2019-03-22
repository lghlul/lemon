package com.demo.dto;

import java.util.Date;

/**
 * @ClassName ClazzDTO
 * @Description 课程DTO
 * @Auther ll
 **/
public class ClazzDTO {
    /**
     * 主键
     */
    private Integer clazzNumber;
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


    public Integer getClazzNumber() {
        return clazzNumber;
    }

    public void setClazzNumber(Integer clazzNumber) {
        this.clazzNumber = clazzNumber;
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
