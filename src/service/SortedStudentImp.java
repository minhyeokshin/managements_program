package service;

import dto.StudentDto;

import java.util.*;

public class SortedStudentImp implements SortedStudent {
    StudentIO studentIO;



    public SortedStudentImp(StudentIO studentIO) {
        this.studentIO = studentIO;
    }
    /**
     * 총점 기준으로 학생을 정렬 (총점이 높은 순, 이름 오름차순)
     */
    @Override
    public void SortedByTotal() {
        Map<String, StudentDto> studentTable = null;
        try {
            studentTable = Verify();
            List<StudentDto> studentList = new ArrayList<>(studentTable.values()); // DTO들만  list로 담음

            studentList.sort(Comparator.comparingInt(StudentDto::getTotal).reversed().thenComparing(StudentDto::getName));
            Map<String, StudentDto> sortedStudentTable = new LinkedHashMap<>();
            for (StudentDto studentDto : studentList) {
                sortedStudentTable.put(studentDto.getStudentNumber(), studentDto);
            }
            studentIO.setStudentTable(sortedStudentTable);
        } catch (Exception e) {
            System.out.println("총점 기준 정렬 중 오류 발생: " + e.getMessage());
        }
    }
    /**
     * 평균 점수 기준으로 학생을 정렬 (평균이 높은 순, 학번 오름차순)
     */
    @Override
    public void SortedByAverage() {
        Map<String, StudentDto> studentTable = null;
        try {
            studentTable = Verify();
            List<StudentDto> studentList = new ArrayList<>(studentTable.values());
            studentList.sort(Comparator.comparingDouble(StudentDto::getAverage).reversed().thenComparing(StudentDto::getStudentNumber));
            Map<String, StudentDto> sortedStudentTable = new LinkedHashMap<>();
            for (StudentDto studentDto : studentList) {
                sortedStudentTable.put(studentDto.getStudentNumber(), studentDto);
            }
            studentIO.setStudentTable(sortedStudentTable);
        } catch (Exception e) {
            System.out.println("평점 기준 정렬 중 오류 발생: " + e.getMessage());
        }

    }
    /**
     * 학생 이름 기준으로 정렬 (이름 오름차순, 학번 오름차순)
     */
    @Override
    public void SortedByName() {
        Map<String, StudentDto> studentTable = null;
        try {
            studentTable = Verify();
            List<StudentDto> studentList = new ArrayList<>(studentTable.values());

            studentList.sort(Comparator.comparing(StudentDto::getName).thenComparing(StudentDto::getStudentNumber));
            Map<String, StudentDto> sortedStudentTable = new LinkedHashMap<>();
            for (StudentDto studentDto : studentList) {
                sortedStudentTable.put(studentDto.getStudentNumber(), studentDto);
            }
            studentIO.setStudentTable(sortedStudentTable);

        } catch (Exception e) {
            System.out.println("학생 이름 정렬 중 오류 발생: " + e.getMessage());
        }


    }
    /**
     * 학번 기준으로 정렬 (학번 오름차순, 이름 오름차순)
     */
    @Override
    public void SortedBySnoNumber()  {
        Map<String, StudentDto> studentTable = null;
        try {
            studentTable = Verify();
            List<StudentDto> studentList = new ArrayList<>(studentTable.values());
            studentList.sort(Comparator.comparing(StudentDto::getStudentNumber).thenComparing(StudentDto::getName));
            Map<String, StudentDto> sortedStudentTable = new LinkedHashMap<>();
            for (StudentDto studentDto : studentList) {
                sortedStudentTable.put(studentDto.getStudentNumber(), studentDto);
            }
            studentIO.setStudentTable(sortedStudentTable);
        } catch (Exception e) {
            System.out.println("학번 기준 정렬 중 오류 발생: " + e.getMessage());
        }
    }

    /**
     * 학점 기준으로 정렬 (학점 오름차순, 이름 오름차순)
     */
    @Override
    public void SortedByGrade() {
        Map<String, StudentDto> studentTable = null;
        try {
            studentTable = Verify();
            List<StudentDto> studentList = new ArrayList<>(studentTable.values());
            studentList.sort(Comparator.comparing(StudentDto::getGrade).thenComparing(StudentDto::getName));
            Map<String, StudentDto> sortedStudentTable = new LinkedHashMap<>();
            for (StudentDto studentDto : studentList) {
                sortedStudentTable.put(studentDto.getStudentNumber(), studentDto);
            }
            studentIO.setStudentTable(sortedStudentTable);
        } catch (Exception e) {
            System.out.println("학점 기준 정렬 중 오류 발생: " + e.getMessage());

        }

    }

    /**
     * 학생 정보가 존재하는지 확인
     * @return 학생 데이터 테이블
     * @throws Exception 학생 데이터가 없을 경우 예외 발생
     */
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