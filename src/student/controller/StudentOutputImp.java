package student.controller;

import employee.DIConfig;
import student.dto.StudentDto;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import student.service.SearchStudent;
import student.service.SortedStudent;
import student.service.StudentInput;

/**
 * Controller
 */
public class StudentOutputImp implements StudentOutput {
    private static final String subjectRegex = "^(국어|수학|영어|과학)$";
    private static final String studentNameRegex = "^[가-힣]{2,4}$";
    private static final String studentScoreRegex = "(^[0-9]$)|(^[1-9][0-9]$)|(^[1][0][0]$)";
    private static final String gradeRegex = "[A-F]";
    private static final String menuNumberRegex = "[1-4]";

    static Scanner input = new Scanner(System.in);
    SearchStudent searchStudent;
    SortedStudent sortedStudent;
    StudentInput studentInput;

    public StudentOutputImp(SearchStudent searchStudent, SortedStudent sortedStudent, StudentInput studentInput) {
        this.searchStudent = searchStudent;
        this.sortedStudent = sortedStudent;
        this.studentInput = studentInput;
    }

    /**
     * welcome 메뉴 출력문
     */
    @Override
    public void welcome() {
        System.out.println("=".repeat(35));
        System.out.println("\tWelcome to the Student System");
        System.out.println("=".repeat(35));
        toTalMenu();
    }

    /**
     * 대분류 메뉴
     */
    @Override
    public void toTalMenu() {
        System.out.println("\t\t\t  Menu");
        System.out.println("=".repeat(35));
        System.out.println("1. 학생정보 입력\t 2. 학생정보 검색");
        System.out.println("3. 학생정보 정렬\t 4. 종료");
        System.out.println("1-4번 번호 중 1개를 입력하세요.");
    }

    /**
     * 2번 검색 메뉴(학생 정보 검색)
     */
    @Override
    public void searchMenu() {
        System.out.println("=".repeat(35));
        System.out.println("\t\t\t 2. Search");
        System.out.println("=".repeat(35));
        System.out.println("1. 전체 학생 검색\t 2. 학번 검색");
        System.out.println("3. 과목별 최고 점수 검색 4. 과목별 최저 점수 검색");
        System.out.println("5. 점수 범위 검색\t6. 등급 검색");
        System.out.println("7. 재시험 대상 학생 검색");
        System.out.println("1-7번 번호 중 1개를 입력하세요.");
    }

    /**
     * 3번 정렬 메뉴(학생 정보 정렬)
     */
    @Override
    public void sortMenu() {
        System.out.println("=".repeat(35));
        System.out.println("\t\t\t 3. Sort");
        System.out.println("=".repeat(35));
        System.out.println("1. 총점 정렬\t 2. 평균 정렬");
        System.out.println("3. 학생 이름 정렬\t 4. 학번 정렬");
        System.out.println("5. 학점 정렬");

    }


    /**
     * 번호 입력
     * @return number
     */
    @Override
    public String numberInput() {
        Scanner input = new Scanner(System.in);
        while (true) {
            String number = input.nextLine();
            if (number.matches(menuNumberRegex)) {
                return number;
            }
            System.out.println("[(1-4)번 번호를 입력해주세요.]");
        }
    }

    /**
     * 학생 정보 입력
     */
    @Override
    public void studentInfoInput() {
        String name = getUserInput("이름을 입력하세요", studentNameRegex);
        int korean = Integer.parseInt(getUserInput("국어 점수를 입력하세요", studentScoreRegex));
        int english = Integer.parseInt(getUserInput("영어 점수를 입력하세요", studentScoreRegex));
        int math = Integer.parseInt(getUserInput("수학 점수를 입력하세요", studentScoreRegex));
        int science = Integer.parseInt(getUserInput("과학 점수를 입력하세요", studentScoreRegex));

        StudentDto studentDto = StudentDto.builder()
                .name(name)
                .korean(korean)
                .english(english)
                .math(math)
                .science(science)
                .build();

        studentInput.putStudentTable(studentDto);
    }

    /**
     * 학생 정보 입력 반환
     * @param info
     * @param regex
     * @return 입력
     */
    String getUserInput(String info, String regex) {
        while (true) {
            System.out.print(info + ": ");
            String studentInfo = StudentOutputImp.input.next();
            if (studentInfo.matches(regex)) {
                return studentInfo;
            }
        }
    }

    /**
     * 학생 정보 검색
     */
    @Override
    public void studentInfoSearch() {
        Scanner input = new Scanner(System.in);
        System.out.println("학생 정보를 검색합니다.");
        searchMenu();
        String regex = "[1-7]";
        while (true) {
            String number = input.nextLine();
            if (number.matches(regex)) {
                switch (number) {
                    case "1": //전체 학생 정보 출력
                        System.out.println("전체 학생 정보를 출력합니다.");
                        totalStudentInfo();
                        break;

                    case "2": //학번 검색
                        System.out.println("학번을 입력해 주세요.");
                        while (true) {
                            String studentNumber = input.nextLine();
                            StudentDto dto = searchStudent.searchBySno(studentNumber);
                            if (dto == null) {
                                System.out.println("학번 다시 입력하세요!");
                            } else {
                                studentInfo();
                                System.out.printf("%-4s %-9s %-5s %-4s %-4s %-5s %-4s %-5s %-3s\n", dto.getName(),
                                        dto.getStudentNumber(), dto.getKorean(), dto.getEnglish(), dto.getMath(),
                                        dto.getScience(), dto.getTotal(), dto.getAverage(), dto.getGrade());
                                break;
                            }
                        }
                        break;
                    case "3": //최고점수
                        System.out.println("최고점수를 검색할 과목을 입력하세요.");
                        while (true) {
                            String subject = input.nextLine();
                            if (subject.matches(subjectRegex)) {
                                List<StudentDto> scoreList = searchStudent.MaxTotalMap(subject);
                                if (scoreList.isEmpty()) {
                                    System.out.println("학생 정보가 존재하지 않습니다.");
                                    System.out.println("핵생 정보를 먼저 입력해 주세요.");
                                } else {
                                    printStudentList(scoreList);
                                }
                                break;
                            } else {
                                System.out.println("올바른 과목명을 입력해주세요. [국어, 영어, 수학, 과학]");
                            }
                        }

                        break;
                    case "4": //최저점수
                        System.out.println("최저점수를 검색할 과목을 입력하세요.");
                        while (true) {
                            String subject = input.nextLine();
                            if (subject.matches(subjectRegex)) {
                                List<StudentDto> scoreList = searchStudent.MinTotalMap(subject);
                                if (scoreList.isEmpty()) {
                                    System.out.println("학생 정보가 존재하지 않습니다.");
                                    System.out.println("핵생 정보를 먼저 입력해 주세요.");
                                } else {
                                    printStudentList(scoreList);
                                }
                                break;
                            } else {
                                System.out.println("올바른 과목명을 입력해주세요. [국어, 영어, 수학, 과학]");
                            }
                        }

                        break;

                    case "5": // 점수 범위 검색
                        System.out.println("검색할 과목을 입력하세요.");
                        int scoreMin, scoreMax;

                        while (true) {
                            String subject = input.nextLine();
                            if (!subject.matches(subjectRegex)) {
                                System.out.println("[국어|수학|영어|과학] 중 하나를 입력하세요.");
                                continue; // 잘못된 입력이면 다시 입력 받음
                            }

                            System.out.println("값 2개 입력하세요.");

                            while (true) {
                                System.out.print("최저 점수: ");
                                String min = input.nextLine();
                                if (!min.matches(studentScoreRegex)) {
                                    System.out.println("0~100 사이의 숫자를 입력하세요.");
                                    continue;
                                }
                                scoreMin = Integer.parseInt(min);
                                break;
                            }

                            while (true) {
                                System.out.print("최고 점수: ");
                                String max = input.nextLine();
                                if (!max.matches(studentScoreRegex)) {
                                    System.out.println("0~100 사이의 숫자를 입력하세요.");
                                    continue;
                                }
                                scoreMax = Integer.parseInt(max);
                                break;
                            }
                            // 검색 실행
                            printStudentList(searchStudent.SearchRange(subject, Math.min(scoreMin, scoreMax),
                                    Math.max(scoreMax, scoreMin)));
                            break;
                        }
                        break;

                    case "6": //등급 검색
                        System.out.println("검색할 등급을 입력하세요.");
                        while (true) {
                            String grade = input.nextLine();
                            if (grade.matches(gradeRegex)) {
                                printStudentList(searchStudent.searchByGrade(grade));
                                break;
                            }
                            System.out.println("[A,B,C,D,E,F] 중 한 개의 등급을 입력하세요.");
                        }
                        break;

                    case "7": //재시험
                        System.out.println("재시험 대상을 검색합니다.[F등급]");
                        printStudentList(searchStudent.searchByReTest());

                        break;

                }
                break;
            } else {
                System.out.println("[(1-7)번 번호를 입력해주세요.]");
            }

        }
    }

    /**
     *  학생 리스트 출력
     * @param searchList
     */
    public void printStudentList(List<StudentDto> searchList) {
        studentInfo();
        searchList.forEach((dto) -> System.out.printf("%-4s %-9s %-5s %-4s %-4s %-5s %-4s %-5s %-3s\n", dto.getName(),
                dto.getStudentNumber(), dto.getKorean(), dto.getEnglish(), dto.getMath(),
                dto.getScience(), dto.getTotal(), dto.getAverage(), dto.getGrade()));
        System.out.println();
    }

    /*

     */

    /**
     * 학생 정보 정렬
     */
    @Override
    public void studentInfoSort() {
        Scanner input = new Scanner(System.in);
        sortMenu();
        String number = input.nextLine();
        switch (number) {
            case "1":
                sortedStudent.SortedByTotal();
                totalStudentInfo();
                break;

            case "2":
                sortedStudent.SortedByAverage();
                totalStudentInfo();
                break;

            case "3":
                sortedStudent.SortedByName();
                totalStudentInfo();
                break;

            case "4":
                sortedStudent.SortedBySnoNumber();
                totalStudentInfo();
                break;

            case "5":
                sortedStudent.SortedByGrade();
                totalStudentInfo();
                break;
        }
    }
    /**
     * 4번 메뉴 (종료)
     */
    @Override
    public void studentExit() {
        System.out.println("종료합니다.");
    }


    /**
     * 학생 전체 정보 출력
     */
    void totalStudentInfo() {
        System.out.println("=".repeat(35));
        Map<String, StudentDto> map = searchStudent.searchAll();
        studentInfo();
        map.forEach((key, value) -> System.out.printf("%-4s %-9s %-5s %-4s %-4s %-5s %-4s %-5s %-3s\n",
                value.getName(), value.getStudentNumber(), value.getKorean(),
                value.getEnglish(), value.getMath(), value.getScience(),
                value.getTotal(), value.getAverage(), value.getGrade()));
    }

    /**
     *  안내 정보 출력
     */
    void studentInfo() {
        System.out.printf("%-5s %-7s %-4s %-3s %-3s %-3s %-3s %-3s %-3s\n", "이름", "학번", "국어", "영어", "수학", "과학", "총점", "평균", "등급");
    }

    /**
     * StudentIO 초기화
     */
    @Override
    public void initialize() {
        searchStudent.initialize();
    }

    @Override
    public void start(){
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