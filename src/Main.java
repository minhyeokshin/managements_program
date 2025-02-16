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
        StudentOutput test = diConfig.getStudentOutput();
        test.initialize();

        while(true) {
            test.welcome();
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