package repository;

import dto.StudentDto;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

public class StudentManager {


    private Map<String, StudentDto> studentTable = new HashMap<>();

    private static StudentManager studentManagerSingleton = new StudentManager();
    static StudentDBIO studentDBIO;

    public static void setStudentDBIO(StudentDBIO studentDBIO) {
        StudentManager.studentDBIO = studentDBIO;
    }

    public static StudentManager getInstance(){ // 싱글톤 패턴 getInstance
        return studentManagerSingleton;
    }

    public Map<String, StudentDto> getStudentTable() {
        return studentTable;
    }



    //
    public void setStudentTable(Map<String, StudentDto> studentTable) {
        this.studentTable = studentTable;
        }
    }

