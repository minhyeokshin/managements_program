package service;

import dto.StudentDto;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class SearchStudentImp implements SearchStudent {
    StudentIO studentIO;

    public SearchStudentImp(StudentIO studentIO) {
        this.studentIO = studentIO;
    }


    @Override
    public StudentDto searchBySno(String studentNumber) {
        return studentIO.getStudentTable().values().stream()
                .filter(student ->
                        student.getStudentNumber() != null &&
                                student.getStudentNumber().equalsIgnoreCase(studentNumber))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Map<String, StudentDto> searchAll() {
        if (studentIO.getStudentTable() != null)
            return studentIO.getStudentTable(); // 🔹 studentTable이 존재하면 그대로 반환
        else
            return Collections.emptyMap();
    }

    @Override
    public List<StudentDto> searchByGrade(String grade) {
        return List.of();
    }

    @Override
    public List<StudentDto> searchMaxLogic(Function<StudentDto, Integer> function) {
        return null;
    }

    @Override
    public List<StudentDto> searchMinLogic(Function<StudentDto, Integer> function) {
        return null;
    }

    @Override
    public Map<String, StudentDto> MinTotalMap() {
        return Map.of();
    }

    @Override
    public Map<String, StudentDto> MaxTotalMap() {
        return Map.of();
    }

    @Override
    public StudentDto searchRangeLogic(Function<StudentDto, Integer> function) {
        return null;
    }

    @Override
    public Map<String, StudentDto> SearchRange() {
        return Map.of();
    }

    @Override
    public List<StudentDto> searchByReTest() {
        return List.of();
    }
}
