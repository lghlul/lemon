package com.demo.controller;

import com.demo.constant.ResultCodeConstant;
import com.demo.dto.*;
import com.demo.common.ResultBean;
import com.demo.model.Clazz;
import com.demo.service.ClazzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName ClassController
 * @Description 课程控制器
 * @Auther ll
 **/
@RestController
@RequestMapping("/clazz")
public class ClazzController {

    @Autowired
    private ClazzService clazzService;


    /*
     * @author ll
     * @Description 删除课程信息
     * @param String
     * @return ResultBean
     */
    @DeleteMapping("delete")
    public ResultBean delete(Integer clazzNumber) {
        try {
            clazzService.delete(clazzNumber);
            ResultBean resultBean = new ResultBean(ResultCodeConstant.SUCCESS);
            return resultBean;
        } catch (Exception e) {
            ResultBean resultBean = new ResultBean(ResultCodeConstant.SERVER_EXCEPTION);
            return resultBean;
        }
    }

    /*
     * @author ll
     * @Description 添加或者更新课程信息
     * @param ClazzDTO
     * @return ResultBean
     */
    @PostMapping("saveOrUpdate")
    public ResultBean saveOrUpdate(ClazzDTO clazzDTO) {
        try {
            //更新
            if (clazzDTO.getClazzNumber() != null) {
                //查询课程是否存在
                if (!clazzService.checkClazzNumberExist(clazzDTO.getClazzNumber())) {
                    //课程不存在
                    ResultBean resultBean = new ResultBean(ResultCodeConstant.CLAZZ_NOT_EXIST);
                    return resultBean;
                }
                Clazz clazz = clazzService.readById(clazzDTO.getClazzId());
                //校验编号
                if (clazz != null && clazz.getClazzNumber() != clazzDTO.getClazzNumber()) {
                    ResultBean resultBean = new ResultBean(ResultCodeConstant.NUMBER_REPEAT);
                    return resultBean;
                }
            } else {
                //校验编号
                if (clazzService.readById(clazzDTO.getClazzId()) != null) {
                    ResultBean resultBean = new ResultBean(ResultCodeConstant.NUMBER_REPEAT);
                    return resultBean;
                }
            }
            ClazzDTO resultClazz = clazzService.saveOrUpdate(clazzDTO);
            ResultBean resultBean = new ResultBean(ResultCodeConstant.SUCCESS, resultClazz);
            return resultBean;
        } catch (Exception e) {
            ResultBean resultBean = new ResultBean(ResultCodeConstant.SERVER_EXCEPTION);
            return resultBean;
        }
    }

    /*
     * @author ll
     * @Description 分页查询课程列表
     * @param ClazzQueryDTO
     * @return ResultBean
     */
    @GetMapping("list")
    public ResultBean list(ClazzQueryDTO clazzQueryDTO) {
        try {
            Object list = clazzService.list(clazzQueryDTO);
            ResultBean resultBean = new ResultBean(ResultCodeConstant.SUCCESS, list);
            return resultBean;
        } catch (Exception e) {
            ResultBean resultBean = new ResultBean(ResultCodeConstant.SERVER_EXCEPTION);
            return resultBean;
        }
    }

    /*
     * @author ll
     * @Description 根据主键查询课程信息
     * @param Integer
     * @return ResultBean
     */
    @GetMapping("read")
    public ResultBean read(Integer clazzNumber) {
        try {
            Clazz clazz = clazzService.read(clazzNumber);
            if (clazz == null) {
                //课程不存在
                ResultBean resultBean = new ResultBean(ResultCodeConstant.CLAZZ_NOT_EXIST);
                return resultBean;
            } else {
                ResultBean resultBean = new ResultBean(ResultCodeConstant.SUCCESS, clazz);
                return resultBean;
            }
        } catch (Exception e) {
            ResultBean resultBean = new ResultBean(ResultCodeConstant.SERVER_EXCEPTION);
            return resultBean;
        }
    }

}
