package controller;

import service.SearchStudent;
import service.SortedStudent;
import service.StudentInput;

public class StudentOutputImp implements StudentOutput {
    SearchStudent searchStudent;
    SortedStudent sortedStudent;
    StudentInput studentInput;

    public StudentOutputImp(SearchStudent searchStudent, SortedStudent sortedStudent, StudentInput studentInput) {
        this.searchStudent = searchStudent;
        this.sortedStudent = sortedStudent;
        this.studentInput = studentInput;

    }




}
