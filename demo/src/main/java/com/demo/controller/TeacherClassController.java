package com.demo.controller;

import com.demo.common.ResultBean;
import com.demo.constant.ResultCodeConstant;
import com.demo.dto.*;
import com.demo.service.ClassInfoService;
import com.demo.service.TeacherClassService;
import com.demo.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName TeacherController
 * @Description 教师控制器
 * @Auther ll
 **/
@RestController
@RequestMapping("/teacherClass")
public class TeacherClassController {

    @Autowired
    private TeacherClassService teacherClassService;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private ClassInfoService classInfoService;


    /*
     * @author ll
     * @Description 添加教师课程信息
     * @param TeacherClassDTO
     * @return ResultBean
     */
    @PostMapping("saveOrUpdate")
    public ResultBean saveOrUpdate(TeacherClassDTO teacherClassDTO) {
        try {
            if (teacherClassDTO.getTeacherId() != null) {
                //校验 老师是否存在
                TeacherDTO teacherDTO = new TeacherDTO();
                teacherDTO.setTeacherId(teacherClassDTO.getTeacherId());
                if (!teacherService.checkTeacherExist(teacherDTO)) {
                    return new ResultBean(ResultCodeConstant.TEACHER_NOT_EXIST);
                }
            }
            if (teacherClassDTO.getClassId() != null) {
                ClassInfoDTO classInfoDTO = new ClassInfoDTO();
                classInfoDTO.setClassId(teacherClassDTO.getClassId());
                //校验课程是否存在
                if (!classInfoService.checkClassExist(classInfoDTO)) {
                    return new ResultBean(ResultCodeConstant.CLASS_NOT_EXIST);
                }
            }
            if (teacherClassDTO.getTeacherClassId() != null) {
                if (!teacherClassService.checkTeacherClassExist(teacherClassDTO.getTeacherClassId())) {
                    return new ResultBean(ResultCodeConstant.TEACHER_CLASS_NOT_EXIST);
                }
            }
            return new ResultBean(ResultCodeConstant.SUCCESS, teacherClassService.saveOrUpdate(teacherClassDTO));
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultBean(ResultCodeConstant.SERVER_EXCEPTION);
        }
    }


    /*
     * @author ll
     * @Description 分页查询教师课程
     * @param ListTeacherClassDTO
     * @return ResultBean
     */
    @GetMapping("list")
    public ResultBean list(ListTeacherClassDTO listTeacherClassDTO) {
        try {
            return new ResultBean(ResultCodeConstant.SUCCESS, teacherClassService.list(listTeacherClassDTO));
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultBean(ResultCodeConstant.SERVER_EXCEPTION);
        }
    }


    /*
     * @author ll
     * @Description 删除教师课程信息
     * @param String
     * @return ResultBean
     */
    @DeleteMapping("delete")
    public ResultBean delete(Integer teacherClassId) {
        try {
            teacherClassService.delete(teacherClassId);
            return new ResultBean(ResultCodeConstant.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultBean(ResultCodeConstant.SERVER_EXCEPTION);
        }
    }
}
