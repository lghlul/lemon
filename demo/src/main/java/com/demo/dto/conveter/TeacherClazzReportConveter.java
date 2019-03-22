package com.demo.dto.conveter;

import com.demo.dto.TeacherClazzReportDTO;
import com.demo.model.TeacherClazzReport;
import org.springframework.cglib.beans.BeanCopier;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * @Author: nhsoft.ll
 * @Description: TeacherClassReport对象转换
 * @Date:Create：2019/3/22 16:33
 * @Modified By：
 */
public class TeacherClazzReportConveter {
    private static BeanCopier copierdto = BeanCopier.create(TeacherClazzReport.class, TeacherClazzReportDTO.class, false);
    private static BeanCopier copiermodel = BeanCopier.create(TeacherClazzReportDTO.class, TeacherClazzReport.class, false);

    public static TeacherClazzReportDTO createDTO(TeacherClazzReport model) {
        if (model == null) {
            return null;
        }
        TeacherClazzReportDTO dto = new TeacherClazzReportDTO();
        copierdto.copy(model, dto, null);
        return dto;
    }

    public static TeacherClazzReport createModel(TeacherClazzReportDTO dto) {
        if (dto == null) {
            return null;
        }
        TeacherClazzReport model = new TeacherClazzReport();
        copiermodel.copy(dto, model, null);

        return model;
    }

    public static Collection<TeacherClazzReport> createModels(Collection<TeacherClazzReportDTO> dtos) {
        if (dtos == null) {
            return null;
        }
        List<TeacherClazzReport> list = new ArrayList<>();
        Iterator<TeacherClazzReportDTO> iterator = dtos.iterator();
        while (iterator.hasNext()) {
            TeacherClazzReportDTO dto = iterator.next();
            TeacherClazzReport model = createModel(dto);
            list.add(model);
        }
        return list;
    }

    public static Collection<TeacherClazzReportDTO> createDTOs(Collection<TeacherClazzReport> models) {
        if (models == null) {
            return null;
        }
        List<TeacherClazzReportDTO> list = new ArrayList<>();
        Iterator<TeacherClazzReport> iterator = models.iterator();
        while (iterator.hasNext()) {
            TeacherClazzReport model = iterator.next();
            TeacherClazzReportDTO dto = createDTO(model);
            list.add(dto);
        }
        return list;
    }
}
