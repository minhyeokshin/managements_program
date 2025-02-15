package controller;

import dto.StudentDto;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import service.SearchStudent;
import service.SortedStudent;
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
    /*
        Welcome to the Student System 출력문
     */
    @Override
    public void welcome() {
        System.out.println("=".repeat(35));
        System.out.println("\tWelcome to the Student System");
        System.out.println("=".repeat(35));
        toTalMenu();
    }

    /*
        학생정보 입력, 검색, 정렬, 종료 메뉴
     */
    @Override
    public void toTalMenu() {
        System.out.println("\t\t\t  Menu");
        System.out.println("=".repeat(35));
        System.out.println("1. 학생정보 입력\t 2. 학생정보 검색");
        System.out.println("3. 학생정보 정렬\t 4. 종료");
        System.out.println("1-4번 번호 중 1개를 입력하세요.");
    }

    /*
        학생정보 검색에 필요한 메뉴
     */
    @Override
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

    @Override
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
    @Override
    public String numberInput() {
        Scanner input = new Scanner(System.in);
        return input.nextLine();
    }

    /*
        학생 정보 입력 -> StudentDto 객체
     */
    @Override
    public void studentInfoInput() {
        String studentNameRegex = "^[a-z]{3,20}$";
        String studentScoreRegex = "(^[0-9]$)|(^[1-9][0-9]$)|(^[1][0][0]$)";


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
    String getUserInput(String info, String regex) {
        String studentInfo;
        System.out.print(info + ": ");
        studentInfo = StudentOutputImp.input.next();
        //제대로 입력하면 return
        if (studentInfo.matches(regex)) {
            return studentInfo;
        } else { //제대로 입력할때까지 반복
            while (true) {
                System.out.print(info + ": ");
                studentInfo = StudentOutputImp.input.next();
                if (studentInfo.matches(regex)) {
                    break;
                }
            }
        }
        return studentInfo;
    }

    @Override
    public void studentInfoSearch() {
        Scanner in = new Scanner(System.in);
        System.out.println("학생 정보를 검색합니다.");
        searchMenu();

        String number = in.nextLine();
        switch (number) {
            case "1": //전체 학생 정보 출력
                System.out.println("전체 학생 정보를 출력합니다.");
                totalStudentInfo();
                break;
            case "2": //학번 검색
                System.out.println("학번을 입력해 주세요.");
                String studentNumber;
                StudentDto dto;
                while (true) {
                    try {
                        studentNumber = in.nextLine();
                        dto = searchStudent.searchBySno(studentNumber);
                        if (dto == null) {
                            throw new IllegalArgumentException();
                        } else {
                            break;
                        }
                    } catch (IllegalArgumentException e) {
                        System.out.println("학번 다시 입력하세요!");
                    }
                }

                System.out.printf("%-4s %-9s %-3s %-3s %-3s %-3s %-3s %-3s %-3s\n", "이름", "학번", "국어", "영어", "수학", "과학",
                        "총점", "평균", "등급");
                System.out.printf("%-4s %-9s %-3s %-3s %-3s %-3s %-3s %-3s %-3s\n", dto.getName(),
                        dto.getStudentNumber(), dto.getKorean(), dto.getEnglish(), dto.getMath(),
                        dto.getScience(), dto.getTotal(), dto.getAverage(), dto.getGrade());

                //Map<String, StudentDto> map1 = searchStudent.searchBySno(studentNumber);

                break;
            case "3":
                System.out.println("최고점수를 검색할 과목을 입력하세요.");
                String subject;
                List<StudentDto> scoreList;
                while (true) {
                    try {
                        subject = input.nextLine();
                        scoreList = searchStudent.MaxTotalMap(subject);
                        if (scoreList.isEmpty()) {
                            throw new IllegalArgumentException();
                        } else {
                            break;
                        }
                    } catch (IllegalArgumentException e) {
                        System.out.println("올바른 과목명을 입력해주세요. [국어, 영어, 수학, 과학]");
                    }
                }
                System.out.printf("%-4s %-9s %-3s %-3s %-3s %-3s %-3s %-3s %-3s\n", "이름", "학번", "국어", "영어", "수학", "과학",
                        "총점", "평균", "등급");
                for (StudentDto dto1 : scoreList) {
                    System.out.printf("%-4s %-9s %-3s %-3s %-3s %-3s %-3s %-3s %-3s\n", dto1.getName(),
                            dto1.getStudentNumber(), dto1.getKorean(), dto1.getEnglish(), dto1.getMath(),
                            dto1.getScience(), dto1.getTotal(), dto1.getAverage(), dto1.getGrade());
                }

                System.out.println();
                break;
            case "4":
                System.out.println("최저점수를 검색할 과목을 입력하세요.");
                String subject2;
                List<StudentDto> scoreList2;
                while (true) {
                    try {
                        subject2 = input.nextLine();
                        scoreList2 = searchStudent.MaxTotalMap(subject2);
                        if (scoreList2.isEmpty()) {
                            throw new IllegalArgumentException();
                        } else {
                            break;
                        }
                    } catch (IllegalArgumentException e) {
                        System.out.println("올바른 과목명을 입력해주세요. [국어, 영어, 수학, 과학]");
                    }
                }
                System.out.printf("%-4s %-9s %-3s %-3s %-3s %-3s %-3s %-3s %-3s\n", "이름", "학번", "국어", "영어", "수학", "과학",
                        "총점", "평균", "등급");
                for (StudentDto dto2 : scoreList2) {
                    System.out.printf("%-4s %-9s %-3s %-3s %-3s %-3s %-3s %-3s %-3s\n", dto2.getName(),
                            dto2.getStudentNumber(), dto2.getKorean(), dto2.getEnglish(), dto2.getMath(),
                            dto2.getScience(), dto2.getTotal(), dto2.getAverage(), dto2.getGrade());
                }

                System.out.println();
                break;

            case "5":
                System.out.println("검색할 과목을 입력하세요.");
                String subject5 = input.nextLine();
                System.out.println("값 2개 입력해하숍 ");
                System.out.println("최저 점수: ");
                double min = input.nextInt();
                System.out.println("최고 점수: ");
                double max = input.nextInt();

                List<StudentDto> scoreList5 = searchStudent.SearchRange(subject5, min, max);
                System.out.printf("%-4s %-9s %-3s %-3s %-3s %-3s %-3s %-3s %-3s\n", "이름", "학번", "국어", "영어", "수학", "과학",
                        "총점", "평균", "등급");
                for (StudentDto dto2 : scoreList5) {
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
                List<StudentDto> list = searchStudent.searchByReTest();
                System.out.printf("%-4s %-9s %-3s %-3s %-3s %-3s %-3s %-3s %-3s\n", "이름", "학번", "국어", "영어", "수학", "과학",
                        "총점", "평균", "등급");
                for (StudentDto dto1 : list) {
                    System.out.printf("%-4s %-9s %-3s %-3s %-3s %-3s %-3s %-3s %-3s\n", dto1.getName(),
                            dto1.getStudentNumber(), dto1.getKorean(), dto1.getEnglish(), dto1.getMath(),
                            dto1.getScience(), dto1.getTotal(), dto1.getAverage(), dto1.getGrade());
                }

                System.out.println();

                break;
        }
    }

    /*

     */

    @Override
    public void studentInfoSort() {
        sortMenu();
        Scanner in = new Scanner(System.in);
        //StudentIO의 map을 가져온다!
        Map<String, StudentDto> map;

        String number = in.nextLine();
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

    @Override
    public void studentExit() {
        System.out.println("종료합니다.");
    }

    //리스트 받은 Search Method
    void searchProcess() {
        String subject = StudentOutputImp.input.nextLine();
        List<StudentDto> scoreList = searchStudent.MaxTotalMap(subject);
        System.out.printf("%-4s %-9s %-3s %-3s %-3s %-3s %-3s %-3s %-3s\n", "이름", "학번", "국어", "영어", "수학", "과학",
                "총점", "평균", "등급");
        for (StudentDto dto : scoreList) {
            System.out.printf("%-4s %-9s %-3s %-3s %-3s %-3s %-3s %-3s %-3s\n", dto.getName(),
                    dto.getStudentNumber(), dto.getKorean(), dto.getEnglish(), dto.getMath(),
                    dto.getScience(), dto.getTotal(), dto.getAverage(), dto.getGrade());
        }

        System.out.println();
    }

    void totalStudentInfo() {
        System.out.println("=".repeat(35));
        Map<String, StudentDto> map = searchStudent.searchAll();
        System.out.printf("%-4s %-9s %-3s %-3s %-3s %-3s %-3s %-3s %-3s\n", "이름", "학번", "국어", "영어", "수학", "과학",
                "총점", "평균", "등급");
        map.entrySet().stream().forEach(
                x -> System.out.printf("%-4s %-9s %-5s %-4s %-4s %-5s %-4s %-5s %-3s\n", x.getValue().getName(),
                        x.getValue().getStudentNumber(), x.getValue().getKorean(), x.getValue().getEnglish(),
                        x.getValue().getMath(),
                        x.getValue().getScience(), x.getValue().getTotal(), x.getValue().getAverage(),
                        x.getValue().getGrade()));
    }


    @Override
    public void initialize() {
        searchStudent.initialize();
    }
}