package com.demo.service;

import com.demo.dto.*;
import com.demo.model.Clazz;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface ClazzService {

    /*
     * @author ll
     * @Description 删除课程信息
     * @param Integer
     * @return ResultBean
     */
    void delete(Integer clazzNum) throws Exception;

    /*
     * @author ll
     * @Description 查询课程列表
     * @param ClazzQuery
     * @return List<Clazz>
     */
    List<Clazz> list(ClazzQuery clazzQuery) throws Exception;

    /*
     * @author ll
     * @Description 分页查询课程列表
     * @param ClazzQuery
     * @return PageInfo<Clazz>
     */
    PageInfo<Clazz> listPage(ClazzQuery clazzQuery);

    /*
     * @author ll
     * @Description 根据主键读取课程信息
     * @param Integer
     * @return Clazz
     */
    Clazz read(Integer clazzNum) throws Exception;

    /*
     * @author ll
     * @Description 根据课程号读取课程信息
     * @param String
     * @return Clazz
     */
    Clazz readById(String clazzId) throws Exception;

    /*
     * @author ll
     * @Description 更新或者保存课程
     * @param Clazz
     * @return Clazz
     */
    Clazz saveOrUpdate(Clazz clazz) throws Exception;

}
