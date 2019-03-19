package com.demo.common;

/**
 * @ClassName ResultCodeEnum
 * @Description 返回码枚举
 * @Auther ll
 **/
public enum ResultCodeEnum {

    SUCCESS(1, "成功"),
    FAIL(-1, "失败"),
    NUMBER_REPEAT(1000, "编号重复"),
    STUDENT_NOT_EXIST(1001, "学生不存在"),
    TEACHER_CLASS_MISMATCHING(1002, "课程与老师不匹配"),
    TEACHER_NOT_EXIST(1003, "老师不存在"),
    CLASS_NOT_EXIST(1004, "课程不存在"),
    STUDENT_NOT_HAVE_CLASS(1005, "学生暂未拥有该课程"),
    NO_PERMISSION(1006, "没有权限"),
    STUDENT_CLASS_EXIST(1007, "不能重复选课");

    /**
     * 编号
     */
    private Integer code;

    /**
     * 描述
     */
    private String desc;

    ResultCodeEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }


    public ResultBean getResponse(Object object) {
        return new ResultBean(code, desc, object);
    }

    public ResultBean getResponse() {
        return new ResultBean(code, desc);
    }


}
