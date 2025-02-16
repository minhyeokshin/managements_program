package service;

import dto.StudentDto;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

public interface SearchStudent {
    StudentDto search(String sno);

    /**
     * studentNumber 기준검색
     */
    StudentDto searchBySno(String studentNumber);

    /**
     * 전체 학생 검색
     */
    Map<String, StudentDto> searchAll();

    /**
     * 특정 등급 학생 검색
     */
    List<StudentDto> searchByGrade(String grade);

    /**
     * 과목별 최저,최고 점수 학생 검색 로직
     */
    List<StudentDto> searchMaxLogic(Function<StudentDto,Integer> function);
    List<StudentDto> searchMinLogic(Function<StudentDto,Integer> function);

    /**
     * 과목별 최저,최고 점수 학생 검색 기능
     */
    List<StudentDto> MaxTotalMap(String subject);
    List<StudentDto> MinTotalMap(String subject);

    /**
     * 범위 검색 로직
     */
    List<StudentDto> searchRangeLogic(Function<StudentDto,Integer> function,double min , double max);

    /**
     * 범위 검색 기능
     */
    List<StudentDto> SearchRange(String subject,double min,double max);

    /**
     * 재시험 대상 검색
     * F등급 필터링
     */
    List<StudentDto> searchByReTest ();

    public void initialize();


}