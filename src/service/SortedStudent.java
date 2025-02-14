package service;

import dto.StudentDto;

import java.util.List;
import java.util.Map;

public interface SortedStudent {

    void printSortedByTotal();   // 총점 점수 기준 정렬
    void printSortedByAverage(); //평균 점수 기준 정렬
    void printSortedByName(); // 학생 이름 기준 정렬
    void printSortedBySnoNumber(); // 학번 기준 정렬
    void printSortedByGrade(); // 학점 기준 정렬
    Map<String, StudentDto> printVerify() throws Exception;

}
