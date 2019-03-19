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
    private IClassInfoService classInfoService;


    /*
     * @author ll
     * @Description 添加课程信息
     * @param AddClassDTO
     * @return ResultBean
     */
    @PostMapping("add")
    public ResultBean add(AddClassDTO addClassDTO) {
        try {
            //校验编号
            if(classInfoService.checkClassExist(addClassDTO.getClassNumber())){
                //编号重复
                return new ResultBean(ResultCodeConstant.NUMBER_REPEAT);
            }else{
                return classInfoService.save(addClassDTO);
            }
        } catch (Exception e) {
            return new ResultBean(ResultCodeConstant.SERVER_EXCEPTION);
        }
    }

    /*
     * @author ll
     * @Description 删除课程信息
     * @param String
     * @return ResultBean
     */
    @DeleteMapping("delete")
    public ResultBean delete(String classNumber) {
        try {
            //校验编号
            if(classInfoService.checkClassExist(classNumber)){
                return classInfoService.delete(classNumber);
            }else{
                return new ResultBean(ResultCodeConstant.CLASS_NOT_EXIST);
            }

        } catch (Exception e) {
            return new ResultBean(ResultCodeConstant.SERVER_EXCEPTION);
        }
    }

    /*
     * @author ll
     * @Description 更新课程信息
     * @param UpdateClassDTO
     * @return ResultBean
     */
    @PutMapping("update")
    public ResultBean update(UpdateClassDTO updateClassDTO) {
        try {

            //校验编号
            if(classInfoService.checkClassExist(updateClassDTO.getClassNumber())){
                return classInfoService.update(updateClassDTO);
            }else{
                return new ResultBean(ResultCodeConstant.CLASS_NOT_EXIST);
            }
        } catch (Exception e) {
            return new ResultBean(ResultCodeConstant.SERVER_EXCEPTION);
        }
    }

}
