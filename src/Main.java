import controller.StudentOutputImp;
import dto.StudentDto;
import service.SearchStudent;

import java.util.HashMap;
import java.util.Map;
import service.SortedStudent;
import service.StudentInput;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        System.out.println("zz");

        DIConfig diConfig = new DIConfig();
        SearchStudent searchStudent = diConfig.getSearchStudent();

        Map<String,StudentDto> test = new HashMap<>();
        StudentDto studentDto = new StudentDto("201912780","김병곤",100,23
        ,43,12,13,12.0,"32");
        StudentDto studentDto1 = new StudentDto("23","이정섭",13,12
                ,13,13,13,13.0,"32");
        StudentDto studentDto2 = new StudentDto("53","신민혁",13,12
                ,13,13,23,13.0,"32");
        StudentDto studentDto3 = new StudentDto("623","서민성",13,123
                ,12,13,23,13.0,"32");
        test.put(studentDto.getStudentNumber(),studentDto);
        test.put(studentDto1.getStudentNumber(),studentDto1);
        test.put(studentDto2.getStudentNumber(),studentDto2);
        test.put(studentDto3.getStudentNumber(),studentDto3);

        diConfig.getStudentIO().setStudentTable(test);
        //System.out.println(searchStudent.search("123"));

        SortedStudent sortedStudent = diConfig.getSortStudent();
        StudentInput studentInput = diConfig.getStudentInput();

        StudentOutputImp test1 = new StudentOutputImp(searchStudent, sortedStudent, studentInput);
        while(true) {
            test1.welcome();
            test1.toTalMenu();
            String number = test1.numberInput();
            switch (number) {
                case "1":
                    test1.studentInfoInput();
                    break;
                case "2":
                    test1.studentInfoSearch();
                    break;
                case "3":
                    test1.studentInfoSort();
                    break;
                case "4":
                    test1.studentExit();
                    break;
            }
        }

    }
}