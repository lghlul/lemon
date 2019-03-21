package com.demo.controller;

import com.demo.common.ResultBean;
import com.demo.constant.ResultCodeConstant;
import com.demo.dto.StudentClazzDTO;
import com.demo.dto.StudentClazzQueryDTO;
import com.demo.service.ClazzService;
import com.demo.service.StudentClazzService;
import com.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName StudentController
 * @Description 学生控制器
 * @Auther ll
 **/
@RestController
@RequestMapping("/studentClazz")
public class StudentClazzController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private StudentClazzService studentClazzService;

    @Autowired
    private ClazzService clazzService;


    /*
     * @author ll
     * @Description 添加或者更新学生课程
     * @param StudentClazzDTO
     * @return ResultBean
     */
    @PostMapping("saveOrUpdate")
    public ResultBean save(StudentClazzDTO studentClazzDTO) {
        try {

            //校验学生是否存在
            if (!studentService.checkStudentNumberExist(studentClazzDTO.getStudentNumber())) {
                ResultBean resultBean = new ResultBean(ResultCodeConstant.STUDENT_NOT_EXIST);
                return resultBean;
            }
            //校验是否存在这门课程
            if (!clazzService.checkClazzNumberExist(studentClazzDTO.getClazzNumber())) {
                ResultBean resultBean = new ResultBean(ResultCodeConstant.CLAZZ_NOT_EXIST);
                return resultBean;
            }
            //添加
            if (studentClazzDTO.getClazzNumber() != null && studentClazzDTO.getStudentNumber() != null && studentClazzDTO.getScore() == null) {
                //校验是否重复选课
                if (studentClazzService.checkStudentClazzExist(studentClazzDTO.getStudentNumber(), studentClazzDTO.getClazzNumber())) {
                    //已经选过该课程
                    ResultBean resultBean = new ResultBean(ResultCodeConstant.STUDENT_CLAZZ_EXIST);
                    return resultBean;
                }
            }

            StudentClazzDTO resultStudentClazz = studentClazzService.saveOrUpdate(studentClazzDTO);
            ResultBean resultBean = new ResultBean(ResultCodeConstant.SUCCESS, resultStudentClazz);
            return resultBean;
        } catch (Exception e) {
            ResultBean resultBean = new ResultBean(ResultCodeConstant.SERVER_EXCEPTION);
            return resultBean;
        }
    }

    /*
     * @author ll
     * @Description 学生课程列表(用于查看学生学年成绩)
     * @param StudentClazzQueryDTO
     * @return ResultBean
     */
    @GetMapping("list")
    public ResultBean list(StudentClazzQueryDTO studentClazzQueryDTO) {
        try {
            List<StudentClazzDTO> list = studentClazzService.list(studentClazzQueryDTO);
            ResultBean resultBean = new ResultBean(ResultCodeConstant.SUCCESS, list);
            return resultBean;
        } catch (Exception e) {
            ResultBean resultBean = new ResultBean(ResultCodeConstant.SERVER_EXCEPTION);
            return resultBean;
        }
    }


    /*
     * @author ll
     * @Description 删除学生课程信息
     * @param String
     * @return ResultBean
     */
    @DeleteMapping("delete")
    public ResultBean delete(Integer studentNumber, Integer clazzNumber) {
        try {
            studentClazzService.delete(studentNumber, clazzNumber);
            ResultBean resultBean = new ResultBean(ResultCodeConstant.SUCCESS);
            return resultBean;
        } catch (Exception e) {
            ResultBean resultBean = new ResultBean(ResultCodeConstant.SERVER_EXCEPTION);
            return resultBean;
        }
    }

}
