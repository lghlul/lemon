package com.demo.controller;

import com.demo.common.ResultBean;
import com.demo.constant.ResultCodeConstant;
import com.demo.dto.StudentClazzDTO;
import com.demo.dto.StudentClazzQuery;
import com.demo.dto.conveter.StudentClazzConveter;
import com.demo.model.Clazz;
import com.demo.model.StudentClazz;
import com.demo.service.ClazzService;
import com.demo.service.StudentClazzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
            if (studentClazzDTO.getClazzNum() != null && studentClazzDTO.getStudentNum() != null && studentClazzDTO.getScore() == null) {
                //校验是否重复选课
                boolean studentClazzExist = studentClazzService.checkStudentClazzExist(studentClazzDTO.getStudentNum(), studentClazzDTO.getClazzNum());
                if (studentClazzExist) {
                    //已经选过该课程
                    ResultBean resultBean = new ResultBean(ResultCodeConstant.STUDENT_CLAZZ_EXIST);
                    return resultBean;
                }
            }
            StudentClazz studentClazz = StudentClazzConveter.createModel(studentClazzDTO);
            StudentClazz resultStudentClazz = studentClazzService.saveOrUpdate(studentClazz);
            StudentClazzDTO resultStudentClazzDTO = StudentClazzConveter.createDTO(resultStudentClazz);
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
            List<StudentClazzDTO> studentClazzDTOList = (List<StudentClazzDTO>) StudentClazzConveter.createDTOs(studentClazzList);
            listClassName(studentClazzDTOList);
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
    public ResultBean delete(Integer studentNum, Integer clazzNum) {
        try {
            studentClazzService.delete(studentNum, clazzNum);
            ResultBean resultBean = new ResultBean(ResultCodeConstant.SUCCESS);
            return resultBean;
        } catch (Exception e) {
            ResultBean resultBean = new ResultBean(ResultCodeConstant.SERVER_EXCEPTION);
            return resultBean;
        }
    }


    /*
     * @author ll
     * @Description 关联查询出课程名称
     * @param List<studentClazzDTOList>
     * @return void
     */
    private void listClassName(List<StudentClazzDTO> studentClazzDTOList) throws Exception {
        //关联属性不应该关联表查询   应将关联表查询 存入缓存
        if (studentClazzDTOList != null) {
            List<Clazz> clazzList = clazzService.list(null);
            // 本应该有缓存   此处用map代替
            Map<Integer, Clazz> clazzMap = new HashMap<>();
            if (clazzList != null) {
                for (Clazz clazz : clazzList) {
                    clazzMap.put(clazz.getClazzNum(), clazz);
                }
            }
            for (StudentClazzDTO studentClazzDTO : studentClazzDTOList) {
                Clazz clazz = clazzMap.get(studentClazzDTO.getClazzNum());
                if (clazz != null) {
                    studentClazzDTO.setClazzName(clazz.getClazzName());
                }
            }
        }
    }

}
