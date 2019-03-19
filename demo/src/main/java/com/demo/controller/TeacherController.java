package com.demo.controller;

import com.demo.constant.CommonConstant;
import com.demo.constant.ResultCodeConstant;
import com.demo.dto.*;
import com.demo.common.ResultBean;
import com.demo.model.Teacher;
import com.demo.service.IClassInfoService;
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

    @Autowired
    private IClassInfoService classInfoService;

    /*
     * @author ll
     * @Description 添加教师信息
     * @param AddTeacherDTO
     * @return ResultBean
     */
    @PostMapping("add")
    public ResultBean add(AddTeacherDTO addTeacherDTO) {
        try{
            if(teacherService.checkTeacherExist(addTeacherDTO.getTeacherNumber())){
                return new ResultBean(ResultCodeConstant.NUMBER_REPEAT);
            }else{
                return teacherService.save(addTeacherDTO);
            }
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
    @GetMapping("list")
    public ResultBean list(ListTeacherDTO listTeacherDTO) {
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
            //校验 老师是否存在
            if (!teacherService.checkTeacherExist(addTeacherClassDTO.getTeacherNumber())) {
                return new ResultBean(ResultCodeConstant.TEACHER_NOT_EXIST);
            }
            //校验课程是否存在
            if (!classInfoService.checkClassExist(addTeacherClassDTO.getClassNumber())) {
                return new ResultBean(ResultCodeConstant.CLASS_NOT_EXIST);
            }
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
            if(teacherService.checkTeacherExist(teacherNumber)){
                return teacherService.listByTerm(teacherNumber);
            }else{
                return new ResultBean(ResultCodeConstant.TEACHER_NOT_EXIST);
            }

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
    @GetMapping("listClassByTeacherLevel")
    public ResultBean listClassByTeacherLevel(String teacherNumber) {
        try{
            ResultBean resultBean = checkPermission(teacherNumber);
            if(resultBean != null){
                return resultBean;
            }
            return teacherService.listClassByTeacherLevel(teacherNumber);
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
    @GetMapping("listTeacherByTeacherLevel")
    public ResultBean listTeacherByTeacherLevel(String teacherNumber) {
        try{
            ResultBean resultBean = checkPermission(teacherNumber);
            if(resultBean != null){
                return resultBean;
            }
            return teacherService.listTeacherByTeacherLevel(teacherNumber);
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
    @DeleteMapping("delete")
    public ResultBean delete(String teacherNumber) {
        try{
            if(teacherService.checkTeacherExist(teacherNumber)){
                return teacherService.delete(teacherNumber);
            }else{
                return new ResultBean(ResultCodeConstant.TEACHER_NOT_EXIST);
            }

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
    @PutMapping("update")
    public ResultBean update(UpdateTeacherDTO updateTeacherDTO) {
        try{
            if(teacherService.checkTeacherExist(updateTeacherDTO.getTeacherNumber())){
                return teacherService.update(updateTeacherDTO);
            }else{
                return new ResultBean(ResultCodeConstant.TEACHER_NOT_EXIST);
            }
        }catch (Exception e){
            return new ResultBean(ResultCodeConstant.SERVER_EXCEPTION);
        }
    }
    /*
     * @author ll
     * @Description 校验是否有权限
     * @param String
     * @return ResultBean
     */
    private ResultBean checkPermission(String teacherNumber){
        //校验 老师是否存在
        Teacher teacherDO = teacherService.get(teacherNumber);
        if (teacherDO == null) {
            return new ResultBean(ResultCodeConstant.TEACHER_NOT_EXIST);
        }
        //校验是否有教务主任权限
        if (teacherDO.getTeacherLevel() != CommonConstant.TEACHER_LEVEL_REGISTRAR) {
            //不是教务主任没有权限
            return new ResultBean(ResultCodeConstant.NO_PERMISSION);
        }
        return null;
    }
}
