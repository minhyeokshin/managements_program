package service;

import dto.StudentDto;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class SortedStudentImp implements SortedStudent {
    StudentIO studentIO;



    public SortedStudentImp(StudentIO studentIO) {
        this.studentIO = studentIO;
    }

    @Override // 총점 점수 기준 정렬
    public void printSortedByTotal() {
        Map<String, StudentDto> studentTable = null; // 캐싱 데이터 불러옴
        try {
            studentTable = printVerify();
            List<StudentDto> studentList = new ArrayList<>(studentTable.values()); // DTO들만  list로 담음

            studentList.sort(Comparator.comparingInt(StudentDto::getTotal).reversed());

            studentList.forEach(System.out::println);
        } catch (Exception e) {
            System.out.println("총점 기준 정렬 중 오류 발생: " + e.getMessage());
        }


    }

    @Override //평균 점수 기준 정렬
    public void printSortedByAverage() {
        Map<String, StudentDto> studentTable = studentIO.getStudentTable();
        List<StudentDto> studentList = new ArrayList<>(studentTable.values());

        studentList.sort(Comparator.comparingDouble(StudentDto::getAverage).reversed());

    }

    @Override // 학생 이름 기준 정렬
    public void printSortedByName() {
        Map<String, StudentDto> studentTable = studentIO.getStudentTable();
        List<StudentDto> studentList = new ArrayList<>(studentTable.values());

        studentList.sort(Comparator.comparing(StudentDto::getName));

        studentList.forEach(System.out::println);
    }

    @Override  // 학번 기준 정렬
    public void printSortedBySnoNumber() {
        Map<String, StudentDto> studentTable = studentIO.getStudentTable();
        List<StudentDto> studentList = new ArrayList<>(studentTable.values());

        studentList.sort(Comparator.comparing(StudentDto::getStudentNumber));

        studentList.forEach(System.out::println);
    }

    @Override
    public void printSortedByGrade() {
        Map<String, StudentDto> studentTable = studentIO.getStudentTable();
        List<StudentDto> studentList = new ArrayList<>(studentTable.values());
        studentList.sort(Comparator.comparing(StudentDto::getGrade).thenComparing(StudentDto::getName));

        studentList.forEach(System.out::println); //테스트 뿌리기
    }
    @Override
    public Map<String, StudentDto> printVerify() throws Exception {
        Map<String, StudentDto> studentTable = studentIO.getStudentTable();
        if (studentTable == null){
            throw new Exception("학생 데이터가 존재 하지 않습니다.");
        }
        if (studentTable.isEmpty()){
            throw new Exception("정렬할 학생 값이 없습니다.");
        }
        return studentTable;
    }
}
