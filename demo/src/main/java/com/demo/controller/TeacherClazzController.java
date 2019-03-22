package com.demo.controller;

import com.demo.common.PageResult;
import com.demo.common.ResultBean;
import com.demo.constant.CommonConstant;
import com.demo.constant.ResultCodeConstant;
import com.demo.dto.*;
import com.demo.model.*;
import com.demo.service.ClazzService;
import com.demo.service.TeacherClazzService;
import com.demo.service.TeacherService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
            TeacherClazz teacherClazz = this.dtoToModel(teacherClazzDTO);
            TeacherClazz resultTeacherClazz = teacherClazzService.saveOrUpdate(teacherClazz);
            TeacherClazzDTO resultTeacherDTOClazz = this.modelToDto(resultTeacherClazz);
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
                List<TeacherClazzDTO> teacherClazzDTOList = this.batchModelToDto(teacherClazzList);
                pageResult.setList(teacherClazzDTOList);
                ResultBean resultBean = new ResultBean(ResultCodeConstant.SUCCESS, pageResult);
                return resultBean;
            } else {
                teacherClazzList = teacherClazzService.list(teacherClazzQuery);
                List<TeacherClazzDTO> teacherClazzDTOList = this.batchModelToDto(teacherClazzList);
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
    public ResultBean delete(Integer teacherNumber, Integer clazzNumber) {
        try {
            teacherClazzService.delete(teacherNumber, clazzNumber);
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
    public ResultBean listByTerm(Integer teacherNumber) {
        try {
            List<ClazzTermReport> clazzTermReports = teacherClazzService.listByTerm(teacherNumber);
            List<ClazzTermReportDTO> clazzTermReportDTOList = this.batchClazzTermReportModelToDto(clazzTermReports);
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
    public ResultBean listClazzByTeacherLevel(Integer teacherNumber) {
        try {
            ResultBean resultBean = checkPermission(teacherNumber);
            if (resultBean != null) {
                return resultBean;
            }
            List<ClazzTermReport> clazzTermReports = teacherClazzService.listClazzByTeacherLevel();
            List<ClazzTermReportDTO> clazzTermReportDTOList = this.batchClazzTermReportModelToDto(clazzTermReports);
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
    public ResultBean listTeacherByTeacherLevel(Integer teacherNumber) {
        try {
            ResultBean resultBean = checkPermission(teacherNumber);
            if (resultBean != null) {
                return resultBean;
            }
            List<TeacherClazzReport> teacherClazzReports = teacherClazzService.listTeacherByTeacherLevel();

            List<TeacherClazzReportDTO> teacherClazzReportDTOList = this.batchTeacherClazzReportModelToDto(teacherClazzReports);
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
    private ResultBean checkPermission(Integer teacherNumber) {
        //校验 老师是否存在
        Teacher teacher = teacherService.read(teacherNumber);
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
     * @Description dto转model
     * @param TeacherClazzDTO
     * @return TeacherClazz
     */
    private TeacherClazz dtoToModel(TeacherClazzDTO teacherClazzDTO) {
        TeacherClazz teacherClazz = new TeacherClazz();
        teacherClazz.setCreateTime(teacherClazzDTO.getCreateTime());
        teacherClazz.setClazzNumber(teacherClazzDTO.getClazzNumber());
        teacherClazz.setTeacherNumber(teacherClazzDTO.getTeacherNumber());
        return teacherClazz;
    }

    /*
     * @author ll
     * @Description model转dto
     * @param TeacherClazz
     * @return TeacherClazzDTO
     */
    private TeacherClazzDTO modelToDto(TeacherClazz teacherClazz) {
        TeacherClazzDTO teacherClazzDTO = new TeacherClazzDTO();
        teacherClazzDTO.setCreateTime(teacherClazz.getCreateTime());
        teacherClazzDTO.setClazzNumber(teacherClazz.getClazzNumber());
        teacherClazzDTO.setTeacherNumber(teacherClazz.getTeacherNumber());
        return teacherClazzDTO;
    }

    /*
     * @author ll
     * @Description model转dto(批量)
     * @param List<TeacherClazz>
     * @return List<TeacherClazzDTO>
     */
    private List<TeacherClazzDTO> batchModelToDto(List<TeacherClazz> teacherClazzList) throws Exception {
        List<TeacherClazzDTO> teacherClazzDTOList = null;
        if (teacherClazzList != null) {
            teacherClazzDTOList = new ArrayList<>();
            List<Clazz> clazzList = clazzService.list(null);
            // 本应该有缓存   此处用map代替
            Map<Integer, Clazz> clazzMap = new HashMap<>();
            if (clazzList != null) {
                for (Clazz clazz : clazzList) {
                    clazzMap.put(clazz.getClazzNumber(), clazz);
                }
            }

            List<Teacher> teacherList = teacherService.list(null);
            // 本应该有缓存   此处用map代替
            Map<Integer, Teacher> teacherMap = new HashMap<>();
            if (clazzList != null) {
                for (Teacher teacher : teacherList) {
                    teacherMap.put(teacher.getTeacherNumber(), teacher);
                }
            }

            for (TeacherClazz teacherClazz : teacherClazzList) {
                TeacherClazzDTO teacherClazzDTO = this.modelToDto(teacherClazz);
                Clazz clazz = clazzMap.get(teacherClazz.getClazzNumber());
                if (clazz != null) {
                    teacherClazzDTO.setClazzName(clazz.getClazzName());
                }
                //设置教师名称与教师编号
                Teacher teacher = teacherMap.get(teacherClazz.getTeacherNumber());
                if (teacher != null) {
                    teacherClazzDTO.setTeacherName(teacher.getTeacherName());
                }
                teacherClazzDTOList.add(teacherClazzDTO);
            }
        }
        return teacherClazzDTOList;
    }

    /*
     * @author ll
     * @Description ClazzTermReport model转dto(批量)
     * @param List<ClazzTermReport>
     * @return List<ClazzTermReportDTO>
     */
    private List<ClazzTermReportDTO> batchClazzTermReportModelToDto(List<ClazzTermReport> clazzTermReportList) throws Exception {
        List<ClazzTermReportDTO> clazzTermReportDTOList = null;
        if (clazzTermReportList != null) {
            clazzTermReportDTOList = new ArrayList<>();
            List<Clazz> clazzList = clazzService.list(null);
            // 本应该有缓存   此处用map代替
            Map<Integer, Clazz> clazzMap = new HashMap<>();
            if (clazzList != null) {
                for (Clazz clazz : clazzList) {
                    clazzMap.put(clazz.getClazzNumber(), clazz);
                }
            }
            for (ClazzTermReport clazzTermReport : clazzTermReportList) {
                ClazzTermReportDTO clazzTermReportDTO = new ClazzTermReportDTO();
                clazzTermReportDTO.setAvgScore(clazzTermReport.getAvgScore());
                clazzTermReportDTO.setClazzId(clazzTermReport.getClazzId());
                clazzTermReportDTO.setMaxScore(clazzTermReport.getMaxScore());
                clazzTermReportDTO.setMinScore(clazzTermReport.getMinScore());
                clazzTermReportDTO.setClazzNumber(clazzTermReport.getClazzNumber());
                clazzTermReportDTO.setTerm(clazzTermReport.getTerm());
                Clazz clazz = clazzMap.get(clazzTermReport.getClazzNumber());
                if (clazz != null) {
                    clazzTermReportDTO.setClazzName(clazz.getClazzName());
                    clazzTermReportDTO.setClazzId(clazz.getClazzId());
                }
                clazzTermReportDTOList.add(clazzTermReportDTO);
            }
        }
        return clazzTermReportDTOList;
    }

    /*
     * @author ll
     * @Description TeacherClazzReport model转dto(批量)
     * @param List<TeacherClazzReport>
     * @return List<TeacherClazzReportDTO>
     */
    private List<TeacherClazzReportDTO> batchTeacherClazzReportModelToDto(List<TeacherClazzReport> teacherClazzReportList) throws Exception {
        List<TeacherClazzReportDTO> teacherClazzReportDTOList = null;
        if (teacherClazzReportList != null) {
            teacherClazzReportDTOList = new ArrayList<>();
            List<Clazz> clazzList = clazzService.list(null);
            // 本应该有缓存   此处用map代替
            Map<Integer, Clazz> clazzMap = new HashMap<>();
            if (clazzList != null) {
                for (Clazz clazz : clazzList) {
                    clazzMap.put(clazz.getClazzNumber(), clazz);
                }
            }
            List<Teacher> teacherList = teacherService.list(null);
            // 本应该有缓存   此处用map代替
            Map<Integer, Teacher> teacherMap = new HashMap<>();
            if (clazzList != null) {
                for (Teacher teacher : teacherList) {
                    teacherMap.put(teacher.getTeacherNumber(), teacher);
                }
            }
            for (TeacherClazzReport teacherClazzReport : teacherClazzReportList) {
                TeacherClazzReportDTO teacherClazzReportDTO = new TeacherClazzReportDTO();
                teacherClazzReportDTO.setAvgScore(teacherClazzReport.getAvgScore());
                teacherClazzReportDTO.setClazzId(teacherClazzReport.getClazzId());
                teacherClazzReportDTO.setMaxScore(teacherClazzReport.getMaxScore());
                teacherClazzReportDTO.setMinScore(teacherClazzReport.getMinScore());
                teacherClazzReportDTO.setTeacherNumber(teacherClazzReport.getTeacherNumber());
                teacherClazzReportDTO.setClazzNumber(teacherClazzReport.getClazzNumber());
                //设置课程名称与课程编号
                Clazz clazz = clazzMap.get(teacherClazzReport.getClazzNumber());
                if (clazz != null) {
                    teacherClazzReportDTO.setClazzName(clazz.getClazzName());
                    teacherClazzReportDTO.setClazzId(clazz.getClazzId());
                }
                //设置教师名称与教师编号
                Teacher teacher = teacherMap.get(teacherClazzReport.getTeacherNumber());
                if (teacher != null) {
                    teacherClazzReportDTO.setTeacherName(teacher.getTeacherName());
                    teacherClazzReportDTO.setTeacherId(teacher.getTeacherId());
                }
                teacherClazzReportDTOList.add(teacherClazzReportDTO);
            }
        }
        return teacherClazzReportDTOList;
    }

}
