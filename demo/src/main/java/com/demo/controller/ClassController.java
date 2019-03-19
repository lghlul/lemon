package com.demo.controller;

import com.demo.domain.DTO.*;
import com.demo.common.ResultBean;
import com.demo.service.IClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName ClassController
 * @Description 课程控制器
 * @Auther ll
 **/
@RestController
@RequestMapping("/class")
public class ClassController {

    @Autowired
    private IClassService classService;


    /*
     * @author ll
     * @Description 添加课程信息
     * @param AddClassDTO
     * @return ResultBean
     */
    @PostMapping("addClass")
    public ResultBean addClass(AddClassDTO addClassDTO) {
        return classService.addClass(addClassDTO);
    }
}
