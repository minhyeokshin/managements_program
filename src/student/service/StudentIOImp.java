package student.service;

import student.dto.StudentDto;
import student.repository.StudentDBIO;
import student.repository.StudentManager;

import java.util.Map;

/**
 * 저장소, 캐시데이터 입출력
 */
public class StudentIOImp implements StudentIO{
    StudentManager studentManager;
    StudentDBIO studentDBIO;

    public StudentIOImp(StudentManager studentManager, StudentDBIO studentDBIO) {
        this.studentManager = studentManager;
        this.studentDBIO = studentDBIO;
    }

    /**
     * 학생 데이터 캐시 데이터에서 가져오는 메소드
     * @return Student map
     */
    @Override
    public Map<String, StudentDto> getStudentTable() {
        return studentManager.getStudentTable();
    }

    /**
     * dbio 와 캐시데이터 업데이트
     * @param studentTable 학생 객체 테이블 키값 : SNO
     */
    @Override
    public void setStudentTable(Map<String, StudentDto> studentTable) {
        studentManager.setStudentTable(studentTable);
        studentDBIO.fileInput(studentTable);
    }

    /**
     * 캐시데이터 초기화
     */
    @Override
    public void initialize() {
        studentManager.setStudentTable(studentDBIO.fileOutput());
    }
}
