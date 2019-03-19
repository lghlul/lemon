package com.demo.controller;

import com.demo.constant.ResultCodeConstant;
import com.demo.dto.*;
import com.demo.common.ResultBean;
import com.demo.model.Student;
import com.demo.model.StudentClass;
import com.demo.service.IStudentService;
import com.demo.service.ITeacherService;
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

    @Autowired
    private ITeacherService teacherService;


    /*
     * @author ll
     * @Description 添加学生信息
     * @param ListStudentDTO
     * @return ResultBean
     */
    @PostMapping("add")
    public ResultBean add(AddStudentDTO addStudentDTO) {
        try {
            if (studentService.checkStudentExist(addStudentDTO.getStudentNumber())) {
                return new ResultBean(ResultCodeConstant.NUMBER_REPEAT);
            } else {
                //编号不存在
                return studentService.save(addStudentDTO);
            }

        } catch (Exception e) {
            return new ResultBean(ResultCodeConstant.SERVER_EXCEPTION);
        }
    }

    /*
     * @author ll
     * @Description 分页查询学生列表
     * @param ListStudentDTO
     * @return ResultBean
     */
    @GetMapping("list")
    public ResultBean list(ListStudentDTO studentDTO) {
        try {
            return studentService.list(studentDTO);
        } catch (Exception e) {
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
        try {
            //校验课程是否存在
            if (!studentService.checkStudentExist(addStudentClassDTO.getStudentNumber())) {
                return new ResultBean(ResultCodeConstant.STUDENT_NOT_EXIST);
            }
            //校验是否存在这门课程
            if (!teacherService.checkTeacherClassExist(addStudentClassDTO.getTeacherClassId())) {
                return new ResultBean(ResultCodeConstant.TEACHER_CLASS_MISMATCHING);
            }
            //校验该学生是否选过这门课程
            if (!studentService.checkStudentClassExist(addStudentClassDTO.getStudentNumber(),addStudentClassDTO.getTeacherClassId() )) {
                return studentService.addStudentClass(addStudentClassDTO);
            } else {
                //已经选过该课程
                return new ResultBean(ResultCodeConstant.STUDENT_CLASS_EXIST);
            }

        } catch (Exception e) {
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
        try {
            //是否选课
            if (!studentService.checkStudentClassExist(updateStudentClassDTO.getStudentNumber(), updateStudentClassDTO.getTeacherClassId())) {
                //没有选课
                return new ResultBean(ResultCodeConstant.STUDENT_NOT_HAVE_CLASS);
            }
            return studentService.updateStudentClass(updateStudentClassDTO);
        } catch (Exception e) {
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
        try {
            return studentService.listStudentClass(listStudentClassDTO);
        } catch (Exception e) {
            return new ResultBean(ResultCodeConstant.SERVER_EXCEPTION);
        }
    }

    /*
     * @author ll
     * @Description 删除学生信息
     * @param String
     * @return ResultBean
     */
    @DeleteMapping("delete")
    public ResultBean delete(String studentNumber) {
        try {
            if (studentService.checkStudentExist(studentNumber)) {
                return studentService.delete(studentNumber);
            } else {
                //编号不存在
                return new ResultBean(ResultCodeConstant.STUDENT_NOT_EXIST);
            }

        } catch (Exception e) {
            return new ResultBean(ResultCodeConstant.SERVER_EXCEPTION);
        }
    }

    /*
     * @author ll
     * @Description 更新课程信息
     * @param UpdateStudentDTO
     * @return ResultBean
     */
    @PutMapping("update")
    public ResultBean update(UpdateStudentDTO updateStudentDTO) {
        try {
            if (studentService.checkStudentExist(updateStudentDTO.getStudentNumber())) {
                return studentService.update(updateStudentDTO);
            } else {
                //编号不存在
                return new ResultBean(ResultCodeConstant.STUDENT_NOT_EXIST);
            }
        } catch (Exception e) {
            return new ResultBean(ResultCodeConstant.SERVER_EXCEPTION);
        }
    }
}
