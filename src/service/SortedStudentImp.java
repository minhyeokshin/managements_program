package service;

import dto.StudentDto;

import java.util.*;

public class SortedStudentImp implements SortedStudent {
    StudentIO studentIO;



    public SortedStudentImp(StudentIO studentIO) {
        this.studentIO = studentIO;
    }

    @Override // 총점 점수 기준 정렬 후 이름으로 다시 정렬
    public void SortedByTotal() {
        Map<String, StudentDto> studentTable = null;
        try {
            studentTable = Verify();
            List<StudentDto> studentList = new ArrayList<>(studentTable.values()); // DTO들만  list로 담음

            studentList.sort(Comparator.comparingInt(StudentDto::getTotal).reversed().thenComparing(StudentDto::getName));
            Map<String, StudentDto> sortedStudentTable = new HashMap<>();
            for (StudentDto studentDto : studentList) {
                sortedStudentTable.put(studentDto.getName(), studentDto);
            }
            studentIO.setStudentTable(sortedStudentTable);
            System.out.println("asdasd");
        } catch (Exception e) {
            System.out.println("총점 기준 정렬 중 오류 발생: " + e.getMessage());
        }


    }

    @Override //평균 점수 기준 정렬후 학번으로 다시 정렬
    public void SortedByAverage() {
        Map<String, StudentDto> studentTable = null;
        try {
            studentTable = Verify();
            List<StudentDto> studentList = new ArrayList<>(studentTable.values());
            studentList.sort(Comparator.comparingDouble(StudentDto::getAverage).reversed().thenComparing(StudentDto::getStudentNumber));
            Map<String, StudentDto> sortedStudentTable = new HashMap<>();
            for (StudentDto studentDto : studentList) {
                sortedStudentTable.put(studentDto.getName(), studentDto);
            }
            studentIO.setStudentTable(sortedStudentTable);
        } catch (Exception e) {
            System.out.println("평점 기준 정렬 중 오류 발생: " + e.getMessage());
        }

    }

    @Override // 학생 이름 기준 정렬 후 학번으로 정렬
    public void SortedByName() {
        Map<String, StudentDto> studentTable = null;
        try {
            studentTable = Verify();
            List<StudentDto> studentList = new ArrayList<>(studentTable.values());

            studentList.sort(Comparator.comparing(StudentDto::getName).thenComparing(StudentDto::getStudentNumber));
            Map<String, StudentDto> sortedStudentTable = new HashMap<>();
            for (StudentDto studentDto : studentList) {
                sortedStudentTable.put(studentDto.getName(), studentDto);
            }
            studentIO.setStudentTable(sortedStudentTable);

        } catch (Exception e) {
            System.out.println("학생 이름 정렬 중 오류 발생: " + e.getMessage());
        }


    }

    @Override  // 학번 기준 정렬 후 이름 기준 다시 정렬
    public void SortedBySnoNumber()  {
        Map<String, StudentDto> studentTable = null;
        try {
            studentTable = Verify();
            List<StudentDto> studentList = new ArrayList<>(studentTable.values());
            studentList.sort(Comparator.comparing(StudentDto::getStudentNumber).thenComparing(StudentDto::getName));
            Map<String, StudentDto> sortedStudentTable = new HashMap<>();
            for (StudentDto studentDto : studentList) {
                sortedStudentTable.put(studentDto.getName(), studentDto);
            }
            studentIO.setStudentTable(sortedStudentTable);
        } catch (Exception e) {
            System.out.println("학번 기준 정렬 중 오류 발생: " + e.getMessage());
        }
    }

    @Override // 학점 기준 정렬 후 이름 기준 다시 정렬
    public void SortedByGrade() {
        Map<String, StudentDto> studentTable = null;
        try {
            studentTable = Verify();
            List<StudentDto> studentList = new ArrayList<>(studentTable.values());
            studentList.sort(Comparator.comparing(StudentDto::getGrade).thenComparing(StudentDto::getName));
            Map<String, StudentDto> sortedStudentTable = new HashMap<>();
            for (StudentDto studentDto : studentList) {
                sortedStudentTable.put(studentDto.getName(), studentDto);
            }
            studentIO.setStudentTable(sortedStudentTable);
        } catch (Exception e) {
            System.out.println("학점 기준 정렬 중 오류 발생: " + e.getMessage());

        }

    }


    @Override
    public Map<String, StudentDto> Verify() throws Exception {
        Map<String, StudentDto> studentTable = studentIO.getStudentTable(); // 캐싱 데이터 불러옴
        if (studentTable == null){
            throw new Exception("학생 데이터가 존재 하지 않습니다.");
        }
        if (studentTable.isEmpty()){
            throw new Exception("정렬할 학생 값이 없습니다.");
        }
        return studentTable;
    }
}