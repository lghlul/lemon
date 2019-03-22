package com.demo.controller;

import com.demo.common.PageResult;
import com.demo.common.ResultBean;
import com.demo.constant.CommonConstant;
import com.demo.constant.ResultCodeConstant;
import com.demo.dto.*;
import com.demo.dto.conveter.ClazzTermReportConveter;
import com.demo.dto.conveter.TeacherClazzConveter;
import com.demo.dto.conveter.TeacherClazzReportConveter;
import com.demo.model.*;
import com.demo.service.ClazzService;
import com.demo.service.TeacherClazzService;
import com.demo.service.TeacherService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName TeacherClazzController
 * @Description 教师课程控制器
 * @Auther ll
 **/
@RestController
@RequestMapping("/teacherClazz")
public class TeacherClazzController {

    @Autowired
    private TeacherClazzService teacherClazzService;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private ClazzService clazzService;


    /*
     * @author ll
     * @Description 添加教师课程信息
     * @param TeacherClazzDTO
     * @return ResultBean
     */
    @PostMapping("saveOrUpdate")
    public ResultBean saveOrUpdate(TeacherClazzDTO teacherClazzDTO) {
        try {
            TeacherClazz teacherClazz = TeacherClazzConveter.createModel(teacherClazzDTO);
            TeacherClazz resultTeacherClazz = teacherClazzService.saveOrUpdate(teacherClazz);
            TeacherClazzDTO resultTeacherDTOClazz = TeacherClazzConveter.createDTO(resultTeacherClazz);
            ResultBean resultBean = new ResultBean(ResultCodeConstant.SUCCESS, resultTeacherDTOClazz);
            return resultBean;
        } catch (Exception e) {
            ResultBean resultBean = new ResultBean(ResultCodeConstant.SERVER_EXCEPTION);
            return resultBean;
        }
    }


    /*
     * @author ll
     * @Description 分页查询教师课程
     * @param TeacherClazzQuery
     * @return ResultBean
     */
    @GetMapping("list")
    public ResultBean list(TeacherClazzQuery teacherClazzQuery) {
        try {
            List<TeacherClazz> teacherClazzList;
            //是否需要分页
            if (teacherClazzQuery.getPaging()) {
                PageInfo<TeacherClazz> clazzPageInfo = teacherClazzService.listPage(teacherClazzQuery);
                teacherClazzList = clazzPageInfo.getList();
                PageResult pageResult = new PageResult();
                pageResult.setTotalCount(clazzPageInfo.getTotal());
                pageResult.setTotalPage(clazzPageInfo.getPages());
                List<TeacherClazzDTO> teacherClazzDTOList = (List<TeacherClazzDTO>) TeacherClazzConveter.createDTOs(teacherClazzList);
                this.listNameForTeacherClass(teacherClazzDTOList);
                pageResult.setList(teacherClazzDTOList);
                ResultBean resultBean = new ResultBean(ResultCodeConstant.SUCCESS, pageResult);
                return resultBean;
            } else {
                teacherClazzList = teacherClazzService.list(teacherClazzQuery);
                List<TeacherClazzDTO> teacherClazzDTOList = (List<TeacherClazzDTO>) TeacherClazzConveter.createDTOs(teacherClazzList);
                this.listNameForTeacherClass(teacherClazzDTOList);
                ResultBean resultBean = new ResultBean(ResultCodeConstant.SUCCESS, teacherClazzDTOList);
                return resultBean;
            }
        } catch (Exception e) {
            ResultBean resultBean = new ResultBean(ResultCodeConstant.SERVER_EXCEPTION);
            return resultBean;
        }
    }


    /*
     * @author ll
     * @Description 删除教师课程信息
     * @param Integer
     * @Param Integer
     * @return ResultBean
     */
    @DeleteMapping("delete")
    public ResultBean delete(Integer teacherNum, Integer clazzNum) {
        try {
            teacherClazzService.delete(teacherNum, clazzNum);
            ResultBean resultBean = new ResultBean(ResultCodeConstant.SUCCESS);
            return resultBean;
        } catch (Exception e) {
            ResultBean resultBean = new ResultBean(ResultCodeConstant.SERVER_EXCEPTION);
            return resultBean;
        }
    }


    /*
     * @author ll
     * @Description 查询教师本人每学年，学科平均成绩，最高分，最低分
     * @param Integer
     * @return ResultBean
     */
    @GetMapping("listByTerm")
    public ResultBean listByTerm(Integer teacherNum) {
        try {
            List<ClazzTermReport> clazzTermReports = teacherClazzService.listByTerm(teacherNum);
            List<ClazzTermReportDTO> clazzTermReportDTOList = (List<ClazzTermReportDTO>) ClazzTermReportConveter.createDTOs(clazzTermReports);
            this.listClazzName(clazzTermReportDTOList);
            ResultBean resultBean = new ResultBean(ResultCodeConstant.SUCCESS, clazzTermReportDTOList);
            return resultBean;
        } catch (Exception e) {
            ResultBean resultBean = new ResultBean(ResultCodeConstant.SERVER_EXCEPTION);
            return resultBean;
        }
    }

    /*
     * @author ll
     * @Description 查询每学年学科平均成绩，最高分，最低分
     * @param Integer
     * @return ResultBean
     */
    @GetMapping("listClazzByTeacherLevel")
    public ResultBean listClazzByTeacherLevel(Integer teacherNum) {
        try {
            ResultBean resultBean = checkPermission(teacherNum);
            if (resultBean != null) {
                return resultBean;
            }
            List<ClazzTermReport> clazzTermReports = teacherClazzService.listClazzByTeacherLevel();
            List<ClazzTermReportDTO> clazzTermReportDTOList = (List<ClazzTermReportDTO>) ClazzTermReportConveter.createDTOs(clazzTermReports);
            this.listClazzName(clazzTermReportDTOList);
            resultBean = new ResultBean(ResultCodeConstant.SUCCESS, clazzTermReportDTOList);
            return resultBean;
        } catch (Exception e) {
            ResultBean resultBean = new ResultBean(ResultCodeConstant.SERVER_EXCEPTION);
            return resultBean;
        }

    }


    /*
     * @author ll
     * @Description 查询教师-学科平均成绩，最高分，最低分
     * @param Integer
     * @return ResultBean
     */
    @GetMapping("listTeacherByTeacherLevel")
    public ResultBean listTeacherByTeacherLevel(Integer teacherNum) {
        try {
            ResultBean resultBean = checkPermission(teacherNum);
            if (resultBean != null) {
                return resultBean;
            }
            List<TeacherClazzReport> teacherClazzReports = teacherClazzService.listTeacherByTeacherLevel();

            List<TeacherClazzReportDTO> teacherClazzReportDTOList = (List<TeacherClazzReportDTO>) TeacherClazzReportConveter.createDTOs(teacherClazzReports);
            this.listNameForReport(teacherClazzReportDTOList);
            resultBean = new ResultBean(ResultCodeConstant.SUCCESS, teacherClazzReportDTOList);
            return resultBean;
        } catch (Exception e) {
            ResultBean resultBean = new ResultBean(ResultCodeConstant.SERVER_EXCEPTION);
            return resultBean;
        }
    }

    /*
     * @author ll
     * @Description 校验是否有权限
     * @param Integer
     * @return ResultBean
     */
    private ResultBean checkPermission(Integer teacherNum) {
        //校验 老师是否存在
        Teacher teacher = teacherService.read(teacherNum);
        if (teacher == null) {
            ResultBean resultBean = new ResultBean(ResultCodeConstant.TEACHER_NOT_EXIST);
            return resultBean;
        }
        //校验是否有教务主任权限
        if (teacher.getTeacherLevel() != CommonConstant.TEACHER_LEVEL_REGISTRAR) {
            //不是教务主任没有权限
            ResultBean resultBean = new ResultBean(ResultCodeConstant.NO_PERMISSION);
            return resultBean;
        }
        return null;
    }


    /*
     * @author ll
     * @Description 关联查询教师课程的名称
     * @param List<TeacherClazzDTO>
     * @return void
     */
    private void listNameForTeacherClass(List<TeacherClazzDTO> teacherClazzDTOList) throws Exception {
        if (teacherClazzDTOList != null) {
            List<Clazz> clazzList = clazzService.list(null);
            // 本应该有缓存   此处用map代替
            Map<Integer, Clazz> clazzMap = new HashMap<>();
            if (clazzList != null) {
                for (Clazz clazz : clazzList) {
                    clazzMap.put(clazz.getClazzNum(), clazz);
                }
            }

            List<Teacher> teacherList = teacherService.list(null);
            // 本应该有缓存   此处用map代替
            Map<Integer, Teacher> teacherMap = new HashMap<>();
            if (clazzList != null) {
                for (Teacher teacher : teacherList) {
                    teacherMap.put(teacher.getTeacherNum(), teacher);
                }
            }
            for (TeacherClazzDTO teacherClazzDTO : teacherClazzDTOList) {
                Clazz clazz = clazzMap.get(teacherClazzDTO.getClazzNum());
                if (clazz != null) {
                    teacherClazzDTO.setClazzName(clazz.getClazzName());
                }
                //设置教师名称与教师编号
                Teacher teacher = teacherMap.get(teacherClazzDTO.getTeacherNum());
                if (teacher != null) {
                    teacherClazzDTO.setTeacherName(teacher.getTeacherName());
                }
            }
        }
    }

    /*
     * @author ll
     * @Description 关联查询课程名称
     * @param List<ClazzTermReportDTO>
     * @return void
     */
    private void listClazzName(List<ClazzTermReportDTO> clazzTermReportDTOList) throws Exception {
        if (clazzTermReportDTOList != null) {
            List<Clazz> clazzList = clazzService.list(null);
            // 本应该有缓存   此处用map代替
            Map<Integer, Clazz> clazzMap = new HashMap<>();
            if (clazzList != null) {
                for (Clazz clazz : clazzList) {
                    clazzMap.put(clazz.getClazzNum(), clazz);
                }
            }
            for (ClazzTermReportDTO clazzTermReportDTO : clazzTermReportDTOList) {
                Clazz clazz = clazzMap.get(clazzTermReportDTO.getClazzNum());
                if (clazz != null) {
                    clazzTermReportDTO.setClazzName(clazz.getClazzName());
                    clazzTermReportDTO.setClazzId(clazz.getClazzId());
                }
            }
        }
    }

    /*
     * @author ll
     * @Description 关联查询出教师和课程的名称
     * @param List<TeacherClazzReport>
     * @return List<TeacherClazzReportDTO>
     */
    private void listNameForReport(List<TeacherClazzReportDTO> teacherClazzReportDTOList) throws Exception {
        if (teacherClazzReportDTOList != null) {
            List<Clazz> clazzList = clazzService.list(null);
            // 本应该有缓存   此处用map代替
            Map<Integer, Clazz> clazzMap = new HashMap<>();
            if (clazzList != null) {
                for (Clazz clazz : clazzList) {
                    clazzMap.put(clazz.getClazzNum(), clazz);
                }
            }
            List<Teacher> teacherList = teacherService.list(null);
            // 本应该有缓存   此处用map代替
            Map<Integer, Teacher> teacherMap = new HashMap<>();
            if (clazzList != null) {
                for (Teacher teacher : teacherList) {
                    teacherMap.put(teacher.getTeacherNum(), teacher);
                }
            }
            for (TeacherClazzReportDTO teacherClazzReportDTO : teacherClazzReportDTOList) {
                //设置课程名称与课程编号
                Clazz clazz = clazzMap.get(teacherClazzReportDTO.getClazzNum());
                if (clazz != null) {
                    teacherClazzReportDTO.setClazzName(clazz.getClazzName());
                    teacherClazzReportDTO.setClazzId(clazz.getClazzId());
                }
                //设置教师名称与教师编号
                Teacher teacher = teacherMap.get(teacherClazzReportDTO.getTeacherNum());
                if (teacher != null) {
                    teacherClazzReportDTO.setTeacherName(teacher.getTeacherName());
                    teacherClazzReportDTO.setTeacherId(teacher.getTeacherId());
                }
            }
        }
    }

}
