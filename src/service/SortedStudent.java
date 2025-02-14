package service;

import dto.StudentDto;

import java.util.Map;

public interface SortedStudent {

    void SortedByTotal();   // 총점 점수 기준 정렬
    void SortedByAverage(); //평균 점수 기준 정렬
    void SortedByName(); // 학생 이름 기준 정렬
    void SortedBySnoNumber(); // 학번 기준 정렬
    void SortedByGrade(); // 학점 기준 정렬
    Map<String, StudentDto> Verify() throws Exception;

}