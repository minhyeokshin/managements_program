package controller;

import dto.StudentDto;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import service.SearchStudent;
import service.SortedStudent;
import service.StudentIO;
import service.StudentInput;

public class StudentOutputImp implements StudentOutput {
    static Scanner input = new Scanner(System.in);
    SearchStudent searchStudent;
    SortedStudent sortedStudent;
    StudentInput studentInput;

    public StudentOutputImp(SearchStudent searchStudent, SortedStudent sortedStudent, StudentInput studentInput) {
        this.searchStudent = searchStudent;
        this.sortedStudent = sortedStudent;
        this.studentInput = studentInput;
    }
    public void welcome() {
        System.out.println("=".repeat(35));
        System.out.println("\twelcome to Student System");
        System.out.println("=".repeat(35));

    }

    public void toTalMenu() {
        System.out.println("\t\t\t  Menu");
        System.out.println("=".repeat(35));
        System.out.println("1. 학생정보 입력\t 2. 학생정보 검색");
        System.out.println("3. 학생정보 정렬\t 4. 종료");
        System.out.println("1-4번 번호 중 1개를 입력하세요.");
    }

    public void searchMenu() {
        System.out.println("=".repeat(35));
        System.out.println("\t\t\t 2. Search");
        System.out.println("=".repeat(35));
        System.out.println("1. 전체 학생 검색\t 2. 학번 검색");
        System.out.println("3. 과목별 최고 점수 검색\t 4. 과목별 최저 점수 검색");
        System.out.println("5. 점수 범위 검색\t6. 등급 검색");
        System.out.println("7. 재시험 대상 학생 검색");
        System.out.println("1-5번 번호 중 1개를 입력하세요.");
    }

    public void sortMenu() {
        System.out.println("=".repeat(35));
        System.out.println("\t\t\t 3. Sort");
        System.out.println("=".repeat(35));
        System.out.println("1. 총점 정렬\t 2. 평균 정렬");
        System.out.println("3. 학생 이름 정렬\t 4. 학번 정렬");
        System.out.println("5. 학점 정렬");

    }


    /*
        menu에서 1-4번 중 번호 선택
     */
    public String numberInput() {
        Scanner input = new Scanner(System.in);
        return input.nextLine();
    }

    /*
        학생 정보 입력 -> StudentDto 객체
     */
    public void studentInfoInput() {
        String studentNameRegex = "^[a-z]{3,20}$";
        String studentScoreRegex = "(^[0-9]$)|(^[1-9][0-9]$)|(^[1][0][0]$)";
        String studentNumber = studentInput.initStudentNumberCounter();

        String name = getUserInput("이름", studentNameRegex);
        int korean = Integer.parseInt(getUserInput("국어 점수", studentScoreRegex));
        int english = Integer.parseInt(getUserInput("영어 점수", studentScoreRegex));
        int math = Integer.parseInt(getUserInput("수학 점수", studentScoreRegex));
        int science = Integer.parseInt(getUserInput("과학 점수", studentScoreRegex));

        StudentDto studentDto = StudentDto.builder()
                .name(name)
                .korean(korean)
                .english(english)
                .math(math)
                .science(science)
                .build();

        //studentInput.createPerfectDto(studentDto, StudentInput.initStudentNumberCounter());
        //putinput(createDto(studentDto))
        //사용자에게
        studentInput.putStudentTable(studentDto);

    }

    //throw new IllegalArgumentException("다시 !");
    private String getUserInput(String info, String regex) {
        String studentInfo;
        System.out.print(info + ": ");
        studentInfo = input.next();
        //제대로 입력하면 return
        if (studentInfo.matches(regex)) {
            return studentInfo;
        } else { //제대로 입력할때까지 반복
            while (true) {
                System.out.print(info + ": ");
                studentInfo = input.next();
                if (studentInfo.matches(regex)) {
                    break;
                }
            }
        }
        return studentInfo;
    }

    public void studentInfoSearch() {

        System.out.println("학생 정보를 검색합니다.");
        searchMenu();

        String number = input.nextLine();
        switch (number) {
            case "1": //전체 학생 정보 출력
                System.out.println("전체 학생 정보를 출력합니다.");
                Map<String, StudentDto> map = searchStudent.searchAll();
                System.out.printf("%-4s %-9s %-3s %-3s %-3s %-3s %-3s %-3s %-3s\n", "이름", "학번", "국어", "영어", "수학", "과학",
                        "총점", "평균", "등급");
                map.entrySet().stream().forEach(
                        x -> System.out.printf("%-4s %-9s %-5s %-4s %-4s %-5s %-4s %-5s %-3s\n", x.getValue().getName(),
                                x.getValue().getStudentNumber(), x.getValue().getKorean(), x.getValue().getEnglish(),
                                x.getValue().getMath(),
                                x.getValue().getScience(), x.getValue().getTotal(), x.getValue().getAverage(),
                                x.getValue().getGrade()));
                break;
            case "2": //학번 검색
                System.out.println("학번을 입력해 주세요.");
                String studentNumber = input.nextLine();
                StudentDto dto = searchStudent.searchBySno(studentNumber);
                System.out.printf("%-4s %-9s %-3s %-3s %-3s %-3s %-3s %-3s %-3s\n", "이름", "학번", "국어", "영어", "수학", "과학",
                        "총점", "평균", "등급");
                System.out.printf("%-4s %-9s %-3s %-3s %-3s %-3s %-3s %-3s %-3s\n", dto.getName(),
                        dto.getStudentNumber(), dto.getKorean(), dto.getEnglish(), dto.getMath(),
                        dto.getScience(), dto.getTotal(), dto.getAverage(), dto.getGrade());

                //Map<String, StudentDto> map1 = searchStudent.searchBySno(studentNumber);

                break;
            case "3":
                System.out.println("최고점수를 검색할 과목을 입력하세요.");
                searchProcess();

                break;
            case "4":
                System.out.println("최저점수를 검색할 과목을 입력하세요.");
                searchProcess();
                break;

            case "5":
                System.out.println("검색할 과목을 입력하세요.");
                String subject2 = input.nextLine();
                System.out.println("값 2개 입력해 ");
                System.out.println("최저 점수: ");
                double min = input.nextInt();
                System.out.println("최고 점수: ");
                double max = input.nextInt();

                List<StudentDto> scoreList2 = searchStudent.SearchRange(subject2, min, max);
                System.out.printf("%-4s %-9s %-3s %-3s %-3s %-3s %-3s %-3s %-3s\n", "이름", "학번", "국어", "영어", "수학", "과학",
                        "총점", "평균", "등급");
                for (StudentDto dto2 : scoreList2) {
                    System.out.printf("%-4s %-9s %-3s %-3s %-3s %-3s %-3s %-3s %-3s\n", dto2.getName(),
                            dto2.getStudentNumber(), dto2.getKorean(), dto2.getEnglish(), dto2.getMath(),
                            dto2.getScience(), dto2.getTotal(), dto2.getAverage(), dto2.getGrade());
                }

                System.out.println();
                break;
                /*
                "5. 점수 범위 검색\t6. 등급 검색");
        System.out.println("7. 재시험 대상 학생 검색");
                 */
            case "6":
                System.out.println("검색할 등급을 입력하세요.");
                searchProcess();
                break;

            case "7":
                System.out.println("재시험 대상을 검색합니다.");
                searchProcess();
                break;
        }
    }

    //리스트 받은 Search Method
    private void searchProcess() {
        String subject = input.nextLine();

        List<StudentDto> scoreList = searchStudent.MaxTotalMap(subject);

        Map<String, StudentDto> map = searchStudent.searchAll();
        System.out.printf("%-4s %-9s %-3s %-3s %-3s %-3s %-3s %-3s %-3s\n", "이름", "학번", "국어", "영어", "수학", "과학",
                "총점", "평균", "등급");
        for (StudentDto dto : scoreList) {
            System.out.printf("%-4s %-9s %-3s %-3s %-3s %-3s %-3s %-3s %-3s\n", dto.getName(),
                    dto.getStudentNumber(), dto.getKorean(), dto.getEnglish(), dto.getMath(),
                    dto.getScience(), dto.getTotal(), dto.getAverage(), dto.getGrade());
        }

        System.out.println();
    }

    public void studentInfoSort() {
        sortMenu();
        Scanner in = new Scanner(System.in);
        String number = in.nextLine();
        switch (number) {
            case "1":
                List<StudentDto> list = sortedStudent.printSortedByTotal();
                System.out.printf("%-4s %-9s %-3s %-3s %-3s %-3s %-3s %-3s %-3s\n", "이름", "학번", "국어", "영어", "수학", "과학",
                        "총점", "평균", "등급");
                for (StudentDto dto : list) {
                    System.out.printf("%-4s %-9s %-3s %-3s %-3s %-3s %-3s %-3s %-3s\n", dto.getName(),
                            dto.getStudentNumber(), dto.getKorean(), dto.getEnglish(), dto.getMath(),
                            dto.getScience(), dto.getTotal(), dto.getAverage(), dto.getGrade());
                }
                break;
            case "2":
                List<StudentDto> list1 = sortedStudent.printSortedByAverage();
                for (StudentDto dto : list1) {
                    System.out.printf("%-4s %-9s %-3s %-3s %-3s %-3s %-3s %-3s %-3s\n", dto.getName(),
                            dto.getStudentNumber(), dto.getKorean(), dto.getEnglish(), dto.getMath(),
                            dto.getScience(), dto.getTotal(), dto.getAverage(), dto.getGrade());
                }
                break;

            case "3":
                sortedStudent.printSortedByName();
                break;

            case "4":
                sortedStudent.printSortedBySnoNumber();
                break;

            case "5":
                sortedStudent.printSortedByGrade();
                break;

        }


    }

    public void studentExit() {
        System.out.println("종료합니다.");
    }

}