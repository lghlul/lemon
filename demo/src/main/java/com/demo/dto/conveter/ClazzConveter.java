package com.demo.dto.conveter;

import com.demo.dto.ClazzDTO;
import com.demo.model.Clazz;
import org.springframework.cglib.beans.BeanCopier;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * @Author: nhsoft.ll
 * @Description: Clazz数据转换
 * @Date:Create：2019/3/22 16:02
 * @Modified By：
 */
public class ClazzConveter {

    private static BeanCopier copierdto = BeanCopier.create(Clazz.class, ClazzDTO.class, false);
    private static BeanCopier copiermodel = BeanCopier.create(ClazzDTO.class, Clazz.class, false);

    public static ClazzDTO createDTO(Clazz model) {
        if (model == null) {
            return null;
        }
        ClazzDTO dto = new ClazzDTO();
        copierdto.copy(model, dto, null);
        return dto;
    }

    public static Clazz createModel(ClazzDTO dto) {
        if (dto == null) {
            return null;
        }
        Clazz model = new Clazz();
        copiermodel.copy(dto, model, null);

        return model;
    }

    public static Collection<Clazz> createModels(Collection<ClazzDTO> dtos) {
        if (dtos == null) {
            return null;
        }
        List<Clazz> list = new ArrayList<Clazz>();
        Iterator<ClazzDTO> iterator = dtos.iterator();
        while (iterator.hasNext()) {
            ClazzDTO dto = iterator.next();
            Clazz model = createModel(dto);
            list.add(model);
        }
        return list;
    }

    public static Collection<ClazzDTO> createDTOs(Collection<Clazz> models) {
        if (models == null) {
            return null;
        }
        List<ClazzDTO> list = new ArrayList<ClazzDTO>();
        Iterator<Clazz> iterator = models.iterator();
        while (iterator.hasNext()) {
            Clazz model = iterator.next();
            ClazzDTO dto = createDTO(model);
            list.add(dto);
        }
        return list;
    }

}
