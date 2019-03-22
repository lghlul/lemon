package com.demo.controller;

import com.demo.common.PageResult;
import com.demo.constant.ResultCodeConstant;
import com.demo.dto.*;
import com.demo.common.ResultBean;
import com.demo.model.Teacher;
import com.demo.service.TeacherService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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
     * @param TeacherQuery
     * @return ResultBean
     */
    @GetMapping("list")
    public ResultBean list(TeacherQuery teacherQuery) {
        try {
            List<Teacher> teacherList;
            //是否需要分页
            if (teacherQuery.getPaging()) {
                PageInfo<Teacher> clazzPageInfo = teacherService.listPage(teacherQuery);
                teacherList = clazzPageInfo.getList();
                PageResult pageResult = new PageResult();
                pageResult.setTotalCount(clazzPageInfo.getTotal());
                pageResult.setTotalPage(clazzPageInfo.getPages());
                List<TeacherDTO> teacherDTOList = this.batchModelToDto(teacherList);
                pageResult.setList(teacherDTOList);
                ResultBean resultBean = new ResultBean(ResultCodeConstant.SUCCESS, pageResult);
                return resultBean;
            } else {
                teacherList = teacherService.list(teacherQuery);
                List<TeacherDTO> teacherDTOList = this.batchModelToDto(teacherList);
                ResultBean resultBean = new ResultBean(ResultCodeConstant.SUCCESS, teacherDTOList);
                return resultBean;
            }
        } catch (Exception e) {
            ResultBean resultBean = new ResultBean(ResultCodeConstant.SERVER_EXCEPTION);
            return resultBean;
        }
    }


    /*
     * @author ll
     * @Description 删除教师信息
     * @param Integer
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
     * @param TeacherDTO
     * @return ResultBean
     */
    @PostMapping("saveOrUpdate")
    public ResultBean saveOrUpdate(TeacherDTO teacherDTO) {
        try {
            Teacher teacherDO = teacherService.readById(teacherDTO.getTeacherId());
            if (teacherDTO.getTeacherNumber() != null) {
                //更新
                //如果改变的编号已经存在与其他教师
                if (teacherDO != null && teacherDO.getTeacherId() != teacherDTO.getTeacherId()) {
                    ResultBean resultBean = new ResultBean(ResultCodeConstant.NUMBER_REPEAT);
                    return resultBean;
                }
            } else {
                //添加
                //校验编号
                Teacher teacher = teacherService.readById(teacherDTO.getTeacherId());
                if (teacher != null) {
                    ResultBean resultBean = new ResultBean(ResultCodeConstant.NUMBER_REPEAT);
                    return resultBean;
                }
            }
            Teacher teacher = this.dtoToModel(teacherDTO);
            Teacher resultTeacher = teacherService.saveOrUpdate(teacher);
            TeacherDTO resultTeacherDTO = this.modelToDto(resultTeacher);
            ResultBean resultBean = new ResultBean(ResultCodeConstant.SUCCESS, resultTeacherDTO);
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
    @GetMapping("read")
    public ResultBean read(Integer teacherNumber) {
        try {

            Teacher teacher = teacherService.read(teacherNumber);
            if (teacher == null) {
                //教师不存在
                ResultBean resultBean = new ResultBean(ResultCodeConstant.TEACHER_NOT_EXIST);
                return resultBean;
            }
            TeacherDTO teacherDTO = this.modelToDto(teacher);
            ResultBean resultBean = new ResultBean(ResultCodeConstant.SUCCESS, teacherDTO);
            return resultBean;

        } catch (Exception e) {
            ResultBean resultBean = new ResultBean(ResultCodeConstant.SERVER_EXCEPTION);
            return resultBean;
        }
    }


    /*
     * @author ll
     * @Description dto转model
     * @param TeacherDTO
     * @return Teacher
     */
    private Teacher dtoToModel(TeacherDTO teacherDTO) {
        Teacher teacher = new Teacher();
        teacher.setCreateTime(teacherDTO.getCreateTime());
        teacher.setTeacherId(teacherDTO.getTeacherId());
        teacher.setTeacherName(teacherDTO.getTeacherName());
        teacher.setTeacherLevel(teacherDTO.getTeacherLevel());
        teacher.setTeacherNumber(teacherDTO.getTeacherNumber());
        return teacher;
    }

    /*
     * @author ll
     * @Description model转dto
     * @param Teacher
     * @return TeacherDTO
     */
    private TeacherDTO modelToDto(Teacher teacher) {
        TeacherDTO teacherDTO = new TeacherDTO();
        teacherDTO.setCreateTime(teacher.getCreateTime());
        teacherDTO.setTeacherId(teacher.getTeacherId());
        teacherDTO.setTeacherName(teacher.getTeacherName());
        teacherDTO.setTeacherLevel(teacher.getTeacherLevel());
        teacherDTO.setTeacherNumber(teacher.getTeacherNumber());
        return teacherDTO;
    }

    /*
     * @author ll
     * @Description model转dto(批量)
     * @param List<Teacher>
     * @return List<TeacherDTO>
     */
    private List<TeacherDTO> batchModelToDto(List<Teacher> teacherList) {
        List<TeacherDTO> teacherDTOList = null;
        if (teacherList != null) {
            teacherDTOList = new ArrayList<>();
            for (Teacher teacher : teacherList) {
                TeacherDTO teacherDTO = this.modelToDto(teacher);
                teacherDTOList.add(teacherDTO);
            }
        }
        return teacherDTOList;
    }
}
