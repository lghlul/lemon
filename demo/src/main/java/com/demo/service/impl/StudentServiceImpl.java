package com.demo.service.impl;

import com.demo.domain.DO.StudentClassDO;
import com.demo.domain.DO.TeacherClassDO;
import com.demo.domain.DTO.*;
import com.demo.common.ResultBean;
import com.demo.common.ResultCodeEnum;
import com.demo.domain.DO.StudentDO;
import com.demo.mapper.StudentClassMapper;
import com.demo.mapper.StudentMapper;
import com.demo.mapper.TeacherClassMapper;
import com.demo.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StudentServiceImpl implements IStudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private StudentClassMapper studentClassMapper;

    @Autowired
    private TeacherClassMapper teacherClassMapper;

    @Override
    public ResultBean addStudent(AddStudentDTO addStudentDTO) {
        StudentDO stu = studentMapper.getStudent(addStudentDTO.getStudentNumber());
        if (stu == null) {
            //编号不存在 可以添加
            addStudentDTO.setCreateTime(System.currentTimeMillis());
            if (studentMapper.saveStudent(addStudentDTO) > 0) {
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
    public ResultBean listStudent(ListStudentDTO studentDTO) {
        int pageSize = studentDTO.getPageSize();
        studentDTO.setOffset((studentDTO.getPageNumber() - 1) * pageSize);
        List<StudentDO> studentList = this.studentMapper.listStudent(studentDTO);
        int totalCount = this.studentMapper.countStudent(studentDTO);
        int totalPage = totalCount % pageSize == 0 ? totalCount / pageSize : totalCount / pageSize + 1;

        Map<String, Object> map = new HashMap<>();
        map.put("studentList", studentList);
        map.put("totalCount", totalCount);
        map.put("totalPage", totalPage);

        return ResultCodeEnum.SUCCESS.getResponse(map);
    }


    @Override
    public ResultBean addStudentClass(AddStudentClassDTO addStudentClassDTO) {
        //校验 学生是否存在
        StudentDO stu = studentMapper.getStudent(addStudentClassDTO.getStudentNumber());
        if (stu == null) {
            return ResultCodeEnum.STUDENT_NOT_EXIST.getResponse();
        }

        //检验这么课程是否存在
        GetTeacherClassDTO readTeacherClassDTO = new GetTeacherClassDTO();
        readTeacherClassDTO.setTeacherClassId(addStudentClassDTO.getTeacherClassId());
        TeacherClassDO teacherClass = this.teacherClassMapper.getTeacherClass(readTeacherClassDTO);
        if (teacherClass == null) {
            return ResultCodeEnum.TEACHER_CLASS_MISMATCHING.getResponse();
        }
        //校验是否已经选了该课程
        GetStudentClassDTO getStudentClassDTO = new GetStudentClassDTO();
        getStudentClassDTO.setTeacherClassId(addStudentClassDTO.getTeacherClassId());
        getStudentClassDTO.setStudentNumber(addStudentClassDTO.getStudentNumber());
        StudentClassDO studentClass = this.studentClassMapper.getStudentClass(getStudentClassDTO);
        if (studentClass != null) {
            //已经选了该课程
            return ResultCodeEnum.STUDENT_CLASS_EXIST.getResponse();
        }

        addStudentClassDTO.setCreateTime(System.currentTimeMillis());
        if (this.studentClassMapper.saveStudentClass(addStudentClassDTO) > 0) {
            return ResultCodeEnum.SUCCESS.getResponse();
        } else {
            return ResultCodeEnum.FAIL.getResponse();
        }
    }


    @Override
    public ResultBean updateStudentClass(UpdateStudentClassDTO updateStudentClassDTO) {
        //是否选课
        GetStudentClassDTO getStudentClassDTO = new GetStudentClassDTO();
        getStudentClassDTO.setStudentNumber(updateStudentClassDTO.getStudentNumber());
        getStudentClassDTO.setTeacherClassId(updateStudentClassDTO.getTeacherClassId());
        StudentClassDO studentClass = this.studentClassMapper.getStudentClass(getStudentClassDTO);
        if (studentClass == null) {
            //没有选课
            return ResultCodeEnum.STUDENT_NOT_HAVE_CLASS.getResponse();
        }
        //录入分数
        if (this.studentClassMapper.updateStudentClass(updateStudentClassDTO) > 0) {
            return ResultCodeEnum.SUCCESS.getResponse();
        } else {
            return ResultCodeEnum.FAIL.getResponse();
        }
    }


    @Override
    public ResultBean listStudentClass(ListStudentClassDTO listStudentClassDTO) {
        List<ListStudentClassDTO> studentClassList = this.studentClassMapper.listStudentClass(listStudentClassDTO);
        return ResultCodeEnum.SUCCESS.getResponse(studentClassList);
    }
}
