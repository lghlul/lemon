package com.demo.service.impl;

import com.demo.domain.DO.*;
import com.demo.domain.DTO.*;
import com.demo.constant.CommonConstant;
import com.demo.common.ResultBean;
import com.demo.common.ResultCodeEnum;
import com.demo.mapper.*;
import com.demo.service.ITeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TeacherServiceImpl implements ITeacherService {

    @Autowired
    private TeacherMapper teacherMapper;

    @Autowired
    private ClassMapper classMapper;

    @Autowired
    private TeacherClassMapper teacherClassMapper;


    @Override
    public ResultBean addTeacher(AddTeacherDTO addTeacherDTO) {
        TeacherDO teacher = teacherMapper.getTeacher(addTeacherDTO.getTeacherNumber());
        if (teacher == null) {
            //编号不存在 可以添加
            addTeacherDTO.setCreateTime(System.currentTimeMillis());
            if (teacherMapper.saveTeacher(addTeacherDTO) > 0) {
                return ResultCodeEnum.SUCCESS.getResponse();
            } else {
                return ResultCodeEnum.FAIL.getResponse();
            }
        } else {
            //编号重复
            return ResultCodeEnum.NUMBER_REPEAT.getResponse();
        }
    }

    @Override
    public ResultBean listTeacher(ListTeacherDTO listTeacherDTO) {
        int pageSize = listTeacherDTO.getPageSize();
        listTeacherDTO.setOffset((listTeacherDTO.getPageNumber() - 1) * pageSize);
        List<TeacherDO> teacherList = this.teacherMapper.listTeacher(listTeacherDTO);
        int totalCount = this.teacherMapper.countTeacher(listTeacherDTO);
        int totalPage = totalCount % pageSize == 0 ? totalCount / pageSize : totalCount / pageSize + 1;
        Map<String, Object> map = new HashMap<>();
        map.put("totalCount", totalCount);
        map.put("teacherList", teacherList);
        map.put("totalPage", totalPage);
        return ResultCodeEnum.SUCCESS.getResponse(map);
    }

    @Override
    public ResultBean addTeacherClass(AddTeacherClassDTO addTeacherClassDTO) {
        //校验 老师是否存在
        if (!checkTeacherExist(addTeacherClassDTO.getTeacherNumber())) {
            return ResultCodeEnum.TEACHER_NOT_EXIST.getResponse();
        }

        //校验课程是否存在
        ClassDO classDO = classMapper.getClass(addTeacherClassDTO.getClassNumber());
        if (classDO == null) {
            return ResultCodeEnum.CLASS_NOT_EXIST.getResponse();
        }
        addTeacherClassDTO.setCreateTime(System.currentTimeMillis());
        if (this.teacherClassMapper.saveTeacherClass(addTeacherClassDTO) > 0) {
            return ResultCodeEnum.SUCCESS.getResponse();
        } else {
            return ResultCodeEnum.FAIL.getResponse();
        }
    }


    @Override
    public ResultBean listTeacherClass(ListTeacherClassDTO listTeacherClassDTO) {
        int pageSize = listTeacherClassDTO.getPageSize();
        listTeacherClassDTO.setOffset((listTeacherClassDTO.getPageNumber() - 1) * pageSize);
        List<ListTeacherClassDTO> teacherClassList = this.teacherClassMapper.listTeacherClass(listTeacherClassDTO);
        int totalConunt = this.teacherClassMapper.countTeacherClass(listTeacherClassDTO);
        int totalPage = totalConunt % pageSize == 0 ? totalConunt / pageSize : totalConunt / pageSize + 1;
        Map<String, Object> map = new HashMap<>();
        map.put("totalCount", totalConunt);
        map.put("teacherClassList", teacherClassList);
        map.put("totalPage", totalPage);
        return ResultCodeEnum.SUCCESS.getResponse(map);
    }


    @Override
    public ResultBean listTeacherClassByTerm(String teacherNumber) {
        //校验 老师是否存在
        if (!checkTeacherExist(teacherNumber)) {
            return ResultCodeEnum.TEACHER_NOT_EXIST.getResponse();
        }
        List<ListTeacherClassByTermDTO> listTeacherClassByTermDTOS = this.teacherClassMapper.listTeacherClassByTerm(teacherNumber);
        return ResultCodeEnum.SUCCESS.getResponse(listTeacherClassByTermDTOS);
    }

    /**
     * @param teacherNumber
     * @return false 不存在 true 存在
     * @description 校验教师是否存在
     */
    private boolean checkTeacherExist(String teacherNumber) {
        //校验 老师是否存在
        TeacherDO teacherDO = teacherMapper.getTeacher(teacherNumber);
        if (teacherDO == null) {
            return false;
        }
        return true;
    }


    @Override
    public ResultBean listTeacherClassByTermWithLevel(String teacherNumber) {
        //校验 老师是否存在
        TeacherDO teacherDO = teacherMapper.getTeacher(teacherNumber);
        if (teacherDO == null) {
            return ResultCodeEnum.TEACHER_NOT_EXIST.getResponse();
        }
        //校验是否有教务主任权限
        if (teacherDO.getTeacherLevel() != CommonConstant.TEACHER_LEVEL_REGISTRAR) {
            //不是教务主任没有权限
            return ResultCodeEnum.NO_PERMISSION.getResponse();
        }
        List<ListTeacherClassByTermDTO> listTeacherClassByTermDTOS = this.teacherClassMapper.listTeacherClassByTerm(null);
        return ResultCodeEnum.SUCCESS.getResponse(listTeacherClassByTermDTOS);
    }

    @Override
    public ResultBean listTeacherClassScore(String teacherNumber) {
        //校验 老师是否存在
        TeacherDO teacherDO = teacherMapper.getTeacher(teacherNumber);
        //校验是否有教务主任权限
        if (teacherDO.getTeacherLevel() != CommonConstant.TEACHER_LEVEL_REGISTRAR) {
            //不是教务主任没有权限
            return ResultCodeEnum.NO_PERMISSION.getResponse();
        }
        List<ListTeacherClassScoreDTO> listTeacherClassScoreDTOS = this.teacherClassMapper.listTeacherClassScore();
        return ResultCodeEnum.SUCCESS.getResponse(listTeacherClassScoreDTOS);
    }
}
