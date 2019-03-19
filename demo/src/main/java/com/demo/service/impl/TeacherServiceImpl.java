package com.demo.service.impl;

import com.demo.constant.ResultCodeConstant;
import com.demo.dto.*;
import com.demo.constant.CommonConstant;
import com.demo.common.ResultBean;
import com.demo.mapper.*;
import com.demo.model.ClassInfo;
import com.demo.model.Teacher;
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
    private ClassInfoMapper classMapper;

    @Autowired
    private TeacherClassMapper teacherClassMapper;


    @Override
    public ResultBean save(AddTeacherDTO addTeacherDTO) throws Exception {
        Teacher teacher = teacherMapper.get(addTeacherDTO.getTeacherNumber());
        if (teacher == null) {
            //编号不存在 可以添加
            addTeacherDTO.setCreateTime(System.currentTimeMillis());
            if (teacherMapper.save(addTeacherDTO) > 0) {
                return new ResultBean(ResultCodeConstant.SUCCESS);
            } else {
                return new ResultBean(ResultCodeConstant.FAIL);
            }
        } else {
            //编号重复
            return new ResultBean(ResultCodeConstant.NUMBER_REPEAT);
        }
    }

    @Override
    public ResultBean list(ListTeacherDTO listTeacherDTO) throws Exception {
        int pageSize = listTeacherDTO.getPageSize();
        listTeacherDTO.setOffset((listTeacherDTO.getPageNumber() - 1) * pageSize);
        List<Teacher> teacherList = this.teacherMapper.list(listTeacherDTO);
        int totalCount = this.teacherMapper.count(listTeacherDTO);
        int totalPage = totalCount % pageSize == 0 ? totalCount / pageSize : totalCount / pageSize + 1;
        Map<String, Object> map = new HashMap<>();
        map.put("totalCount", totalCount);
        map.put("teacherList", teacherList);
        map.put("totalPage", totalPage);
        return new ResultBean(ResultCodeConstant.SUCCESS, map);
    }

    @Override
    public ResultBean addTeacherClass(AddTeacherClassDTO addTeacherClassDTO) throws Exception {
        //校验 老师是否存在
        if (!checkTeacherExist(addTeacherClassDTO.getTeacherNumber())) {
            return new ResultBean(ResultCodeConstant.TEACHER_NOT_EXIST);
        }

        //校验课程是否存在
        ClassInfo classDO = classMapper.get(addTeacherClassDTO.getClassNumber());
        if (classDO == null) {
            return new ResultBean(ResultCodeConstant.CLASS_NOT_EXIST);
        }
        addTeacherClassDTO.setCreateTime(System.currentTimeMillis());
        if (this.teacherClassMapper.save(addTeacherClassDTO) > 0) {
            return new ResultBean(ResultCodeConstant.SUCCESS);
        } else {
            return new ResultBean(ResultCodeConstant.FAIL);
        }
    }


    @Override
    public ResultBean listTeacherClass(ListTeacherClassDTO listTeacherClassDTO) throws Exception {
        int pageSize = listTeacherClassDTO.getPageSize();
        listTeacherClassDTO.setOffset((listTeacherClassDTO.getPageNumber() - 1) * pageSize);
        List<ListTeacherClassDTO> teacherClassList = this.teacherClassMapper.list(listTeacherClassDTO);
        int totalConunt = this.teacherClassMapper.count(listTeacherClassDTO);
        int totalPage = totalConunt % pageSize == 0 ? totalConunt / pageSize : totalConunt / pageSize + 1;
        Map<String, Object> map = new HashMap<>();
        map.put("totalCount", totalConunt);
        map.put("teacherClassList", teacherClassList);
        map.put("totalPage", totalPage);
        return new ResultBean(ResultCodeConstant.SUCCESS, map);
    }


    @Override
    public ResultBean listByTerm(String teacherNumber) throws Exception {
        //校验 老师是否存在
        if (!checkTeacherExist(teacherNumber)) {
            return new ResultBean(ResultCodeConstant.TEACHER_NOT_EXIST);
        }
        List<ListTeacherClassByTermDTO> listTeacherClassByTermDTOS = this.teacherClassMapper.listByTerm(teacherNumber);
        return new ResultBean(ResultCodeConstant.SUCCESS, listTeacherClassByTermDTOS);
    }

    /**
     * @param teacherNumber
     * @return false 不存在 true 存在
     * @description 校验教师是否存在
     */
    private boolean checkTeacherExist(String teacherNumber) throws Exception {
        //校验 老师是否存在
        Teacher teacherDO = teacherMapper.get(teacherNumber);
        if (teacherDO == null) {
            return false;
        }
        return true;
    }


    @Override
    public ResultBean listByLevel(String teacherNumber) throws Exception {
        //校验 老师是否存在
        Teacher teacherDO = teacherMapper.get(teacherNumber);
        if (teacherDO == null) {
            return new ResultBean(ResultCodeConstant.TEACHER_NOT_EXIST);
        }
        //校验是否有教务主任权限
        if (teacherDO.getTeacherLevel() != CommonConstant.TEACHER_LEVEL_REGISTRAR) {
            //不是教务主任没有权限
            return new ResultBean(ResultCodeConstant.NO_PERMISSION);
        }
        List<ListTeacherClassByTermDTO> listTeacherClassByTermDTOS = this.teacherClassMapper.listByTerm(null);
        return new ResultBean(ResultCodeConstant.SUCCESS, listTeacherClassByTermDTOS);
    }

    @Override
    public ResultBean listWithScore(String teacherNumber) throws Exception {
        //校验 老师是否存在
        Teacher teacherDO = teacherMapper.get(teacherNumber);
        //校验是否有教务主任权限
        if (teacherDO.getTeacherLevel() != CommonConstant.TEACHER_LEVEL_REGISTRAR) {
            //不是教务主任没有权限
            return new ResultBean(ResultCodeConstant.NO_PERMISSION);
        }
        List<ListTeacherClassScoreDTO> listTeacherClassScoreDTOS = this.teacherClassMapper.listWithScore();
        return new ResultBean(ResultCodeConstant.SUCCESS, listTeacherClassScoreDTOS);
    }

    @Override
    public ResultBean delete(String teacherNumber) throws Exception {
        if (teacherMapper.delete(teacherNumber) > 0) {
            return new ResultBean(ResultCodeConstant.SUCCESS);
        } else {
            return new ResultBean(ResultCodeConstant.FAIL);
        }
    }

    @Override
    public ResultBean update(UpdateTeacherDTO updateTeacherDTO) throws Exception {
        if (teacherMapper.update(updateTeacherDTO) > 0) {
            return new ResultBean(ResultCodeConstant.SUCCESS);
        } else {
            return new ResultBean(ResultCodeConstant.FAIL);
        }
    }
}
