package com.demo.controller;

import com.demo.constant.ResultCodeConstant;
import com.demo.dto.*;
import com.demo.common.ResultBean;
import com.demo.model.Student;
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
    @PostMapping("save")
    public ResultBean save(StudentDTO studentDTO) {
        try {

            if (studentService.checkStudentExist(studentDTO)) {
                return new ResultBean(ResultCodeConstant.NUMBER_REPEAT, "学生编号已存在");
            } else {
                //编号不存在
                return new ResultBean(ResultCodeConstant.SUCCESS, studentService.saveOrUpdate(studentDTO));
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
            return new ResultBean(ResultCodeConstant.SUCCESS, "成功", studentService.list(studentDTO));
        } catch (Exception e) {
            return new ResultBean(ResultCodeConstant.SERVER_EXCEPTION, "服务器异常");
        }
    }

    /*
     * @author ll
     * @Description 学生选课
     * @param StudentClassDTO
     * @return ResultBean
     */
    @PostMapping("saveStudentClass")
    public ResultBean saveStudentClass(StudentClassDTO studentClassDTO) {
        try {
            StudentDTO studentDTO = new StudentDTO();
            studentDTO.setStudentId(studentClassDTO.getStudentId());
            //校验学生是否存在
            if (!studentService.checkStudentExist(studentDTO)) {
                return new ResultBean(ResultCodeConstant.STUDENT_NOT_EXIST, "学生不存在");
            }
            //校验是否存在这门课程
            if (!teacherService.checkTeacherClassExist(studentClassDTO.getTeacherClassId())) {
                return new ResultBean(ResultCodeConstant.TEACHER_CLASS_MISMATCHING, "教师课程不存在");
            }
            //校验该学生是否选过这门课程
            if (!studentService.checkStudentClassExist(studentClassDTO)) {
                return new ResultBean(ResultCodeConstant.SUCCESS, "成功", studentService.saveOrUpdateStudentClass(studentClassDTO));
            } else {
                //已经选过该课程
                return new ResultBean(ResultCodeConstant.STUDENT_CLASS_EXIST, "重复选课");
            }

        } catch (Exception e) {
            e.printStackTrace();
            return new ResultBean(ResultCodeConstant.SERVER_EXCEPTION, "服务器异常");
        }
    }

    /*
     * @author ll
     * @Description 更新学生课程信息(录入课程分数)
     * @param UpdateStudentClassDTO
     * @return ResultBean
     */
    @PutMapping("updateStudentClass")
    public ResultBean updateStudentClass(StudentClassDTO studentClassDTO) {
        try {
            //是否选课
            if (!studentService.checkStudentClassExist(studentClassDTO)) {
                //没有选课
                return new ResultBean(ResultCodeConstant.STUDENT_NOT_HAVE_CLASS, "学生没有该课程");
            }
            return new ResultBean(ResultCodeConstant.SUCCESS, "成功", studentService.saveOrUpdateStudentClass(studentClassDTO));
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultBean(ResultCodeConstant.SERVER_EXCEPTION, "服务器异常");
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
            return new ResultBean(ResultCodeConstant.SUCCESS,"成功", studentService.listStudentClass(listStudentClassDTO));
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultBean(ResultCodeConstant.SERVER_EXCEPTION , "服务器异常");
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
            return new ResultBean(ResultCodeConstant.SUCCESS);

        } catch (Exception e) {
            return new ResultBean(ResultCodeConstant.SERVER_EXCEPTION);
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
            return new ResultBean(ResultCodeConstant.SUCCESS , "成功" , student);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResultBean(ResultCodeConstant.SERVER_EXCEPTION , "服务器异常");
        }
    }

    /*
     * @author ll
     * @Description 更新学生信息
     * @param UpdateStudentDTO
     * @return ResultBean
     */
    @PutMapping("update")
    public ResultBean update(StudentDTO studentDTO) {
        try {
            //查询该学生是否存在
            StudentDTO student = new StudentDTO();
            student.setStudentId(studentDTO.getStudentId());
            if (!studentService.checkStudentExist(student)) {
                //学生不存在
                return new ResultBean(ResultCodeConstant.STUDENT_NOT_EXIST, "学生不存在");
            }
            student.setStudentId(null);
            student.setStudentNumber(studentDTO.getStudentNumber());
            Student studentDO = studentService.get(student);
            //如果改变的编号已经存在与其他学生
            if (studentDO != null && studentDO.getStudentId() != studentDTO.getStudentId()) {
                return new ResultBean(ResultCodeConstant.NUMBER_REPEAT, "学生编号已存在");
            }
            return new ResultBean(ResultCodeConstant.SUCCESS, "成功", studentService.saveOrUpdate(studentDTO));
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultBean(ResultCodeConstant.SERVER_EXCEPTION , "服务器异常");
        }
    }
}
