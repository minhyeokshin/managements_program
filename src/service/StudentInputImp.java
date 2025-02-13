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
    public String initStudentNumberCounter() {
        Map<String, StudentDto> studentDtoMap = studentIO.getStudentTable();
        if (studentNumberCounter == 0) {
            List<String> list = studentDtoMap.keySet().stream().toList();
            studentNumberCounter = list.stream().mapToInt(x -> Integer.parseInt(x)).max().orElse(20250000);
        }
        return String.valueOf(++studentNumberCounter);
    }
// createDto(StudentDto){
/*sto.수학
    영어
        과학
    ㄱㄷ셔구 builder .d.d.d.d.d.
}*/
    //중복정보 검사 (학생이름 + 국어 + 영어 + 수학 + 과학)
    public boolean isDuplicateStudent(StudentDto studentDto) {
        Map<String, StudentDto> studentDtoMap = StudentManager.getInstance().getStudentTable();
        for (StudentDto check : studentDtoMap.values()) {
            if (check.getName().equals(studentDto.getName()) &&
                    check.getKorean() == studentDto.getKorean() &&
                    check.getEnglish() == studentDto.getEnglish() &&
                    check.getMath() == studentDto.getMath() &&
                    check.getScience() == studentDto.getScience()) {
                return true;
            }
        }
        return false;
    }

    //Total 계산 (korean + english + math + science)
    public int calcTotal(StudentDto studentDto) {
        return studentDto.getKorean() + studentDto.getEnglish() + studentDto.getMath() + studentDto.getScience();
    }

    //Average 계산
    public double calcAverage(StudentDto studentDto) {
        return calcTotal(studentDto) / 4.0;
    }

    //Grade 계산
    public String calcGrade(double average) {
        switch ((int) (average / 10)) {
            case 10:
            case 9:
                return "A";
            case 8:
                return "B";
            case 7:
                return "C";
            case 6:
                return "D";
            default:
                return "F";
        }
    }
}