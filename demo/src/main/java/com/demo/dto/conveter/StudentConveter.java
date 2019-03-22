package com.demo.dto.conveter;

import com.demo.dto.StudentDTO;
import com.demo.model.Student;
import org.springframework.cglib.beans.BeanCopier;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * @Author: nhsoft.ll
 * @Description: Student对象转换
 * @Date:Create：2019/3/22 16:05
 * @Modified By：
 */
public class StudentConveter {
    private static BeanCopier copierdto = BeanCopier.create(Student.class, StudentDTO.class, false);
    private static BeanCopier copiermodel = BeanCopier.create(StudentDTO.class, Student.class, false);

    public static StudentDTO createDTO(Student model) {
        if (model == null) {
            return null;
        }
        StudentDTO dto = new StudentDTO();
        copierdto.copy(model, dto, null);
        return dto;
    }

    public static Student createModel(StudentDTO dto) {
        if (dto == null) {
            return null;
        }
        Student model = new Student();
        copiermodel.copy(dto, model, null);

        return model;
    }

    public static Collection<Student> createModels(Collection<StudentDTO> dtos) {
        if (dtos == null) {
            return null;
        }
        List<Student> list = new ArrayList<>();
        Iterator<StudentDTO> iterator = dtos.iterator();
        while (iterator.hasNext()) {
            StudentDTO dto = iterator.next();
            Student model = createModel(dto);
            list.add(model);
        }
        return list;
    }

    public static Collection<StudentDTO> createDTOs(Collection<Student> models) {
        if (models == null) {
            return null;
        }
        List<StudentDTO> list = new ArrayList<>();
        Iterator<Student> iterator = models.iterator();
        while (iterator.hasNext()) {
            Student model = iterator.next();
            StudentDTO dto = createDTO(model);
            list.add(dto);
        }
        return list;
    }
}
