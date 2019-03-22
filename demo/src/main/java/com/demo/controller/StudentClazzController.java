package com.demo.controller;

import com.demo.common.ResultBean;
import com.demo.constant.ResultCodeConstant;
import com.demo.dto.StudentClazzDTO;
import com.demo.dto.StudentClazzQuery;
import com.demo.model.Clazz;
import com.demo.model.StudentClazz;
import com.demo.service.ClazzService;
import com.demo.service.StudentClazzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName StudentClazzController
 * @Description 学生课程控制器
 * @Auther ll
 **/
@RestController
@RequestMapping("/studentClazz")
public class StudentClazzController {


    @Autowired
    private StudentClazzService studentClazzService;

    @Autowired
    private ClazzService clazzService;


    /*
     * @author ll
     * @Description 添加或者更新学生课程
     * @param StudentClazzDTO
     * @return ResultBean
     */
    @PostMapping("saveOrUpdate")
    public ResultBean save(StudentClazzDTO studentClazzDTO) {
        try {
            //添加
            if (studentClazzDTO.getClazzNumber() != null && studentClazzDTO.getStudentNumber() != null && studentClazzDTO.getScore() == null) {
                //校验是否重复选课
                boolean studentClazzExist = studentClazzService.checkStudentClazzExist(studentClazzDTO.getStudentNumber(), studentClazzDTO.getClazzNumber());
                if (studentClazzExist) {
                    //已经选过该课程
                    ResultBean resultBean = new ResultBean(ResultCodeConstant.STUDENT_CLAZZ_EXIST);
                    return resultBean;
                }
            }
            StudentClazz studentClazz = this.dtoToModel(studentClazzDTO);
            StudentClazz resultStudentClazz = studentClazzService.saveOrUpdate(studentClazz);
            StudentClazzDTO resultStudentClazzDTO = this.modelToDto(resultStudentClazz);
            ResultBean resultBean = new ResultBean(ResultCodeConstant.SUCCESS, resultStudentClazzDTO);
            return resultBean;
        } catch (Exception e) {
            ResultBean resultBean = new ResultBean(ResultCodeConstant.SERVER_EXCEPTION);
            return resultBean;
        }
    }

    /*
     * @author ll
     * @Description 学生课程列表(用于查看学生学年成绩)
     * @param StudentClazzQuery
     * @return ResultBean
     */
    @GetMapping("list")
    public ResultBean list(StudentClazzQuery studentClazzQuery) {
        try {
            List<StudentClazz> studentClazzList = studentClazzService.list(studentClazzQuery);
            List<StudentClazzDTO> studentClazzDTOList = this.batchModelToDto(studentClazzList);
            ResultBean resultBean = new ResultBean(ResultCodeConstant.SUCCESS, studentClazzDTOList);
            return resultBean;
        } catch (Exception e) {
            ResultBean resultBean = new ResultBean(ResultCodeConstant.SERVER_EXCEPTION);
            return resultBean;
        }
    }


    /*
     * @author ll
     * @Description 删除学生课程信息
     * @param Integer
     * @param Integer
     * @return ResultBean
     */
    @DeleteMapping("delete")
    public ResultBean delete(Integer studentNumber, Integer clazzNumber) {
        try {
            studentClazzService.delete(studentNumber, clazzNumber);
            ResultBean resultBean = new ResultBean(ResultCodeConstant.SUCCESS);
            return resultBean;
        } catch (Exception e) {
            ResultBean resultBean = new ResultBean(ResultCodeConstant.SERVER_EXCEPTION);
            return resultBean;
        }
    }


    /*
     * @author ll
     * @Description dto转model
     * @param StudentClazzDTO
     * @return StudentClazz
     */
    private StudentClazz dtoToModel(StudentClazzDTO studentClazzDTO) {
        StudentClazz studentClazz = new StudentClazz();
        studentClazz.setCreateTime(studentClazzDTO.getCreateTime());
        studentClazz.setClazzNumber(studentClazzDTO.getClazzNumber());
        studentClazz.setStudentNumber(studentClazzDTO.getStudentNumber());
        studentClazz.setScore(studentClazzDTO.getScore());
        studentClazz.setTerm(studentClazzDTO.getTerm());
        return studentClazz;
    }

    /*
     * @author ll
     * @Description model转dto
     * @param StudentClazz
     * @return StudentClazzDTO
     */
    private StudentClazzDTO modelToDto(StudentClazz studentClazz) {
        StudentClazzDTO studentClazzDTO = new StudentClazzDTO();
        studentClazzDTO.setCreateTime(studentClazz.getCreateTime());
        studentClazzDTO.setClazzNumber(studentClazz.getClazzNumber());
        studentClazzDTO.setStudentNumber(studentClazz.getStudentNumber());
        studentClazzDTO.setScore(studentClazz.getScore());
        studentClazzDTO.setTerm(studentClazz.getTerm());
        return studentClazzDTO;
    }

    /*
     * @author ll
     * @Description model转dto(批量)
     * @param List<StudentClazz>
     * @return List<StudentClazzDTO>
     */
    private List<StudentClazzDTO> batchModelToDto(List<StudentClazz> studentClazzList) throws Exception {
        //关联属性不应该关联表查询   应将关联表查询 存入缓存
        List<StudentClazzDTO> studentClazzDTOList = null;
        if (studentClazzList != null) {
            studentClazzDTOList = new ArrayList<>();
            List<Clazz> clazzList = clazzService.list(null);
            // 本应该有缓存   此处用map代替
            Map<Integer, Clazz> clazzMap = new HashMap<>();
            if (clazzList != null) {
                for (Clazz clazz : clazzList) {
                    clazzMap.put(clazz.getClazzNumber(), clazz);
                }
            }
            for (StudentClazz studentClazz : studentClazzList) {
                StudentClazzDTO studentClazzDTO = this.modelToDto(studentClazz);
                Clazz clazz = clazzMap.get(studentClazz.getClazzNumber());
                if (clazz != null) {
                    studentClazzDTO.setClazzName(clazz.getClazzName());
                }
                studentClazzDTOList.add(studentClazzDTO);
            }
        }
        return studentClazzDTOList;
    }

}
