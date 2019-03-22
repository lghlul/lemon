package com.demo.dto.conveter;

import com.demo.dto.StudentClazzDTO;
import com.demo.model.StudentClazz;
import org.springframework.cglib.beans.BeanCopier;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * @Author: nhsoft.ll
 * @Description: StudentClazz对象转换
 * @Date:Create：2019/3/22 16:11
 * @Modified By：
 */
public class StudentClazzConveter {
    private static BeanCopier copierdto = BeanCopier.create(StudentClazz.class, StudentClazzDTO.class, false);
    private static BeanCopier copiermodel = BeanCopier.create(StudentClazzDTO.class, StudentClazz.class, false);

    public static StudentClazzDTO createDTO(StudentClazz model) {
        if (model == null) {
            return null;
        }
        StudentClazzDTO dto = new StudentClazzDTO();
        copierdto.copy(model, dto, null);
        return dto;
    }

    public static StudentClazz createModel(StudentClazzDTO dto) {
        if (dto == null) {
            return null;
        }
        StudentClazz model = new StudentClazz();
        copiermodel.copy(dto, model, null);

        return model;
    }

    public static Collection<StudentClazz> createModels(Collection<StudentClazzDTO> dtos) {
        if (dtos == null) {
            return null;
        }
        List<StudentClazz> list = new ArrayList<>();
        Iterator<StudentClazzDTO> iterator = dtos.iterator();
        while (iterator.hasNext()) {
            StudentClazzDTO dto = iterator.next();
            StudentClazz model = createModel(dto);
            list.add(model);
        }
        return list;
    }

    public static Collection<StudentClazzDTO> createDTOs(Collection<StudentClazz> models) {
        if (models == null) {
            return null;
        }
        List<StudentClazzDTO> list = new ArrayList<>();
        Iterator<StudentClazz> iterator = models.iterator();
        while (iterator.hasNext()) {
            StudentClazz model = iterator.next();
            StudentClazzDTO dto = createDTO(model);
            list.add(dto);
        }
        return list;
    }
}
