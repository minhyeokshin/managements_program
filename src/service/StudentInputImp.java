package service;


import dto.StudentDto;

import java.util.Map;
import java.util.Scanner;

public class StudentInputImp implements StudentInput {
    StudentIO studentIO;

    public StudentInputImp(StudentIO studentIO) {
        this.studentIO = studentIO;
    }

    //studentNumber,name,korean,english,math,science,total,average,grade
    //output에서 입력받은 객체 map에 삽입 (StudentNumber 생성)
    public void inputStudent(String StudentNumber, String name, int korean, int english, int math, int science, int total, int average, String grade) {
        StudentDto studentDto = new StudentDto(StudentNumber, name, korean, english, math, science, total, average, grade);
        studentIO.getStudentTable().put(StudentNumber,studentDto);
    }
    //중복값을 입력받았을 때, 오류 만들어서 output으로 throw
}
