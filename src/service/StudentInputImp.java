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
    @Override
    public String initStudentNumberCounter() {
        Map<String, StudentDto> studentDtoMap = studentIO.getStudentTable();
        if(studentNumberCounter == 0) {
            List<String> list = studentDtoMap.keySet().stream().toList();
            studentNumberCounter = list.stream().mapToInt(x -> Integer.parseInt(x)).max().orElse(20250000);
        }
        studentNumberCounter++;
        return String.valueOf(studentNumberCounter);
    }

//    //중복정보 검사 (학생이름 + 국어 + 영어 + 수학 + 과학)
//    public boolean isDuplicateStudent (StudentDto studentDto) {
//        Map<String, StudentDto> studentDtoMap = studentIO.getStudentTable();
//        for (StudentDto check : studentDtoMap.values()) {
//            if (check.getName().equals(studentDto.getName()) &&
//                check.getKorean() == studentDto.getKorean() &&
//                check.getEnglish() == studentDto.getEnglish() &&
//                check.getMath() == studentDto.getMath() &&
//                check.getScience() == studentDto.getScience()) {
//                return true;
//            }
//        }
//        return false;
//    }

    //Builder
    public StudentDto createPerfectDto(StudentDto studentDto, String studentNumber){
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

    // map(학번,DTO) put
    @Override
    public void putStudentTable(StudentDto studentDto) {
        String studentNumber = initStudentNumberCounter();
        studentIO.getStudentTable().put(studentNumber, createPerfectDto(studentDto, studentNumber));
    }

    //Total 계산 (korean + english + math + science)
    @Override
    public int calcTotal(StudentDto studentDto) {
        return studentDto.getKorean() + studentDto.getEnglish() + studentDto.getMath() + studentDto.getScience();
    }

    //Average 계산
    @Override
    public double calcAverage(StudentDto studentDto) {
        return calcTotal(studentDto) / 4.0;
    }

    //Grade 계산
    @Override
    public String calcGrade(double average) {
        switch ((int) (average / 10)) {
            case 10, 9: return "A";
            case 8: return "B";
            case 7: return "C";
            case 6: return "D";
            default: return "F";
        }
    }
}