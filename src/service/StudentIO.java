package service;

import dto.StudentDto;

import java.util.Map;

/**
 * 저장소, 캐시데이터 입출력
 */
public interface StudentIO {

    /**
     * 학생 데이터 캐시 데이터에서 가져오는 메소드
     * @return Student map
     */
    Map<String, StudentDto> getStudentTable();

    /**
     * dbio 와 캐시데이터 업데이트
     * @param studentTable 학생 객체 테이블 키값 : SNO
     */
    void setStudentTable(Map<String, StudentDto> studentTable);

    /**
     * 캐시데이터 초기화
     */
    void initialize();
}
