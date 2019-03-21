package com.demo.controller;

import com.demo.common.ResultBean;
import com.demo.constant.CommonConstant;
import com.demo.constant.ResultCodeConstant;
import com.demo.dto.*;
import com.demo.model.Teacher;
import com.demo.service.ClazzService;
import com.demo.service.TeacherClazzService;
import com.demo.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName TeacherController
 * @Description 教师控制器
 * @Auther ll
 **/
@RestController
@RequestMapping("/teacherClazz")
public class TeacherClazzController {

    @Autowired
    private TeacherClazzService teacherClazzService;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private ClazzService clazzService;


    /*
     * @author ll
     * @Description 添加教师课程信息
     * @param TeacherClazzDTO
     * @return ResultBean
     */
    @PostMapping("saveOrUpdate")
    public ResultBean saveOrUpdate(TeacherClazzDTO teacherClazzDTO) {
        try {
            //校验 老师是否存在
            if (!teacherService.checkTeacherNumberExist(teacherClazzDTO.getTeacherNumber())) {
                ResultBean resultBean = new ResultBean(ResultCodeConstant.TEACHER_NOT_EXIST);
                return resultBean;
            }
            //校验课程是否存在
            if (!clazzService.checkClazzNumberExist(teacherClazzDTO.getClazzNumber())) {
                ResultBean resultBean = new ResultBean(ResultCodeConstant.CLAZZ_NOT_EXIST);
                return resultBean;
            }
            TeacherClazzDTO resultTeacherClazz = teacherClazzService.saveOrUpdate(teacherClazzDTO);
            ResultBean resultBean = new ResultBean(ResultCodeConstant.SUCCESS, resultTeacherClazz);
            return resultBean;
        } catch (Exception e) {
            ResultBean resultBean = new ResultBean(ResultCodeConstant.SERVER_EXCEPTION);
            return resultBean;
        }
    }


    /*
     * @author ll
     * @Description 分页查询教师课程
     * @param TeacherClazzQueryDTO
     * @return ResultBean
     */
    @GetMapping("list")
    public ResultBean list(TeacherClazzQueryDTO teacherClazzQueryDTO) {
        try {
            Object list = teacherClazzService.list(teacherClazzQueryDTO);
            ResultBean resultBean = new ResultBean(ResultCodeConstant.SUCCESS, list);
            return resultBean;
        } catch (Exception e) {
            ResultBean resultBean = new ResultBean(ResultCodeConstant.SERVER_EXCEPTION);
            return resultBean;
        }
    }


    /*
     * @author ll
     * @Description 删除教师课程信息
     * @param String
     * @return ResultBean
     */
    @DeleteMapping("delete")
    public ResultBean delete(Integer teacherNumber, Integer clazzNumber) {
        try {
            teacherClazzService.delete(teacherNumber, clazzNumber);
            ResultBean resultBean = new ResultBean(ResultCodeConstant.SUCCESS);
            return resultBean;
        } catch (Exception e) {
            ResultBean resultBean = new ResultBean(ResultCodeConstant.SERVER_EXCEPTION);
            return resultBean;
        }
    }


    /*
     * @author ll
     * @Description 查询教师本人每学年，学科平均成绩，最高分，最低分
     * @param teacherNumber
     * @return ResultBean
     */
    @GetMapping("listByTerm")
    public ResultBean listByTerm(Integer teacherNumber) {
        try {
            if (teacherService.checkTeacherNumberExist(teacherNumber)) {
                List<TeacherClazzTermQueryDTO> teacherClazzTermQueryDTOS = teacherClazzService.listByTerm(teacherNumber);
                ResultBean resultBean = new ResultBean(ResultCodeConstant.SUCCESS, teacherClazzTermQueryDTOS);
                return resultBean;
            } else {
                ResultBean resultBean = new ResultBean(ResultCodeConstant.TEACHER_NOT_EXIST);
                return resultBean;
            }
        } catch (Exception e) {
            ResultBean resultBean = new ResultBean(ResultCodeConstant.SERVER_EXCEPTION);
            return resultBean;
        }
    }

    /*
     * @author ll
     * @Description 查询每学年学科平均成绩，最高分，最低分
     * @param teacherNumber
     * @return ResultBean
     */
    @GetMapping("listClazzByTeacherLevel")
    public ResultBean listClazzByTeacherLevel(Integer teacherNumber) {
        try {
            ResultBean resultBean = checkPermission(teacherNumber);
            if (resultBean != null) {
                return resultBean;
            }
            List<TeacherClazzTermQueryDTO> teacherClazzTermQueryDTOS = teacherClazzService.listClazzByTeacherLevel();
            resultBean = new ResultBean(ResultCodeConstant.SUCCESS, teacherClazzTermQueryDTOS);
            return resultBean;
        } catch (Exception e) {
            ResultBean resultBean = new ResultBean(ResultCodeConstant.SERVER_EXCEPTION);
            return resultBean;
        }

    }


    /*
     * @author ll
     * @Description 查询教师-学科平均成绩，最高分，最低分
     * @param teacherNumber
     * @return ResultBean
     */
    @GetMapping("listTeacherByTeacherLevel")
    public ResultBean listTeacherByTeacherLevel(Integer teacherNumber) {
        try {
            ResultBean resultBean = checkPermission(teacherNumber);
            if (resultBean != null) {
                return resultBean;
            }
            List<TeacherClazzScoreQueryDTO> teacherClazzScoreQueryDTOS = teacherClazzService.listTeacherByTeacherLevel();
            resultBean = new ResultBean(ResultCodeConstant.SUCCESS, teacherClazzScoreQueryDTOS);
            return resultBean;
        } catch (Exception e) {
            ResultBean resultBean = new ResultBean(ResultCodeConstant.SERVER_EXCEPTION);
            return resultBean;
        }
    }

    /*
     * @author ll
     * @Description 校验是否有权限
     * @param String
     * @return ResultBean
     */
    private ResultBean checkPermission(int teacherNumber) {
        //校验 老师是否存在
        Teacher teacherDO = teacherService.read(teacherNumber);
        if (teacherDO == null) {
            ResultBean resultBean = new ResultBean(ResultCodeConstant.TEACHER_NOT_EXIST);
            return resultBean;
        }
        //校验是否有教务主任权限
        if (teacherDO.getTeacherLevel() != CommonConstant.TEACHER_LEVEL_REGISTRAR) {
            //不是教务主任没有权限
            ResultBean resultBean = new ResultBean(ResultCodeConstant.NO_PERMISSION);
            return resultBean;
        }
        return null;
    }
}
