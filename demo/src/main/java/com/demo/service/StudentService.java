package com.demo.service;

import com.demo.dto.*;
import com.demo.model.Student;
import com.github.pagehelper.PageInfo;

import java.util.List;


public interface StudentService {

    /*
     * @author ll
     * @Description 查询学生列表
     * @param StudentQuery
     * @return List<Student>
     */
    List<Student> list(StudentQuery studentQuery) throws Exception;

    /*
     * @author ll
     * @Description 分页查询学生列表
     * @param StudentQuery
     * @return PageInfo<Student>
     */
    PageInfo<Student> listPage(StudentQuery studentQuery) throws Exception;

    /*
     * @author ll
     * @Description 删除学生信息
     * @param Integer
     * @return void
     */
    void delete(Integer studentId) throws Exception;

    /*
     * @author ll
     * @Description 根据主键读取学生信息
     * @param Integer
     * @return Student
     */
    Student read(Integer studentNum);

    /*
     * @author ll
     * @Description 根据学号读取学生信息
     * @param String
     * @return Student
     */
    Student readById(String studentId);

    /*
     * @author ll
     * @Description 保存或者更新学生
     * @param Student
     * @return Student
     */
    Student saveOrUpdate(Student student) throws Exception;

}
