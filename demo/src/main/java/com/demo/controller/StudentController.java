package com.demo.controller;

import com.demo.constant.ResultCodeConstant;
import com.demo.dto.*;
import com.demo.common.ResultBean;
import com.demo.model.Student;
import com.demo.service.StudentService;
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
    private StudentService studentService;

    /*
     * @author ll
     * @Description 分页查询学生列表
     * @param ListStudentDTO
     * @return ResultBean
     */
    @GetMapping("list")
    public ResultBean list(ListStudentDTO studentDTO) {
        try {
            return new ResultBean(ResultCodeConstant.SUCCESS, "成功", studentService.list(studentDTO));
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultBean(ResultCodeConstant.SERVER_EXCEPTION, "服务器异常");
        }
    }


    /*
     * @author ll
     * @Description 删除学生信息
     * @param String
     * @return ResultBean
     */
    @DeleteMapping("delete")
    public ResultBean delete(Integer studentId) {
        try {
            studentService.delete(studentId);
            return new ResultBean(ResultCodeConstant.SUCCESS, "成功");

        } catch (Exception e) {
            e.printStackTrace();
            return new ResultBean(ResultCodeConstant.SERVER_EXCEPTION, "服务器异常");
        }
    }

    /*
     * @author ll
     * @Description 根据主键查询学生信息
     * @param Integer
     * @return ResultBean
     */
    @GetMapping("get")
    public ResultBean get(Integer studentId) {
        try {
            StudentDTO studentDTO = new StudentDTO();
            studentDTO.setStudentId(studentId);
            if (!studentService.checkStudentExist(studentDTO)) {
                //学生不存在
                return new ResultBean(ResultCodeConstant.STUDENT_NOT_EXIST, "学生不存在");
            }
            Student student = studentService.get(studentDTO);
            return new ResultBean(ResultCodeConstant.SUCCESS, "成功", student);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResultBean(ResultCodeConstant.SERVER_EXCEPTION, "服务器异常");
        }
    }

    /*
     * @author ll
     * @Description 更新学生信息
     * @param UpdateStudentDTO
     * @return ResultBean
     */
    @PostMapping("saveOrUpdate")
    public ResultBean saveOrUpdate(StudentDTO studentDTO) {
        try {
            StudentDTO student = new StudentDTO();
            if(studentDTO.getStudentId() != null){
                //查询该学生是否存在
                student.setStudentId(studentDTO.getStudentId());
                if (!studentService.checkStudentExist(student)) {
                    //学生不存在
                    return new ResultBean(ResultCodeConstant.STUDENT_NOT_EXIST, "学生不存在");
                }

                student.setStudentId(null);
                student.setStudentNumber(studentDTO.getStudentNumber());
                Student studentDO = studentService.get(student);
                //如果改变的编号已经存在与其他学生
                if (studentDO != null &&  studentDO.getStudentId() != studentDTO.getStudentId()) {
                    return new ResultBean(ResultCodeConstant.NUMBER_REPEAT, "学生编号已存在");
                }
            }else{
                student.setStudentNumber(studentDTO.getStudentNumber());
                if(studentService.checkStudentExist(student)){
                    return new ResultBean(ResultCodeConstant.NUMBER_REPEAT, "学生编号已存在");
                }
            }
            return new ResultBean(ResultCodeConstant.SUCCESS, "成功", studentService.saveOrUpdate(studentDTO));
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultBean(ResultCodeConstant.SERVER_EXCEPTION, "服务器异常");
        }
    }
}
