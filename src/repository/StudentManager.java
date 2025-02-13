package repository;

import dto.StudentDto;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class StudentManager {

    private StudentManager(){};

    private Map<String, StudentDto> studentTable = new HashMap<>(); // 캐싱데이터

    private static StudentManager studentManagerSingleton = new StudentManager();

    public static StudentManager getInstance(){ // 싱글톤 패턴 getInstance
        return studentManagerSingleton;
    }

    public Map<String, StudentDto> getStudentTable() {
        return studentTable;
    }

    public void setStudentTable(Map<String, StudentDto> studentTable) {
        this.studentTable = studentTable;
    }
}
