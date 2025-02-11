package service;

import dto.StudentDto;

public class SearchStudentImp implements SearchStudent {
    StudentIO studentIO;

    public SearchStudentImp(StudentIO studentIO) {
        this.studentIO = studentIO;
    }

    @Override
    public StudentDto search(String sno) {
        return studentIO.getStudentTable().get(sno);
    }


}
