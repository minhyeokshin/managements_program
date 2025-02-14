package service;

import dto.StudentDto;

public interface StudentInput {
    String initStudentNumberCounter();
    int calcTotal(StudentDto studentDto);
    double calcAverage(StudentDto studentDto);
    String calcGrade(double average);
    void putStudentTable(StudentDto studentDto);
    StudentDto createPerfectDto(StudentDto studentDto, String studentNumber);
}