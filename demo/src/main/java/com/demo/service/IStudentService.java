package com.demo.service;

import com.demo.dto.*;
import com.demo.common.ResultBean;
import com.demo.model.Student;

public interface IStudentService {

    ResultBean save(AddStudentDTO addStudentDTO) throws Exception;

    ResultBean list(ListStudentDTO studentDTO) throws Exception;

    ResultBean addStudentClass(AddStudentClassDTO addStudentClassDTO) throws Exception;

    ResultBean updateStudentClass(UpdateStudentClassDTO updateStudentClassDTO) throws Exception;

    ResultBean listStudentClass(ListStudentClassDTO studentClassWithScoreDTO) throws Exception;

    ResultBean delete(String studentNumber) throws Exception;

    ResultBean update(UpdateStudentDTO updateStudentDTO) throws Exception;

    Student get(String studentNumber);

    boolean checkStudentExist(String studentNumber);

    boolean checkStudentClassExist(String studentNumber , int teacherClassId);

}
