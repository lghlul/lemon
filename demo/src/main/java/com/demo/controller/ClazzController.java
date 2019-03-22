package com.demo.controller;

import com.demo.common.PageResult;
import com.demo.constant.ResultCodeConstant;
import com.demo.dto.*;
import com.demo.common.ResultBean;
import com.demo.model.Clazz;
import com.demo.service.ClazzService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
            Clazz clazz = clazzService.readById(clazzDTO.getClazzId());

            if (clazzDTO.getClazzNumber() != null) {
                //更新
                //校验编号
                if (clazz != null && clazz.getClazzNumber() != clazzDTO.getClazzNumber()) {
                    ResultBean resultBean = new ResultBean(ResultCodeConstant.NUMBER_REPEAT);
                    return resultBean;
                }
            } else {
                //添加
                //校验编号
                if (clazz != null) {
                    ResultBean resultBean = new ResultBean(ResultCodeConstant.NUMBER_REPEAT);
                    return resultBean;
                }
            }
            Clazz clazzModel = this.dtoToModel(clazzDTO);
            Clazz resultClazz = clazzService.saveOrUpdate(clazzModel);
            ClazzDTO resultClazzDTO = this.modelToDto(resultClazz);
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
                List<ClazzDTO> clazzDTOList = this.batchModelToDto(clazzList);
                pageResult.setList(clazzDTOList);
                ResultBean resultBean = new ResultBean(ResultCodeConstant.SUCCESS, pageResult);
                return resultBean;
            } else {
                clazzList = clazzService.list(clazzQuery);
                List<ClazzDTO> clazzDTOList = this.batchModelToDto(clazzList);
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
    public ResultBean read(Integer clazzNumber) {
        try {
            Clazz clazz = clazzService.read(clazzNumber);
            if (clazz == null) {
                //课程不存在
                ResultBean resultBean = new ResultBean(ResultCodeConstant.CLAZZ_NOT_EXIST);
                return resultBean;
            } else {
                ClazzDTO clazzDTO = this.modelToDto(clazz);
                ResultBean resultBean = new ResultBean(ResultCodeConstant.SUCCESS, clazzDTO);
                return resultBean;
            }
        } catch (Exception e) {
            ResultBean resultBean = new ResultBean(ResultCodeConstant.SERVER_EXCEPTION);
            return resultBean;
        }
    }

    /*
     * @author ll
     * @Description dto转model
     * @param ClazzDTO
     * @return Clazz
     */
    private Clazz dtoToModel(ClazzDTO clazzDTO) {
        Clazz clazz = new Clazz();
        clazz.setClazzId(clazzDTO.getClazzId());
        clazz.setClazzName(clazzDTO.getClazzName());
        clazz.setClazzNumber(clazzDTO.getClazzNumber());
        clazz.setCreateTime(clazzDTO.getCreateTime());
        return clazz;
    }

    /*
     * @author ll
     * @Description model转dto
     * @param Clazz
     * @return ClazzDTO
     */
    private ClazzDTO modelToDto(Clazz clazz) {
        ClazzDTO clazzDTO = new ClazzDTO();
        clazzDTO.setClazzId(clazz.getClazzId());
        clazzDTO.setClazzName(clazz.getClazzName());
        clazzDTO.setClazzNumber(clazz.getClazzNumber());
        clazzDTO.setCreateTime(clazz.getCreateTime());
        return clazzDTO;
    }

    /*
     * @author ll
     * @Description model转dto(批量)
     * @param List<Clazz>
     * @return List<ClazzDTO>
     */
    private List<ClazzDTO> batchModelToDto(List<Clazz> clazzList) {
        List<ClazzDTO> clazzDTOList = null;
        if (clazzList != null) {
            clazzDTOList = new ArrayList<>();
            for (Clazz clazz : clazzList) {
                ClazzDTO clazzDTO = this.modelToDto(clazz);
                clazzDTOList.add(clazzDTO);
            }
        }
        return clazzDTOList;
    }

}
