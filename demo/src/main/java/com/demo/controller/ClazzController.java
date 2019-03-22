package com.demo.controller;

import com.demo.common.PageResult;
import com.demo.constant.ResultCodeConstant;
import com.demo.dto.*;
import com.demo.common.ResultBean;
import com.demo.dto.conveter.ClazzConveter;
import com.demo.model.Clazz;
import com.demo.service.ClazzService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
     * @param Integer
     * @return ResultBean
     */
    @DeleteMapping("delete")
    public ResultBean delete(Integer clazzNum) {
        try {
            clazzService.delete(clazzNum);
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
            Clazz clazz = clazzService.readById(clazzDTO.getClazzId());

            if (clazzDTO.getClazzNum() != null) {
                //更新
                //校验编号
                if (clazz != null && clazz.getClazzNum() != clazzDTO.getClazzNum()) {
                    ResultBean resultBean = new ResultBean(ResultCodeConstant.NUM_REPEAT);
                    return resultBean;
                }
            } else {
                //添加
                //校验编号
                if (clazz != null) {
                    ResultBean resultBean = new ResultBean(ResultCodeConstant.NUM_REPEAT);
                    return resultBean;
                }
            }
            Clazz clazzModel = ClazzConveter.createModel(clazzDTO);
            Clazz resultClazz = clazzService.saveOrUpdate(clazzModel);
            ClazzDTO resultClazzDTO = ClazzConveter.createDTO(resultClazz);
            ResultBean resultBean = new ResultBean(ResultCodeConstant.SUCCESS, resultClazzDTO);
            return resultBean;
        } catch (Exception e) {
            ResultBean resultBean = new ResultBean(ResultCodeConstant.SERVER_EXCEPTION);
            return resultBean;
        }
    }

    /*
     * @author ll
     * @Description 分页查询课程列表
     * @param ClazzQuery
     * @return ResultBean
     */
    @GetMapping("list")
    public ResultBean list(ClazzQuery clazzQuery) {
        try {
            List<Clazz> clazzList;
            //是否需要分页
            if (clazzQuery.getPaging()) {
                PageInfo<Clazz> clazzPageInfo = clazzService.listPage(clazzQuery);
                clazzList = clazzPageInfo.getList();
                PageResult pageResult = new PageResult();
                pageResult.setTotalCount(clazzPageInfo.getTotal());
                pageResult.setTotalPage(clazzPageInfo.getPages());
                List<ClazzDTO> clazzDTOList = (List<ClazzDTO>) ClazzConveter.createDTOs(clazzList);
                pageResult.setList(clazzDTOList);
                ResultBean resultBean = new ResultBean(ResultCodeConstant.SUCCESS, pageResult);
                return resultBean;
            } else {
                clazzList = clazzService.list(clazzQuery);
                List<ClazzDTO> clazzDTOList = (List<ClazzDTO>) ClazzConveter.createDTOs(clazzList);
                ResultBean resultBean = new ResultBean(ResultCodeConstant.SUCCESS, clazzDTOList);
                return resultBean;
            }
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
    public ResultBean read(Integer clazzNum) {
        try {
            Clazz clazz = clazzService.read(clazzNum);
            if (clazz == null) {
                //课程不存在
                ResultBean resultBean = new ResultBean(ResultCodeConstant.CLAZZ_NOT_EXIST);
                return resultBean;
            } else {
                ClazzDTO clazzDTO = ClazzConveter.createDTO(clazz);
                ResultBean resultBean = new ResultBean(ResultCodeConstant.SUCCESS, clazzDTO);
                return resultBean;
            }
        } catch (Exception e) {
            ResultBean resultBean = new ResultBean(ResultCodeConstant.SERVER_EXCEPTION);
            return resultBean;
        }
    }


}
