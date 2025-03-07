package student.controller;

/**
 * Controller
 */
public interface StudentOutput {
    /**
     * welcome 메뉴 출력문
     */
    void welcome();

    /**
     * 대분류 메뉴
     */
    void toTalMenu();

    /**
     * 2번 검색 메뉴(학생 정보 검색)
     */
    void searchMenu();

    /**
     * 3번 정렬 메뉴(학생 정보 정렬)
     */
    void sortMenu();

    /**
     * 4번 메뉴 (종료)
     */
    void studentExit();

    /**
     * 번호 입력
     * @return number
     */
    String numberInput();

    /**
     * 학생 정보 입력
     */
    void studentInfoInput();

    /**
     * 학생 정보 검색
     */
    void studentInfoSearch();

    /**
     * 학생 정보 정렬
     */
    void studentInfoSort();

    /**
     * StudentIO 초기화
     */
    void initialize();

    void start();
}
