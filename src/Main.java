import dto.StudentDto;
import service.SearchStudent;

import java.util.HashMap;
import java.util.Map;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        System.out.println("zz");

        DIConfig diConfig = new DIConfig();
        SearchStudent searchStudent = diConfig.getSearchStudent();

        Map<String,StudentDto> test = new HashMap<>();
        StudentDto studentDto = new StudentDto("123","123",123,123
        ,123,123,123,123.0,"32");
        test.put(studentDto.getStudentNumber(),studentDto);

        diConfig.getStudentIO().setStudentTable(test);
        System.out.println(searchStudent.search("123"));
    }
}