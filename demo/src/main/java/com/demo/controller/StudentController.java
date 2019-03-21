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
     * @param StudentQueryDTO
     * @return ResultBean
     */
    @GetMapping("list")
    public ResultBean list(StudentQueryDTO studentDTO) {
        try {
            Object list = studentService.list(studentDTO);
            ResultBean resultBean = new ResultBean(ResultCodeConstant.SUCCESS, list);
            return resultBean;
        } catch (Exception e) {
            ResultBean resultBean = new ResultBean(ResultCodeConstant.SERVER_EXCEPTION);
            return resultBean;
        }
    }


    /*
     * @author ll
     * @Description 删除学生信息
     * @param String
     * @return ResultBean
     */
    @DeleteMapping("delete")
    public ResultBean delete(Integer studentNumber) {
        try {
            studentService.delete(studentNumber);
            ResultBean resultBean = new ResultBean(ResultCodeConstant.SUCCESS);
            return resultBean;
        } catch (Exception e) {
            ResultBean resultBean = new ResultBean(ResultCodeConstant.SERVER_EXCEPTION);
            return resultBean;
        }
    }

    /*
     * @author ll
     * @Description 根据主键查询学生信息
     * @param Integer
     * @return ResultBean
     */
    @GetMapping("read")
    public ResultBean read(Integer studentNumber) {
        try {

            Student student = studentService.read(studentNumber);
            if (student == null) {
                //学生不存在
                ResultBean resultBean = new ResultBean(ResultCodeConstant.STUDENT_NOT_EXIST);
                return resultBean;
            }
            ResultBean resultBean = new ResultBean(ResultCodeConstant.SUCCESS, student);
            return resultBean;

        } catch (Exception e) {
            ResultBean resultBean = new ResultBean(ResultCodeConstant.SERVER_EXCEPTION);
            return resultBean;
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
            //更新
            if (studentDTO.getStudentNumber() != null) {
                //查询该学生是否存在
                if (!studentService.checkStudentNumberExist(studentDTO.getStudentNumber())) {
                    //学生不存在
                    ResultBean resultBean = new ResultBean(ResultCodeConstant.STUDENT_NOT_EXIST);
                    return resultBean;
                }
                Student student = studentService.readById(studentDTO.getStudentId());
                //如果改变的编号已经存在与其他学生
                if (student != null && student.getStudentNumber() != studentDTO.getStudentNumber()) {
                    ResultBean resultBean = new ResultBean(ResultCodeConstant.NUMBER_REPEAT);
                    return resultBean;
                }
            } else {
                //添加
                //校验编号
                if (studentService.readById(studentDTO.getStudentId()) != null) {
                    ResultBean resultBean = new ResultBean(ResultCodeConstant.NUMBER_REPEAT);
                    return resultBean;
                }
            }
            StudentDTO resultStudent = studentService.saveOrUpdate(studentDTO);
            ResultBean resultBean = new ResultBean(ResultCodeConstant.SUCCESS, resultStudent);
            return resultBean;
        } catch (Exception e) {
            ResultBean resultBean = new ResultBean(ResultCodeConstant.SERVER_EXCEPTION);
            return resultBean;
        }
    }
}
