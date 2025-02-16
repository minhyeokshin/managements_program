package service;


import dto.StudentDto;
import repository.StudentManager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * 학생정보를 테이블에 입력하기 위한 클래스
 */
public class StudentInputImp implements StudentInput {
    //studentNumber,name,korean,english,math,science,total,average,grade

    StudentIO studentIO;
    private static int studentNumberCounter = 0;

    public StudentInputImp(StudentIO studentIO) {
        this.studentIO = studentIO;
    }

    /**
     * 학번을 생성하는 메서드
     * 테이블에 저장된 가장 큰 학번을 찾아서 그 다음 학번을 생성
     * @return 생성한 학번 반환
     */
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

    /**
     * 완벽한 학생정보 DTO를 생성하기 위한 메서드
     * @param studentDto 학생정보 DTO
     * @param studentNumber 학번
     * @return builder를 통한 완벽한 학생정보 객체 반환
     */
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

    /**
     * 학생정보를 테이블에 입력하는 메서드
     * @param studentDto 학생정보 DTO
     */
    @Override
    public void putStudentTable(StudentDto studentDto) {
        String studentNumber = initStudentNumberCounter();
        studentIO.getStudentTable().put(studentNumber, createPerfectDto(studentDto, studentNumber));
        studentIO.setStudentTable(studentIO.getStudentTable());
    }

    /**
     * 총점을 계산하는 메서드
     * @param studentDto 학생정보 DTO
     * @return 계산한 총점을 반환
     */
    private int calcTotal(StudentDto studentDto) {
        return studentDto.getKorean() + studentDto.getEnglish() + studentDto.getMath() + studentDto.getScience();
    }

    /**
     * 평균을 계산하는 메서드
     * @param studentDto 학생정보 DTO
     * @return 계산한 평균값을 반환
     */
    private double calcAverage(StudentDto studentDto) {
        return calcTotal(studentDto) / 4.0;
    }

    /**
     * 평균점수를 바탕으로 학점을 정하는 메서드
     * @param average 평균 점수
     * @return 측정한 학점을 반환
     */
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