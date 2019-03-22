package com.demo.dto.conveter;

import com.demo.dto.ClazzTermReportDTO;
import com.demo.model.ClazzTermReport;
import org.springframework.cglib.beans.BeanCopier;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * @Author: nhsoft.ll
 * @Description: ClazzTermReport 对象转换
 * @Date:Create：2019/3/22 16:31
 * @Modified By：
 */
public class ClazzTermReportConveter {
    private static BeanCopier copierdto = BeanCopier.create(ClazzTermReport.class, ClazzTermReportDTO.class, false);
    private static BeanCopier copiermodel = BeanCopier.create(ClazzTermReportDTO.class, ClazzTermReport.class, false);

    public static ClazzTermReportDTO createDTO(ClazzTermReport model) {
        if (model == null) {
            return null;
        }
        ClazzTermReportDTO dto = new ClazzTermReportDTO();
        copierdto.copy(model, dto, null);
        return dto;
    }

    public static ClazzTermReport createModel(ClazzTermReportDTO dto) {
        if (dto == null) {
            return null;
        }
        ClazzTermReport model = new ClazzTermReport();
        copiermodel.copy(dto, model, null);

        return model;
    }

    public static Collection<ClazzTermReport> createModels(Collection<ClazzTermReportDTO> dtos) {
        if (dtos == null) {
            return null;
        }
        List<ClazzTermReport> list = new ArrayList<>();
        Iterator<ClazzTermReportDTO> iterator = dtos.iterator();
        while (iterator.hasNext()) {
            ClazzTermReportDTO dto = iterator.next();
            ClazzTermReport model = createModel(dto);
            list.add(model);
        }
        return list;
    }

    public static Collection<ClazzTermReportDTO> createDTOs(Collection<ClazzTermReport> models) {
        if (models == null) {
            return null;
        }
        List<ClazzTermReportDTO> list = new ArrayList<>();
        Iterator<ClazzTermReport> iterator = models.iterator();
        while (iterator.hasNext()) {
            ClazzTermReport model = iterator.next();
            ClazzTermReportDTO dto = createDTO(model);
            list.add(dto);
        }
        return list;
    }
}
