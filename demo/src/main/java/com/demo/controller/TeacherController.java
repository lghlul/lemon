package com.demo.controller;

import com.demo.constant.CommonConstant;
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
    public ResultBean list(ListTeacherDTO listTeacherDTO) {
        try {
            return new ResultBean(ResultCodeConstant.SUCCESS, "成功", teacherService.list(listTeacherDTO));
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultBean(ResultCodeConstant.SERVER_EXCEPTION, "服务器异常");
        }
    }


    /*
     * @author ll
     * @Description 查询教师本人每学年，学科平均成绩，最高分，最低分
     * @param teacherNumber
     * @return ResultBean
     */
    @GetMapping("listByTerm")
    public ResultBean listByTerm(Integer teacherId) {
        try {
            TeacherDTO teacherDTO = new TeacherDTO();
            teacherDTO.setTeacherId(teacherId);
            if (teacherService.checkTeacherExist(teacherDTO)) {
                return new ResultBean(ResultCodeConstant.SUCCESS, "成功", teacherService.listByTerm(teacherId));
            } else {
                return new ResultBean(ResultCodeConstant.TEACHER_NOT_EXIST, "教师不存在");
            }

        } catch (Exception e) {
            return new ResultBean(ResultCodeConstant.SERVER_EXCEPTION, "服务器异常");
        }
    }

    /*
     * @author ll
     * @Description 查询每学年学科平均成绩，最高分，最低分
     * @param teacherNumber
     * @return ResultBean
     */
    @GetMapping("listClassByTeacherLevel")
    public ResultBean listClassByTeacherLevel(Integer teacherId) {
        try {
            TeacherDTO teacherDTO = new TeacherDTO();
            teacherDTO.setTeacherId(teacherId);
            ResultBean resultBean = checkPermission(teacherDTO);
            if (resultBean != null) {
                return resultBean;
            }
            return new ResultBean(ResultCodeConstant.SUCCESS, "成功", teacherService.listClassByTeacherLevel());
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultBean(ResultCodeConstant.SERVER_EXCEPTION, "服务器异常");
        }

    }


    /*
     * @author ll
     * @Description 查询教师-学科平均成绩，最高分，最低分
     * @param teacherNumber
     * @return ResultBean
     */
    @GetMapping("listTeacherByTeacherLevel")
    public ResultBean listTeacherByTeacherLevel(Integer teacherId) {
        try {
            TeacherDTO teacherDTO = new TeacherDTO();
            teacherDTO.setTeacherId(teacherId);
            ResultBean resultBean = checkPermission(teacherDTO);
            if (resultBean != null) {
                return resultBean;
            }
            return new ResultBean(ResultCodeConstant.SUCCESS, "成功", teacherService.listTeacherByTeacherLevel());
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultBean(ResultCodeConstant.SERVER_EXCEPTION, "服务器异常");
        }
    }


    /*
     * @author ll
     * @Description 删除教师信息
     * @param String
     * @return ResultBean
     */
    @DeleteMapping("delete")
    public ResultBean delete(Integer teacherId) {
        try {
            teacherService.delete(teacherId);
            return new ResultBean(ResultCodeConstant.SUCCESS, "成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultBean(ResultCodeConstant.SERVER_EXCEPTION, "服务器异常");
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
            TeacherDTO teacher = new TeacherDTO();
            if(teacherDTO.getTeacherId() != null){
                //查询该教师是否存在
                teacher.setTeacherId(teacherDTO.getTeacherId());
                if (!teacherService.checkTeacherExist(teacher)) {
                    //教师不存在
                    return new ResultBean(ResultCodeConstant.TEACHER_NOT_EXIST, "教师不存在");
                }

                teacher.setTeacherId(null);
                teacher.setTeacherNumber(teacherDTO.getTeacherNumber());
                Teacher teacherDO = teacherService.get(teacher);
                //如果改变的编号已经存在与其他学生
                if (teacherDO != null && teacherDO.getTeacherId() != teacherDTO.getTeacherId()) {
                    return new ResultBean(ResultCodeConstant.NUMBER_REPEAT, "教师编号已存在");
                }
            }else{
                teacher.setTeacherNumber(teacherDTO.getTeacherNumber());
                if (teacherService.checkTeacherExist(teacher)){
                    return new ResultBean(ResultCodeConstant.NUMBER_REPEAT, "教师编号已存在");
                }
            }
            return new ResultBean(ResultCodeConstant.SUCCESS, "成功", teacherService.saveOrUpdate(teacherDTO));
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultBean(ResultCodeConstant.SERVER_EXCEPTION, "服务器异常");
        }
    }

    /*
     * @author ll
     * @Description 校验是否有权限
     * @param String
     * @return ResultBean
     */
    private ResultBean checkPermission(TeacherDTO teacherDTO) {
        //校验 老师是否存在
        Teacher teacherDO = teacherService.get(teacherDTO);
        if (teacherDO == null) {
            return new ResultBean(ResultCodeConstant.TEACHER_NOT_EXIST, "教师不存在");
        }
        //校验是否有教务主任权限
        if (teacherDO.getTeacherLevel() != CommonConstant.TEACHER_LEVEL_REGISTRAR) {
            //不是教务主任没有权限
            return new ResultBean(ResultCodeConstant.NO_PERMISSION, "暂无权限");
        }
        return null;
    }

    /*
     * @author ll
     * @Description 根据主键查询教师信息
     * @param Integer
     * @return ResultBean
     */
    @GetMapping("get")
    public ResultBean get(Integer teacherId) {
        try {
            TeacherDTO teacherDTO = new TeacherDTO();
            teacherDTO.setTeacherId(teacherId);
            if (!teacherService.checkTeacherExist(teacherDTO)) {
                //教师不存在
                return new ResultBean(ResultCodeConstant.TEACHER_NOT_EXIST, "教师不存在");
            }
            Teacher teacher = teacherService.get(teacherDTO);
            return new ResultBean(ResultCodeConstant.SUCCESS, "成功", teacher);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResultBean(ResultCodeConstant.SERVER_EXCEPTION, "服务器异常");
        }
    }
}
