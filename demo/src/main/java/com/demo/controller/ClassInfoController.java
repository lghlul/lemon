package com.demo.controller;

import com.demo.constant.ResultCodeConstant;
import com.demo.dto.*;
import com.demo.common.ResultBean;
import com.demo.model.ClassInfo;
import com.demo.service.IClassInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName ClassController
 * @Description 课程控制器
 * @Auther ll
 **/
@RestController
@RequestMapping("/class")
public class ClassInfoController {

    @Autowired
    private IClassInfoService classInfoService;


    /*
     * @author ll
     * @Description 添加课程信息
     * @param ClassInfoDTO
     * @return ResultBean
     */
    @PostMapping("save")
    public ResultBean save(ClassInfoDTO classInfoDTO) {
        try {
            //校验编号
            if (classInfoService.checkClassExist(classInfoDTO)) {
                //编号重复
                return new ResultBean(ResultCodeConstant.NUMBER_REPEAT, "课程编号重复");
            } else {
                return new ResultBean(ResultCodeConstant.SUCCESS, "成功", classInfoService.saveOrUpdate(classInfoDTO));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultBean(ResultCodeConstant.SERVER_EXCEPTION, "服务器异常");
        }
    }

    /*
     * @author ll
     * @Description 删除课程信息
     * @param String
     * @return ResultBean
     */
    @DeleteMapping("delete")
    public ResultBean delete(Integer classId) {
        try {
            //校验编号
            classInfoService.delete(classId);
            return new ResultBean(ResultCodeConstant.SUCCESS, "成功");

        } catch (Exception e) {
            e.printStackTrace();
            return new ResultBean(ResultCodeConstant.SERVER_EXCEPTION, "服务器异常");
        }
    }

    /*
     * @author ll
     * @Description 更新课程信息
     * @param UpdateClassDTO
     * @return ResultBean
     */
    @PutMapping("update")
    public ResultBean update(ClassInfoDTO classInfoDTO) {
        try {
            //查询课程是否存在
            ClassInfoDTO classInfo = new ClassInfoDTO();
            classInfo.setClassId(classInfoDTO.getClassId());
            if (!classInfoService.checkClassExist(classInfo)) {
                //课程不存在
                return new ResultBean(ResultCodeConstant.CLASS_NOT_EXIST, "课程不存在");
            }
            classInfo.setClassId(null);
            classInfo.setClassNumber(classInfoDTO.getClassNumber());
            ClassInfo classInfoDO = classInfoService.get(classInfo);
            //校验编号
            if (classInfoDO != null && classInfoDO.getClassId() != classInfoDTO.getClassId()) {
                return new ResultBean(ResultCodeConstant.NUMBER_REPEAT, "课程编号已存在");
            } else {
                return new ResultBean(ResultCodeConstant.SUCCESS, "成功", classInfoService.saveOrUpdate(classInfoDTO));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultBean(ResultCodeConstant.SERVER_EXCEPTION);
        }
    }

    /*
     * @author ll
     * @Description 分页查询课程列表
     * @param ListClassInfoDTO
     * @return ResultBean
     */
    @GetMapping("list")
    public ResultBean list(ListClassInfoDTO listClassInfoDTO) {
        try {
            return new ResultBean(ResultCodeConstant.SUCCESS, "成功", classInfoService.list(listClassInfoDTO));
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultBean(ResultCodeConstant.SERVER_EXCEPTION, "服务器异常");
        }
    }

    /*
     * @author ll
     * @Description 根据主键查询课程信息
     * @param Integer
     * @return ResultBean
     */
    @GetMapping("get")
    public ResultBean get(Integer classId) {
        try {
            ClassInfoDTO classInfoDTO = new ClassInfoDTO();
            classInfoDTO.setClassId(classId);
            if (!classInfoService.checkClassExist(classInfoDTO)) {
                //课程不存在
                return new ResultBean(ResultCodeConstant.CLASS_NOT_EXIST, "课程不存在");
            }
            ClassInfo classInfo = classInfoService.get(classInfoDTO);
            return new ResultBean(ResultCodeConstant.SUCCESS , "成功" , classInfo);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResultBean(ResultCodeConstant.SERVER_EXCEPTION , "服务器异常");
        }
    }

}
