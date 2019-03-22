package com.demo.dto.conveter;

import com.demo.dto.TeacherClazzDTO;
import com.demo.model.TeacherClazz;
import org.springframework.cglib.beans.BeanCopier;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * @Author: nhsoft.ll
 * @Description: TeacherClazz对象转换
 * @Date:Create：2019/3/22 16:15
 * @Modified By：
 */
public class TeacherClazzConveter {
    private static BeanCopier copierdto = BeanCopier.create(TeacherClazz.class, TeacherClazzDTO.class, false);
    private static BeanCopier copiermodel = BeanCopier.create(TeacherClazzDTO.class, TeacherClazz.class, false);

    public static TeacherClazzDTO createDTO(TeacherClazz model) {
        if (model == null) {
            return null;
        }
        TeacherClazzDTO dto = new TeacherClazzDTO();
        copierdto.copy(model, dto, null);
        return dto;
    }

    public static TeacherClazz createModel(TeacherClazzDTO dto) {
        if (dto == null) {
            return null;
        }
        TeacherClazz model = new TeacherClazz();
        copiermodel.copy(dto, model, null);

        return model;
    }

    public static Collection<TeacherClazz> createModels(Collection<TeacherClazzDTO> dtos) {
        if (dtos == null) {
            return null;
        }
        List<TeacherClazz> list = new ArrayList<>();
        Iterator<TeacherClazzDTO> iterator = dtos.iterator();
        while (iterator.hasNext()) {
            TeacherClazzDTO dto = iterator.next();
            TeacherClazz model = createModel(dto);
            list.add(model);
        }
        return list;
    }

    public static Collection<TeacherClazzDTO> createDTOs(Collection<TeacherClazz> models) {
        if (models == null) {
            return null;
        }
        List<TeacherClazzDTO> list = new ArrayList<>();
        Iterator<TeacherClazz> iterator = models.iterator();
        while (iterator.hasNext()) {
            TeacherClazz model = iterator.next();
            TeacherClazzDTO dto = createDTO(model);
            list.add(dto);
        }
        return list;
    }
}
