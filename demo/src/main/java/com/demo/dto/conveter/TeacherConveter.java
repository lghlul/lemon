package com.demo.dto.conveter;

import com.demo.dto.TeacherDTO;
import com.demo.model.Teacher;
import org.springframework.cglib.beans.BeanCopier;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * @Author: nhsoft.ll
 * @Description: Teacher对象转换
 * @Date:Create：2019/3/22 16:07
 * @Modified By：
 */

public class TeacherConveter {
    private static BeanCopier copierdto = BeanCopier.create(Teacher.class, TeacherDTO.class, false);
    private static BeanCopier copiermodel = BeanCopier.create(TeacherDTO.class, Teacher.class, false);

    public static TeacherDTO createDTO(Teacher model) {
        if (model == null) {
            return null;
        }
        TeacherDTO dto = new TeacherDTO();
        copierdto.copy(model, dto, null);
        return dto;
    }

    public static Teacher createModel(TeacherDTO dto) {
        if (dto == null) {
            return null;
        }
        Teacher model = new Teacher();
        copiermodel.copy(dto, model, null);

        return model;
    }

    public static Collection<Teacher> createModels(Collection<TeacherDTO> dtos) {
        if (dtos == null) {
            return null;
        }
        List<Teacher> list = new ArrayList<>();
        Iterator<TeacherDTO> iterator = dtos.iterator();
        while (iterator.hasNext()) {
            TeacherDTO dto = iterator.next();
            Teacher model = createModel(dto);
            list.add(model);
        }
        return list;
    }

    public static Collection<TeacherDTO> createDTOs(Collection<Teacher> models) {
        if (models == null) {
            return null;
        }
        List<TeacherDTO> list = new ArrayList<>();
        Iterator<Teacher> iterator = models.iterator();
        while (iterator.hasNext()) {
            Teacher model = iterator.next();
            TeacherDTO dto = createDTO(model);
            list.add(dto);
        }
        return list;
    }
}
