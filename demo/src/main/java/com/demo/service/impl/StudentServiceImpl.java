package com.demo.service.impl;

import com.demo.constant.ResultCodeConstant;
import com.demo.model.Student;
import com.demo.model.StudentClass;
import com.demo.model.TeacherClass;
import com.demo.dto.*;
import com.demo.common.ResultBean;
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
    public ResultBean save(AddStudentDTO addStudentDTO) throws Exception {
        Student stu = studentMapper.get(addStudentDTO.getStudentNumber());
        if (stu == null) {
            //编号不存在 可以添加
            addStudentDTO.setCreateTime(System.currentTimeMillis());
            if (studentMapper.save(addStudentDTO) > 0) {
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
    public ResultBean list(ListStudentDTO studentDTO) throws Exception {
        int pageSize = studentDTO.getPageSize();
        studentDTO.setOffset((studentDTO.getPageNumber() - 1) * pageSize);
        List<Student> studentList = this.studentMapper.list(studentDTO);
        int totalCount = this.studentMapper.count(studentDTO);
        int totalPage = totalCount % pageSize == 0 ? totalCount / pageSize : totalCount / pageSize + 1;

        Map<String, Object> map = new HashMap<>();
        map.put("studentList", studentList);
        map.put("totalCount", totalCount);
        map.put("totalPage", totalPage);

        return new ResultBean(ResultCodeConstant.SUCCESS, map);
    }


    @Override
    public ResultBean addStudentClass(AddStudentClassDTO addStudentClassDTO) throws Exception {
        //校验 学生是否存在
        Student stu = studentMapper.get(addStudentClassDTO.getStudentNumber());
        if (stu == null) {
            return new ResultBean(ResultCodeConstant.STUDENT_NOT_EXIST);
        }

        //检验这么课程是否存在
        GetTeacherClassDTO readTeacherClassDTO = new GetTeacherClassDTO();
        readTeacherClassDTO.setTeacherClassId(addStudentClassDTO.getTeacherClassId());
        TeacherClass teacherClass = this.teacherClassMapper.get(readTeacherClassDTO);
        if (teacherClass == null) {
            return new ResultBean(ResultCodeConstant.TEACHER_CLASS_MISMATCHING);
        }
        //校验是否已经选了该课程
        GetStudentClassDTO getStudentClassDTO = new GetStudentClassDTO();
        getStudentClassDTO.setTeacherClassId(addStudentClassDTO.getTeacherClassId());
        getStudentClassDTO.setStudentNumber(addStudentClassDTO.getStudentNumber());
        StudentClass studentClass = this.studentClassMapper.get(getStudentClassDTO);
        if (studentClass != null) {
            //已经选了该课程
            return new ResultBean(ResultCodeConstant.STUDENT_CLASS_EXIST);
        }

        addStudentClassDTO.setCreateTime(System.currentTimeMillis());
        if (this.studentClassMapper.save(addStudentClassDTO) > 0) {
            return new ResultBean(ResultCodeConstant.SUCCESS);
        } else {
            return new ResultBean(ResultCodeConstant.FAIL);
        }
    }


    @Override
    public ResultBean updateStudentClass(UpdateStudentClassDTO updateStudentClassDTO) throws Exception {
        //是否选课
        GetStudentClassDTO getStudentClassDTO = new GetStudentClassDTO();
        getStudentClassDTO.setStudentNumber(updateStudentClassDTO.getStudentNumber());
        getStudentClassDTO.setTeacherClassId(updateStudentClassDTO.getTeacherClassId());
        StudentClass studentClass = this.studentClassMapper.get(getStudentClassDTO);
        if (studentClass == null) {
            //没有选课
            return new ResultBean(ResultCodeConstant.STUDENT_NOT_HAVE_CLASS);
        }
        //录入分数
        if (this.studentClassMapper.update(updateStudentClassDTO) > 0) {
            return new ResultBean(ResultCodeConstant.SUCCESS);
        } else {
            return new ResultBean(ResultCodeConstant.FAIL);
        }
    }


    @Override
    public ResultBean listStudentClass(ListStudentClassDTO listStudentClassDTO) throws Exception {
        List<ListStudentClassDTO> studentClassList = this.studentClassMapper.list(listStudentClassDTO);
        return new ResultBean(ResultCodeConstant.SUCCESS, studentClassList);
    }


    @Override
    public ResultBean delete(String studentNumber) throws Exception {
        if (studentMapper.delete(studentNumber) > 0) {
            return new ResultBean(ResultCodeConstant.SUCCESS);
        } else {
            return new ResultBean(ResultCodeConstant.FAIL);
        }
    }

    @Override
    public ResultBean update(UpdateStudentDTO updateClassDTO) throws Exception {
        if (studentMapper.update(updateClassDTO) > 0) {
            return new ResultBean(ResultCodeConstant.SUCCESS);
        } else {
            return new ResultBean(ResultCodeConstant.FAIL);
        }
    }
}
