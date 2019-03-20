package com.demo.controller;

import com.demo.constant.CommonConstant;
import com.demo.constant.ResultCodeConstant;
import com.demo.dto.*;
import com.demo.common.ResultBean;
import com.demo.model.Student;
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
     * @param TeacherDTO
     * @return ResultBean
     */
    @PostMapping("save")
    public ResultBean save(TeacherDTO teacherDTO) {
        try {
            if (teacherService.checkTeacherExist(teacherDTO)) {
                return new ResultBean(ResultCodeConstant.NUMBER_REPEAT, "教师编号已存在");
            } else {
                return new ResultBean(ResultCodeConstant.SUCCESS, "成功", teacherService.saveOrUpdate(teacherDTO));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultBean(ResultCodeConstant.SERVER_EXCEPTION, "服务器异常");
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
        try {
            return new ResultBean(ResultCodeConstant.SUCCESS, teacherService.list(listTeacherDTO));
        } catch (Exception e) {
            return new ResultBean(ResultCodeConstant.SERVER_EXCEPTION);
        }
    }

    /*
     * @author ll
     * @Description 添加教师课程信息
     * @param TeacherClassDTO
     * @return ResultBean
     */
    @PostMapping("saveTeacherClass")
    public ResultBean saveTeacherClass(TeacherClassDTO teacherClassDTO) {
        return saveOrUpdateTeacherClass(teacherClassDTO);
    }

    private ResultBean saveOrUpdateTeacherClass(TeacherClassDTO teacherClassDTO){
        try {
            if(teacherClassDTO.getTeacherId() != null){
                //校验 老师是否存在
                TeacherDTO teacherDTO = new TeacherDTO();
                teacherDTO.setTeacherId(teacherClassDTO.getTeacherId());
                if (!teacherService.checkTeacherExist(teacherDTO)) {
                    return new ResultBean(ResultCodeConstant.TEACHER_NOT_EXIST, "教师不存在");
                }
            }
            if(teacherClassDTO.getClassId() != null){
                ClassInfoDTO classInfoDTO = new ClassInfoDTO();
                classInfoDTO.setClassId(teacherClassDTO.getClassId());
                //校验课程是否存在
                if (!classInfoService.checkClassExist(classInfoDTO)) {
                    return new ResultBean(ResultCodeConstant.CLASS_NOT_EXIST, "课程不存在");
                }
            }
            return new ResultBean(ResultCodeConstant.SUCCESS, "成功", teacherService.saveOrUpdateTeacherClass(teacherClassDTO));
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultBean(ResultCodeConstant.SERVER_EXCEPTION, "服务器异常");
        }
    }

    /*
     * @author ll
     * @Description 更新教师课程信息
     * @param TeacherClassDTO
     * @return ResultBean
     */
    @PutMapping("updateTeacherClass")
    public ResultBean updateTeacherClass(TeacherClassDTO teacherClassDTO) {
        return saveOrUpdateTeacherClass(teacherClassDTO);
    }

    /*
     * @author ll
     * @Description 分页查询教师课程
     * @param ListTeacherClassDTO
     * @return ResultBean
     */
    @GetMapping("listTeacherClass")
    public ResultBean listTeacherClass(ListTeacherClassDTO listTeacherClassDTO) {
        try {
            return new ResultBean(ResultCodeConstant.SUCCESS, "成功", teacherService.listTeacherClass(listTeacherClassDTO));
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
                return new ResultBean(ResultCodeConstant.SUCCESS, teacherService.listByTerm(teacherId));
            } else {
                return new ResultBean(ResultCodeConstant.TEACHER_NOT_EXIST);
            }

        } catch (Exception e) {
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
            return new ResultBean(ResultCodeConstant.SERVER_EXCEPTION, "服务器异常");
        }
    }

    /*
     * @author ll
     * @Description 更新教师信息
     * @param UpdateTeacherDTO
     * @return ResultBean
     */
    @PutMapping("update")
    public ResultBean update(TeacherDTO teacherDTO) {
        try {
            //查询该教师是否存在
            TeacherDTO teacher = new TeacherDTO();
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
            return new ResultBean(ResultCodeConstant.SUCCESS, "成功", teacherService.saveOrUpdate(teacherDTO));
        } catch (Exception e) {
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
            return new ResultBean(ResultCodeConstant.SUCCESS , "成功" , teacher);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResultBean(ResultCodeConstant.SERVER_EXCEPTION , "服务器异常");
        }
    }
}
