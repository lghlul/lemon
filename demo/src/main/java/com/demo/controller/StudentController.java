package com.demo.controller;

import com.demo.common.PageResult;
import com.demo.constant.ResultCodeConstant;
import com.demo.dto.*;
import com.demo.common.ResultBean;
import com.demo.model.Student;
import com.demo.service.StudentService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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
     * @param StudentQuery
     * @return ResultBean
     */
    @GetMapping("list")
    public ResultBean list(StudentQuery studentQuery) {
        try {
            List<Student> studentList;
            //是否需要分页
            if (studentQuery.getPaging()) {
                PageInfo<Student> clazzPageInfo = studentService.listPage(studentQuery);
                studentList = clazzPageInfo.getList();
                PageResult pageResult = new PageResult();
                pageResult.setTotalCount(clazzPageInfo.getTotal());
                pageResult.setTotalPage(clazzPageInfo.getPages());
                List<StudentDTO> StudentDTOList = this.batchModelToDto(studentList);
                pageResult.setList(StudentDTOList);
                ResultBean resultBean = new ResultBean(ResultCodeConstant.SUCCESS, pageResult);
                return resultBean;
            } else {
                studentList = studentService.list(studentQuery);
                List<StudentDTO> studentDTOList = this.batchModelToDto(studentList);
                ResultBean resultBean = new ResultBean(ResultCodeConstant.SUCCESS, studentDTOList);
                return resultBean;
            }
        } catch (Exception e) {
            ResultBean resultBean = new ResultBean(ResultCodeConstant.SERVER_EXCEPTION);
            return resultBean;
        }
    }


    /*
     * @author ll
     * @Description 删除学生信息
     * @param Integer
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
            StudentDTO studentDTO = this.modelToDto(student);
            ResultBean resultBean = new ResultBean(ResultCodeConstant.SUCCESS, studentDTO);
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

            Student student = studentService.readById(studentDTO.getStudentId());
            if (studentDTO.getStudentNumber() != null) {
                //更新
                //如果改变的编号已经存在与其他学生
                if (student != null && student.getStudentNumber() != studentDTO.getStudentNumber()) {
                    ResultBean resultBean = new ResultBean(ResultCodeConstant.NUMBER_REPEAT);
                    return resultBean;
                }
            } else {
                //添加
                //校验编号
                if (student != null) {
                    ResultBean resultBean = new ResultBean(ResultCodeConstant.NUMBER_REPEAT);
                    return resultBean;
                }
            }
            Student studentModel = this.dtoToModel(studentDTO);
            Student resultStudent = studentService.saveOrUpdate(studentModel);
            StudentDTO resultStudentDTO = this.modelToDto(resultStudent);
            ResultBean resultBean = new ResultBean(ResultCodeConstant.SUCCESS, resultStudentDTO);
            return resultBean;
        } catch (Exception e) {
            ResultBean resultBean = new ResultBean(ResultCodeConstant.SERVER_EXCEPTION);
            return resultBean;
        }
    }


    /*
     * @author ll
     * @Description dto转model
     * @param StudentDTO
     * @return Student
     */
    private Student dtoToModel(StudentDTO studentDTO) {
        Student student = new Student();
        student.setCreateTime(studentDTO.getCreateTime());
        student.setStudentId(studentDTO.getStudentId());
        student.setStudentName(studentDTO.getStudentName());
        student.setStudentNumber(studentDTO.getStudentNumber());
        return student;
    }

    /*
     * @author ll
     * @Description model转dto
     * @param Student
     * @return StudentDTO
     */
    private StudentDTO modelToDto(Student student) {
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setCreateTime(student.getCreateTime());
        studentDTO.setStudentId(student.getStudentId());
        studentDTO.setStudentName(student.getStudentName());
        studentDTO.setStudentNumber(student.getStudentNumber());
        return studentDTO;
    }

    /*
     * @author ll
     * @Description model转dto(批量)
     * @param List<Student>
     * @return List<StudentDTO>
     */
    private List<StudentDTO> batchModelToDto(List<Student> studentList) {
        List<StudentDTO> studentDTOList = null;
        if (studentList != null) {
            studentDTOList = new ArrayList<>();
            for (Student student : studentList) {
                StudentDTO studentDTO = this.modelToDto(student);
                studentDTOList.add(studentDTO);
            }
        }
        return studentDTOList;
    }
}
