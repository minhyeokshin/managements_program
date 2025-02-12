import dto.StudentDto;
import repository.StudentDBIOUseFileIO;
import service.SearchStudent;

import java.util.HashMap;
import java.util.Map;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        System.out.println("zz");

        Map<String, StudentDto> testmap = new HashMap<>();
        testmap.put("123",new StudentDto("123", "123", 1, 1, 1, 1, 123, 123,"a"));
        testmap.put("133",new StudentDto("123", "123", 1, 1, 1, 1, 123, 123,"a"));
        testmap.put("163",new StudentDto("123", "123", 1, 1, 1, 1, 123, 123,"a"));

        StudentDBIOUseFileIO studentDBIOUseFileIO = new StudentDBIOUseFileIO();
        studentDBIOUseFileIO.fileInput(testmap);

        studentDBIOUseFileIO.fileOutput().entrySet().stream().forEach(System.out::println);
    }
}