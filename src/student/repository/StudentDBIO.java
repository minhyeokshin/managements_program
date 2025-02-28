package student.repository;

import student.dto.StudentDto;

import java.util.Map;

/**
 * db 나 파일같은 저장소에관한 입출력을 담당하는 인터페이스
 */
public interface StudentDBIO {
    /**
     * 데이터를 저장하는 메소드
     * @param studentTable 기본키와 학생 DTO 를 밸류값으로 가지는 map 자료구조
     */
    void fileInput(Map<String, StudentDto> studentTable);

    /**
     * 저장소의 데이터를 map으로 꺼내오는 메소드
     * @return 기본키와 학생 DTO 를 밸류값으로 가지는 map 자료구조
     */
    Map<String, StudentDto> fileOutput();
}