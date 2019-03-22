package com.demo.service;

import com.demo.dto.StudentClazzQuery;
import com.demo.model.StudentClazz;

import java.util.List;

public interface StudentClazzService {

    /*
     * @author ll
     * @Description 添加或者更新学生课程
     * @param StudentClazz
     * @return StudentClazz
     */
    StudentClazz saveOrUpdate(StudentClazz studentClazz) throws Exception;

    /*
     * @author ll
     * @Description 查询学生课程列表
     * @param StudentClazzQuery
     * @return List<StudentClazz>
     */
    List<StudentClazz> list(StudentClazzQuery studentClazzQuery) throws Exception;

    /*
     * @author ll
     * @Description 校验学生课程是否存在
     * @param Integer
     * @param Integer
     * @return boolean
     */
    boolean checkStudentClazzExist(Integer studentNum, Integer clazzNum);

    /*
     * @author ll
     * @Description 删除学生课程
     * @param Integer
     * @param Integer
     * @return void
     */
    void delete(Integer studentNum, Integer clazzNum) throws Exception;


}
