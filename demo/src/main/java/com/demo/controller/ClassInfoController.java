package com.demo.controller;

import com.demo.constant.ResultCodeConstant;
import com.demo.dto.*;
import com.demo.common.ResultBean;
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
    private IClassInfoService classService;


    /*
     * @author ll
     * @Description 添加课程信息
     * @param AddClassDTO
     * @return ResultBean
     */
    @PostMapping("addClass")
    public ResultBean addClass(AddClassDTO addClassDTO) {
        try{
            return classService.save(addClassDTO);
        }catch (Exception e){
            return new ResultBean(ResultCodeConstant.SERVER_EXCEPTION);
        }
    }

    /*
     * @author ll
     * @Description 删除课程信息
     * @param String
     * @return ResultBean
     */
    @DeleteMapping("deleteClass")
    public ResultBean deleteClass(String classNumber) {
        try{
            return classService.delete(classNumber);
        }catch (Exception e){
            return new ResultBean(ResultCodeConstant.SERVER_EXCEPTION);
        }
    }

    /*
     * @author ll
     * @Description 更新课程信息
     * @param UpdateClassDTO
     * @return ResultBean
     */
    @PutMapping("updateClass")
    public ResultBean updateClass(UpdateClassDTO updateClassDTO) {
        try{
            return classService.update(updateClassDTO);
        }catch (Exception e){
            return new ResultBean(ResultCodeConstant.SERVER_EXCEPTION);
        }
    }
}
