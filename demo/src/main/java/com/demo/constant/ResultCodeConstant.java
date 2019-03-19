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
    public static final int SUCCESS = 1;
    /**
     * 失败
     */
    public static final int FAIL = -1;
    /**
     * 服务器异常
     */
    public static final int SERVER_EXCEPTION = -2;
    /**
     * 编号重复
     */
    public static final int NUMBER_REPEAT = 1000;
    /**
     * 学生不存在
     */
    public static final int STUDENT_NOT_EXIST = 1001;
    /**
     * 课程与老师不匹配
     */
    public static final int TEACHER_CLASS_MISMATCHING = 1002;
    /**
     * 老师不存在
     */
    public static final int TEACHER_NOT_EXIST = 1003;
    /**
     * 课程不存在
     */
    public static final int CLASS_NOT_EXIST = 1004;
    /**
     * 学生暂未拥有该课程
     */
    public static final int STUDENT_NOT_HAVE_CLASS = 1005;
    /**
     * 没有权限
     */
    public static final int NO_PERMISSION = 1006;
    /**
     * 不能重复选课
     */
    public static final int STUDENT_CLASS_EXIST = 1007;


}
