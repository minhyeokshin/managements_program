package student.repository;

import student.dto.StudentDto;
import java.util.HashMap;
import java.util.Map;

/**
 * 캐시 데이터 클래스 (싱글톤패턴 적용)
 */
public class StudentManager {


    private Map<String, StudentDto> studentTable = new HashMap<>();

    private static StudentManager studentManagerSingleton = new StudentManager();

    /**
     * 싱글톤으로 캐시데이터를 생성하는 메소드
     * @return 싱글톤 매니저 객체 반환
     */
    public static StudentManager getInstance(){ // 싱글톤 패턴 getInstance
        return studentManagerSingleton;
    }

    /**
     * 캐시 테이블 얻어오는 메소드
     * @return 학생 정보를 담은 Map
     */
    public Map<String, StudentDto> getStudentTable() {
        return studentTable;
    }


    /**
     * 캐시 테이즐을 수정하는 메소드
     * @param studentTable 학생 정보를 담은 map
     */
    public void setStudentTable(Map<String, StudentDto> studentTable) {
        this.studentTable = studentTable;
    }
}