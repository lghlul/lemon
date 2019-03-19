package com.demo.controller;

import com.demo.constant.ResultCodeConstant;
import com.demo.dto.*;
import com.demo.common.ResultBean;
import com.demo.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
        try{
            return studentService.save(addStudentDTO);
        }catch (Exception e){
            return new ResultBean(ResultCodeConstant.SERVER_EXCEPTION);
        }
    }

    /*
     * @author ll
     * @Description 分页查询学生列表
     * @param ListStudentDTO
     * @return ResultBean
     */
    @GetMapping("listStudent")
    public ResultBean listStudent(ListStudentDTO studentDTO) {
        try{
            return studentService.list(studentDTO);
        }catch (Exception e){
            return new ResultBean(ResultCodeConstant.SERVER_EXCEPTION);
        }
    }

    /*
     * @author ll
     * @Description 学生选课
     * @param AddStudentClassDTO
     * @return ResultBean
     */
    @PostMapping("addStudentClass")
    public ResultBean addStudentClass(AddStudentClassDTO addStudentClassDTO) {
        try{
            return studentService.addStudentClass(addStudentClassDTO);
        }catch (Exception e){
            return new ResultBean(ResultCodeConstant.SERVER_EXCEPTION);
        }
    }

    /*
     * @author ll
     * @Description 更新学生课程信息(录入课程分数)
     * @param UpdateStudentClassDTO
     * @return ResultBean
     */
    @PutMapping("updateStudentClass")
    public ResultBean updateStudentClass(UpdateStudentClassDTO updateStudentClassDTO) {
        try{
            return studentService.updateStudentClass(updateStudentClassDTO);
        }catch (Exception e){
            return new ResultBean(ResultCodeConstant.SERVER_EXCEPTION);
        }
    }

    /*
     * @author ll
     * @Description 学生课程列表(用于查看学生学年成绩)
     * @param ListStudentClassDTO
     * @return ResultBean
     */
    @GetMapping("listStudentClass")
    public ResultBean listStudentClass(ListStudentClassDTO listStudentClassDTO) {
        try{
            return studentService.listStudentClass(listStudentClassDTO);
        }catch (Exception e){
            return new ResultBean(ResultCodeConstant.SERVER_EXCEPTION);
        }
    }

    /*
     * @author ll
     * @Description 删除学生信息
     * @param String
     * @return ResultBean
     */
    @DeleteMapping("deleteStudent")
    public ResultBean deleteStudent(String studentNumber) {
        try{
            return studentService.delete(studentNumber);
        }catch (Exception e){
            return new ResultBean(ResultCodeConstant.SERVER_EXCEPTION);
        }
    }

    /*
     * @author ll
     * @Description 更新课程信息
     * @param UpdateStudentDTO
     * @return ResultBean
     */
    @PutMapping("updateStudent")
    public ResultBean updateStudent(UpdateStudentDTO updateStudentDTO) {
        try{
            return studentService.update(updateStudentDTO);
        }catch (Exception e){
            return new ResultBean(ResultCodeConstant.SERVER_EXCEPTION);
        }
    }


}
