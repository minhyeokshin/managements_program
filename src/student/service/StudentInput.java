package student.service;

import student.dto.StudentDto;

/**
 * 학생정보를 테이블에 입력하기 위한 인터페이스
 */
public interface StudentInput {
    /**
     * 학생정보를 테이블에 입력하는 메서드
     * @param studentDto 학생정보 DTO
     */
    void putStudentTable(StudentDto studentDto);
}