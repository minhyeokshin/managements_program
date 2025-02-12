package repository;

import dto.StudentDto;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

public class StudentManager {

    @Getter
    @Setter
    private Map<String, StudentDto> studentTable = new HashMap<>();

    private static StudentManager studentManagerSingleton = new StudentManager();

    private StudentManager(){};

    public static StudentManager getInstance(){ // 싱글톤 패턴 getInstance
        return studentManagerSingleton;
    }
}
