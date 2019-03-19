package com.demo.controller;

import com.demo.domain.DTO.*;
import com.demo.common.ResultBean;
import com.demo.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName StudentController
 * @Description 学生控制器
 * @Auther ll
 **/
@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private IStudentService studentService;


    /*
     * @author ll
     * @Description 添加学生信息
     * @param ListStudentDTO
     * @return ResultBean
     */
    @PostMapping("addStudent")
    public ResultBean addStudent(AddStudentDTO addStudentDTO) {
        return studentService.addStudent(addStudentDTO);
    }

    /*
     * @author ll
     * @Description 分页查询学生列表
     * @param ListStudentDTO
     * @return ResultBean
     */
    @GetMapping("listStudent")
    public ResultBean listStudent(ListStudentDTO studentDTO) {
        return studentService.listStudent(studentDTO);
    }

    /*
     * @author ll
     * @Description 学生选课
     * @param AddStudentClassDTO
     * @return ResultBean
     */
    @PostMapping("addStudentClass")
    public ResultBean addStudentClass(AddStudentClassDTO addStudentClassDTO) {
        return studentService.addStudentClass(addStudentClassDTO);
    }

    /*
     * @author ll
     * @Description 更新学生课程信息(录入课程分数)
     * @param UpdateStudentClassDTO
     * @return ResultBean
     */
    @PostMapping("updateStudentClass")
    public ResultBean updateStudentClass(UpdateStudentClassDTO updateStudentClassDTO) {
        return studentService.updateStudentClass(updateStudentClassDTO);
    }

    /*
     * @author ll
     * @Description 学生课程列表(用于查看学生学年成绩)
     * @param ListStudentClassDTO
     * @return ResultBean
     */
    @GetMapping("listStudentClass")
    public ResultBean listStudentClass(ListStudentClassDTO listStudentClassDTO) {
        return studentService.listStudentClass(listStudentClassDTO);
    }


}
