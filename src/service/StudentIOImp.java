package service;

import dto.StudentDto;
import repository.StudentDBIO;
import repository.StudentManager;

import java.util.Map;

public class StudentIOImp implements StudentIO{
    StudentManager studentManager;
    StudentDBIO studentDBIO;

    public StudentIOImp(StudentManager studentManager, StudentDBIO studentDBIO) {
        this.studentManager = studentManager;
        this.studentDBIO = studentDBIO;
    }


    @Override
    public Map<String, StudentDto> getStudentTable() {
        return studentManager.getStudentTable();
    }
    @Override
    public void setStudentTable(Map<String, StudentDto> studentTable) {
        studentManager.setStudentTable(studentTable);
        studentDBIO.fileInput(studentTable);
    }

    @Override
    public void updateStudentTable(StudentDto studentDto) {
        studentManager.getStudentTable().put(studentDto.getStudentNumber(), studentDto);
    }

    @Override
    public void initialize() {
        studentManager.setStudentTable(studentDBIO.fileOutput());
    }
}
