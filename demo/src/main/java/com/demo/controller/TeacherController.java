package com.demo.controller;

import com.demo.constant.ResultCodeConstant;
import com.demo.dto.*;
import com.demo.common.ResultBean;
import com.demo.model.Teacher;
import com.demo.service.TeacherService;
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
    private TeacherService teacherService;


    /*
     * @author ll
     * @Description 分页查询教师
     * @param listTeacher
     * @return ResultBean
     */
    @GetMapping("list")
    public ResultBean list(TeacherQueryDTO teacherQueryDTO) {
        try {
            Object list = teacherService.list(teacherQueryDTO);
            ResultBean resultBean = new ResultBean(ResultCodeConstant.SUCCESS, list);
            return resultBean;
        } catch (Exception e) {
            ResultBean resultBean = new ResultBean(ResultCodeConstant.SERVER_EXCEPTION);
            return resultBean;
        }
    }


    /*
     * @author ll
     * @Description 删除教师信息
     * @param String
     * @return ResultBean
     */
    @DeleteMapping("delete")
    public ResultBean delete(Integer teacherNumber) {
        try {
            teacherService.delete(teacherNumber);
            ResultBean resultBean = new ResultBean(ResultCodeConstant.SUCCESS);
            return resultBean;
        } catch (Exception e) {
            ResultBean resultBean = new ResultBean(ResultCodeConstant.SERVER_EXCEPTION);
            return resultBean;
        }
    }

    /*
     * @author ll
     * @Description 添加或者更新教师信息
     * @param UpdateTeacherDTO
     * @return ResultBean
     */
    @PostMapping("saveOrUpdate")
    public ResultBean saveOrUpdate(TeacherDTO teacherDTO) {
        try {
            //更新
            if (teacherDTO.getTeacherNumber() != null) {
                //查询该教师是否存在
                if (!teacherService.checkTeacherNumberExist(teacherDTO.getTeacherNumber())) {
                    //教师不存在
                    ResultBean resultBean = new ResultBean(ResultCodeConstant.TEACHER_NOT_EXIST);
                    return resultBean;
                }

                Teacher teacherDO = teacherService.readById(teacherDTO.getTeacherId());
                //如果改变的编号已经存在与其他教师
                if (teacherDO != null && teacherDO.getTeacherId() != teacherDTO.getTeacherId()) {
                    ResultBean resultBean = new ResultBean(ResultCodeConstant.NUMBER_REPEAT);
                    return resultBean;
                }
            } else {
                //校验编号
                if (teacherService.readById(teacherDTO.getTeacherId()) != null) {
                    ResultBean resultBean = new ResultBean(ResultCodeConstant.NUMBER_REPEAT);
                    return resultBean;
                }
            }
            TeacherDTO resultTeacher = teacherService.saveOrUpdate(teacherDTO);
            ResultBean resultBean = new ResultBean(ResultCodeConstant.SUCCESS, resultTeacher);
            return resultBean;
        } catch (Exception e) {
            ResultBean resultBean = new ResultBean(ResultCodeConstant.SERVER_EXCEPTION);
            return resultBean;
        }
    }


    /*
     * @author ll
     * @Description 根据主键查询教师信息
     * @param Integer
     * @return ResultBean
     */
    @GetMapping("get")
    public ResultBean get(Integer teacherNumber) {
        try {
            if (!teacherService.checkTeacherNumberExist(teacherNumber)) {
                //教师不存在
                ResultBean resultBean = new ResultBean(ResultCodeConstant.TEACHER_NOT_EXIST);
                return resultBean;
            }
            Teacher teacher = teacherService.read(teacherNumber);
            ResultBean resultBean = new ResultBean(ResultCodeConstant.SUCCESS, teacher);
            return resultBean;

        } catch (Exception e) {
            ResultBean resultBean = new ResultBean(ResultCodeConstant.SERVER_EXCEPTION);
            return resultBean;
        }
    }
}
