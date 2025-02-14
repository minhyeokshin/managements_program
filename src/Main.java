import controller.StudentOutput;
import controller.StudentOutputImp;
import dto.StudentDto;
import repository.StudentDBIO;
import repository.StudentDBIOUseFileIO;
import service.SearchStudent;
import service.SortedStudent;
import service.StudentIO;

import java.util.HashMap;
import java.util.Map;
import service.StudentInput;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {


        DIConfig diConfig = new DIConfig();
//        StudentIO studentIO = diConfig.getStudentIO();
//        Map<String,StudentDto> testData = new HashMap<>();


//        testData.put("20231001", new StudentDto("20231001", "김병곤", 90, 80, 85, 95, 350, 87.5, "A"));
//        testData.put("20231002", new StudentDto("20231002", "최문규", 90, 75, 80, 85, 310, 77.5, "B"));
//        testData.put("20231003", new StudentDto("20231003", "이정섭", 85, 90, 95, 100, 370, 92.5, "A+"));
//        testData.put("20231004", new StudentDto("20231004", "이동휘", 60, 65, 70, 75, 270, 67.5, "F"));
//        testData.put("20231005", new StudentDto("20231005", "고은아", 50, 55, 60, 65, 230, 57.5, "F"));


//        studentIO.setStudentTable(testData);
//
//        diConfig.getStudentIO().setStudentTable(testData);
        //System.out.println(searchStudent.search("123"));

        StudentOutput test = diConfig.getStudentOutput();
        test.initialize();

        StudentDBIO studentDBIO = new StudentDBIOUseFileIO();
        studentDBIO.fileOutput().entrySet().forEach(x -> System.out.println(x.getValue()));


        while(true) {
            test.welcome();
            test.toTalMenu();
            String number = test.numberInput();
            switch (number) {
                case "1":
                    test.studentInfoInput();
                    break;
                case "2":
                    test.studentInfoSearch();
                    break;
                case "3":
                    test.studentInfoSort();
                    break;
                case "4":
                    test.studentExit();
                    break;
            }
            if(number.equals("4")) break;
        }

    }
}