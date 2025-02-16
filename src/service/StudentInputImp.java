package service;


import dto.StudentDto;
import repository.StudentManager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class StudentInputImp implements StudentInput {
    //studentNumber,name,korean,english,math,science,total,average,grade

    StudentIO studentIO;
    private static int studentNumberCounter = 0;

    public StudentInputImp(StudentIO studentIO) {
        this.studentIO = studentIO;
    }

    //학번(studentNumberCounter 생성)
    private String initStudentNumberCounter() {
        Map<String, StudentDto> studentDtoMap = studentIO.getStudentTable();
        if(studentNumberCounter == 0) {
            List<String> list = studentDtoMap.keySet().stream().toList();
            studentNumberCounter = list.stream()
                    .mapToInt(x -> Integer.parseInt(x))
                    .max()
                    .orElse(20250000);
        }
        studentNumberCounter++;
        return String.valueOf(studentNumberCounter);
    }

    //Builder
    private StudentDto createPerfectDto(StudentDto studentDto, String studentNumber){
        return StudentDto.builder()
                .studentNumber(studentNumber)
                .name(studentDto.getName())
                .korean(studentDto.getKorean())
                .english(studentDto.getEnglish())
                .math(studentDto.getMath())
                .science(studentDto.getScience())
                .total(calcTotal(studentDto))
                .average(calcAverage(studentDto))
                .grade(calcGrade(calcAverage(studentDto)))
                .build();
    }

    @Override
    // map(학번,DTO) put
    public void putStudentTable(StudentDto studentDto) {
        String studentNumber = initStudentNumberCounter();
        studentIO.getStudentTable().put(studentNumber, createPerfectDto(studentDto, studentNumber));
        studentIO.setStudentTable(studentIO.getStudentTable());
    }

    //Total 계산 (korean + english + math + science)
    private int calcTotal(StudentDto studentDto) {
        return studentDto.getKorean() + studentDto.getEnglish() + studentDto.getMath() + studentDto.getScience();
    }

    //Average 계산
    private double calcAverage(StudentDto studentDto) {
        return calcTotal(studentDto) / 4.0;
    }

    //Grade 계산
    private String calcGrade(double average) {
        switch ((int) (average / 10)) {
            case 10, 9: return "A";
            case 8: return "B";
            case 7: return "C";
            case 6: return "D";
            default: return "F";
        }
    }
}