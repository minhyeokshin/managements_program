import dto.StudentDto;
import repository.StudentDBIOUseFileIO;
import service.SearchStudent;
import service.SortedStudent;
import service.StudentIO;

import java.util.HashMap;
import java.util.Map;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        DIConfig diConfig = new DIConfig();
        SortedStudent sortedStudent = diConfig.getSortStudent();
        StudentIO studentIO = diConfig.getStudentIO();
        Map<String,StudentDto> testData = new HashMap<>();

        testData.put("20231001", new StudentDto("20231001", "김병곤", 90, 80, 85, 95, 350, 87.5, "A"));
        testData.put("20231002", new StudentDto("20231002", "최문규", 70, 75, 80, 85, 310, 77.5, "B"));
        testData.put("20231003", new StudentDto("20231003", "이정섭", 85, 90, 95, 100, 370, 92.5, "A+"));
        testData.put("20231004", new StudentDto("20231004", "이동휘", 60, 65, 70, 75, 270, 67.5, "C"));
        testData.put("20231005", new StudentDto("20231005", "고은아", 50, 55, 60, 65, 230, 57.5, "D"));

        studentIO.setStudentTable(testData);

        System.out.println("학번 기준 오름 차순");
        sortedStudent.printSortedBySnoNumber(); // 학번 기준 오름 차순
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++");

        System.out.println("평균 점수 기준 내림 차순");
        sortedStudent.printSortedByAverage();   // 평균 점수 기준 내림 차순
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++");

        System.out.println("학생 이름 기준 오름 차순");
        sortedStudent.printSortedByName();      // 학생 이름 기준 오름 차순
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++");

        System.out.println("총점 기준 오름 차순");
        sortedStudent.printSortedByTotal();     // 총점 기준 오름 차순
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++");

    }
}