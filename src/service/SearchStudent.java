package service;

import dto.StudentDto;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public interface SearchStudent {
    //studentNumber 기준검색 O
    StudentDto searchBySno(String studentNumber);

    //전체 학생 검색 O
    Map<String, StudentDto> searchAll();

    //특정 등급 학생 검색
    List<StudentDto> searchByGrade(String grade);

    //과목별 최저,최고 점수 학생 검색 로직

    StudentDto searchMaxLogic(Function<StudentDto,Integer> function);
    StudentDto searchMinLogic(Function<StudentDto,Integer> function);

    //과목별 최저,최고 점수 학생 검색 기능
    Map<String, StudentDto> MinTotalMap();
    Map<String, StudentDto> MaxTotalMap();

    //범위 검색 로직
    StudentDto searchRangeLogic(Function<StudentDto,Integer> function);
    //범위 검색 기능
    Map<String, StudentDto> SearchRange();

    //재시험 대상 필터링(F등급 필터링(60점이하))
    List<StudentDto> searchByReTest ();
}
