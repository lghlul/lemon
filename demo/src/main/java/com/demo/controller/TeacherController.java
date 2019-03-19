package com.demo.controller;

import com.demo.constant.ResultCodeConstant;
import com.demo.dto.*;
import com.demo.common.ResultBean;
import com.demo.service.ITeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
        try{
            return teacherService.save(addTeacherDTO);
        }catch (Exception e){
            return new ResultBean(ResultCodeConstant.SERVER_EXCEPTION);
        }
    }

    /*
     * @author ll
     * @Description 分页查询教师
     * @param listTeacher
     * @return ResultBean
     */
    @GetMapping("listTeacher")
    public ResultBean listTeacher(ListTeacherDTO listTeacherDTO) {
        try{
            return teacherService.list(listTeacherDTO);
        }catch (Exception e){
            return new ResultBean(ResultCodeConstant.SERVER_EXCEPTION);
        }
    }

    /*
     * @author ll
     * @Description 添加教师课程信息
     * @param AddTeacherClassDTO
     * @return ResultBean
     */
    @PostMapping("addTeacherClass")
    public ResultBean addTeacherClass(AddTeacherClassDTO addTeacherClassDTO) {
        try{
            return teacherService.addTeacherClass(addTeacherClassDTO);
        }catch (Exception e){
            return new ResultBean(ResultCodeConstant.SERVER_EXCEPTION);
        }
    }

    /*
     * @author ll
     * @Description 分页查询教师课程
     * @param ListTeacherClassDTO
     * @return ResultBean
     */
    @GetMapping("listTeacherClass")
    public ResultBean listTeacherClass(ListTeacherClassDTO listTeacherClassDTO) {
        try{
            return teacherService.listTeacherClass(listTeacherClassDTO);
        }catch (Exception e){
            return new ResultBean(ResultCodeConstant.SERVER_EXCEPTION);
        }
    }

    /*
     * @author ll
     * @Description 查询教师本人每学年，学科平均成绩，最高分，最低分
     * @param teacherNumber
     * @return ResultBean
     */
    @GetMapping("listByTerm")
    public ResultBean listByTerm(String teacherNumber) {
        try{
            return teacherService.listByTerm(teacherNumber);
        }catch (Exception e){
            return new ResultBean(ResultCodeConstant.SERVER_EXCEPTION);
        }
    }

    /*
     * @author ll
     * @Description 查询每学年学科平均成绩，最高分，最低分
     * @param teacherNumber
     * @return ResultBean
     */
    @GetMapping("listWithLevel")
    public ResultBean listWithLevel(String teacherNumber) {
        try{
            return teacherService.listByLevel(teacherNumber);
        }catch (Exception e){
            return new ResultBean(ResultCodeConstant.SERVER_EXCEPTION);
        }

    }


    /*
     * @author ll
     * @Description 查询教师-学科平均成绩，最高分，最低分
     * @param teacherNumber
     * @return ResultBean
     */
    @GetMapping("listWithScore")
    public ResultBean listWithScore(String teacherNumber) {
        try{
            return teacherService.listWithScore(teacherNumber);
        }catch (Exception e){
            return new ResultBean(ResultCodeConstant.SERVER_EXCEPTION);
        }
    }


    /*
     * @author ll
     * @Description 删除教师信息
     * @param String
     * @return ResultBean
     */
    @DeleteMapping("deleteTeacher")
    public ResultBean deleteTeacher(String teacherNumber) {
        try{
            return teacherService.delete(teacherNumber);
        }catch (Exception e){
            return new ResultBean(ResultCodeConstant.SERVER_EXCEPTION);
        }
    }

    /*
     * @author ll
     * @Description 更新教师信息
     * @param UpdateTeacherDTO
     * @return ResultBean
     */
    @PutMapping("updateTeacher")
    public ResultBean updateTeacher(UpdateTeacherDTO updateTeacherDTO) {
        try{
            return teacherService.update(updateTeacherDTO);
        }catch (Exception e){
            return new ResultBean(ResultCodeConstant.SERVER_EXCEPTION);
        }
    }

}
