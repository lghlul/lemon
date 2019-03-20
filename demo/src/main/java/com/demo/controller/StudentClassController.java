package com.demo.controller;

import com.demo.common.ResultBean;
import com.demo.constant.ResultCodeConstant;
import com.demo.dto.ListStudentClassDTO;
import com.demo.dto.StudentClassDTO;
import com.demo.dto.StudentDTO;
import com.demo.service.StudentClassService;
import com.demo.service.StudentService;
import com.demo.service.TeacherClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName StudentController
 * @Description 学生控制器
 * @Auther ll
 **/
@RestController
@RequestMapping("/studentClass")
public class StudentClassController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private StudentClassService studentClassService;

    @Autowired
    private TeacherClassService teacherClassService;



    /*
     * @author ll
     * @Description 添加或者更新学生课程
     * @param StudentClassDTO
     * @return ResultBean
     */
    @PostMapping("saveOrUpdate")
    public ResultBean save(StudentClassDTO studentClassDTO) {
        try {
            if(studentClassDTO.getStudentClassId() == null){
                StudentDTO studentDTO = new StudentDTO();
                studentDTO.setStudentId(studentClassDTO.getStudentId());
                //校验学生是否存在
                if (!studentService.checkStudentExist(studentDTO)) {
                    return new ResultBean(ResultCodeConstant.STUDENT_NOT_EXIST, "学生不存在");
                }
                //校验是否存在这门课程
                if (!teacherClassService.checkTeacherClassExist(studentClassDTO.getTeacherClassId())) {
                    return new ResultBean(ResultCodeConstant.TEACHER_CLASS_MISMATCHING, "教师课程不存在");
                }
                //添加 校验是否重复选课
                if(studentClassService.checkStudentClassExist(studentClassDTO)){
                    //已经选过该课程
                    return new ResultBean(ResultCodeConstant.STUDENT_CLASS_EXIST, "重复选课");
                }
            }else{
                //修改校验课程是否存在
                if(!studentClassService.checkStudentClassExist(studentClassDTO)){
                    //已经选过该课程
                    return new ResultBean(ResultCodeConstant.STUDENT_NOT_HAVE_CLASS, "学生课程不存在");
                }

            }
            return new ResultBean(ResultCodeConstant.SUCCESS, "成功", studentClassService.saveOrUpdate(studentClassDTO));

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
    @GetMapping("list")
    public ResultBean list(ListStudentClassDTO listStudentClassDTO) {
        try {
            return new ResultBean(ResultCodeConstant.SUCCESS, "成功", studentClassService.list(listStudentClassDTO));
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultBean(ResultCodeConstant.SERVER_EXCEPTION, "服务器异常");
        }
    }

}
