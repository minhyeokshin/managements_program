package repository;

import dto.StudentDto;

import java.util.Map;

public interface StudentDBIO {
    void fileInput(Map<String, StudentDto> studentTable);
    Map<String, StudentDto> fileOutput();
}

