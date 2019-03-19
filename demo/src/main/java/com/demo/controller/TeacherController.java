package com.demo.controller;

import com.demo.domain.DTO.*;
import com.demo.common.ResultBean;
import com.demo.service.ITeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName TeacherController
 * @Description 教师控制器
 * @Auther ll
 **/
@RestController
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    private ITeacherService teacherService;

    /*
     * @author ll
     * @Description 添加教师信息
     * @param AddTeacherDTO
     * @return ResultBean
     */
    @PostMapping("addTeacher")
    public ResultBean addTeacher(AddTeacherDTO addTeacherDTO) {
        return teacherService.addTeacher(addTeacherDTO);
    }

    /*
     * @author ll
     * @Description 分页查询教师
     * @param listTeacher
     * @return ResultBean
     */
    @GetMapping("listTeacher")
    public ResultBean listTeacher(ListTeacherDTO listTeacherDTO) {
        return teacherService.listTeacher(listTeacherDTO);
    }

    /*
     * @author ll
     * @Description 添加教师课程信息
     * @param AddTeacherClassDTO
     * @return ResultBean
     */
    @PostMapping("addTeacherClass")
    public ResultBean addTeacherClass(AddTeacherClassDTO addTeacherClassDTO) {
        return teacherService.addTeacherClass(addTeacherClassDTO);
    }

    /*
     * @author ll
     * @Description 分页查询教师课程
     * @param ListTeacherClassDTO
     * @return ResultBean
     */
    @GetMapping("listTeacherClass")
    public ResultBean listTeacherClass(ListTeacherClassDTO listTeacherClassDTO) {
        return teacherService.listTeacherClass(listTeacherClassDTO);
    }

    /*
     * @author ll
     * @Description 查询教师本人每学年，学科平均成绩，最高分，最低分
     * @param teacherNumber
     * @return ResultBean
     */
    @GetMapping("listTeacherClassByTerm")
    public ResultBean listTeacherClassByTerm(String teacherNumber) {
        return teacherService.listTeacherClassByTerm(teacherNumber);
    }

    /*
     * @author ll
     * @Description 查询每学年学科平均成绩，最高分，最低分
     * @param teacherNumber
     * @return ResultBean
     */
    @GetMapping("listTeacherClassByTermWithLevel")
    public ResultBean listTeacherClassByTermWithLevel(String teacherNumber) {
        return teacherService.listTeacherClassByTermWithLevel(teacherNumber);
    }


    /*
     * @author ll
     * @Description 查询教师-学科平均成绩，最高分，最低分
     * @param teacherNumber
     * @return ResultBean
     */
    @GetMapping("listTeacherClassScore")
    public ResultBean listTeacherClassScore(String teacherNumber) {
        return teacherService.listTeacherClassScore(teacherNumber);
    }


}
