package com.demo.service;

import com.demo.domain.DTO.*;
import com.demo.common.ResultBean;

public interface IStudentService {

    ResultBean addStudent(AddStudentDTO addStudentDTO);

    ResultBean listStudent(ListStudentDTO studentDTO);


    ResultBean addStudentClass(AddStudentClassDTO addStudentClassDTO);


    ResultBean updateStudentClass(UpdateStudentClassDTO updateStudentClassDTO);


    ResultBean listStudentClass(ListStudentClassDTO studentClassWithSocreDTO);
}
