package controller;

import dto.StudentDto;
import java.util.List;
import java.util.Map;

public interface StudentOutput {
    void welcome();

    void toTalMenu();

    void searchMenu();

    void sortMenu();

    /*
            menu에서 1-4번 중 번호 선택
         */
    String numberInput();

    /*
            학생 정보 입력 -> StudentDto 객체
         */
    void studentInfoInput();


    void studentInfoSearch();



    void studentInfoSort();

    void studentExit();

    void initialize();
}
