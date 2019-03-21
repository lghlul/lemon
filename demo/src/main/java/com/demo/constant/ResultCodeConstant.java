package com.demo.constant;


/**
 * @ClassName ResultCodeConstant
 * @Description 返回码枚举
 * @Auther ll
 **/
public class ResultCodeConstant {

    /**
     * 成功
     */
    public static final String SUCCESS = "1,成功";
    /**
     * 失败
     */
    public static final String FAIL = "-1,失败";
    /**
     * 服务器异常
     */
    public static final String SERVER_EXCEPTION = "-2,服务器异常";
    /**
     * 编号重复
     */
    public static final String NUMBER_REPEAT = "1000,编号重复";
    /**
     * 学生不存在
     */
    public static final String STUDENT_NOT_EXIST = "1001,学生不存在";
    /**
     * 教师不存在
     */
    public static final String TEACHER_NOT_EXIST = "1003,教师不存在";
    /**
     * 课程不存在
     */
    public static final String CLAZZ_NOT_EXIST = "1004,课程不存在";
    /**
     * 学生暂未拥有该课程
     */
    public static final String STUDENT_NOT_HAVE_CLAZZ = "1005,学生暂未拥有该课程";
    /**
     * 没有权限
     */
    public static final String NO_PERMISSION = "1006,没有权限";
    /**
     * 不能重复选课
     */
    public static final String STUDENT_CLAZZ_EXIST = "1007,不能重复选课";
    /**
     * 教师课程不存在
     */
    public static final String TEACHER_CLAZZ_NOT_EXIST = "1008,教师课程不存在";


}
