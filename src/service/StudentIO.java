package service;

import dto.StudentDto;

import java.util.Map;

public interface StudentIO {

    Map<String, StudentDto> getStudentTable();

    void setStudentTable(Map<String, StudentDto> studentTable);
}
