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
        System.out.println("5. 재시험 대상 학생 검색");
        System.out.println("1-5번 번호 중 1개를 입력하세요.");
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

        //StudentInput에 studentDto 던지기



        /*a b c d
        .bulder.asd

        searchStudent(StudentDto)*/
    }
    //throw new IllegalArgumentException("다시 !");
    private String getUserInput(String di, String studentNumberRegex) {
        /*String studentNumber;
        while(true) {
            System.out.print(di+": ");
            studentNumber = input.next();
            //학번 제대로 입력하면 학번 넘겨
            if(studentNumber.matches(studentNumberRegex)) {
                return studentNumber;
            } else throw new IllegalArgumentException("다시 !");
        }*/

        String studentNumber;
        System.out.print(di+": ");
        studentNumber = input.next();
        //학번 제대로 입력하면 학번 넘겨
        if(studentNumber.matches(studentNumberRegex)) {
            return studentNumber;
        } else { //학번 제대로 입력 못하면 다시 입력
            while(true) {
                System.out.print(di+": ");
                studentNumber = input.next();
                if(studentNumber.matches(studentNumberRegex)) {
                    break;
                }
            }
        }
        return studentNumber;
    }

    public void studentInfoSearch() {
        /*
        1. 전체학생검색
        2. 학번검색
        3. 과목별 최고점수 검색
        3-1. 국어 ……
        과목별 최저점수 검색
        4-1. 국어 …4.2~~~
        점수 범위로 검색
        5.1 총점…~
        재시험 대상학생검색
         */
        System.out.println("학생 정보를 검색합니다.");
        searchMenu();

        Scanner in = new Scanner(System.in);
        String number = in.nextLine();
        switch (number) {
            case "1": //전체 학생 정보 출력
                System.out.println("전체 학생 정보를 출력합니다.");
                Map<String, StudentDto> map = searchStudent.searchAll();
                System.out.printf("%-4s %-9s %-3s %-3s %-3s %-3s %-3s %-3s %-3s\n", "이름", "학번", "국어", "영어", "수학", "과학", "총점", "평균", "등급");
                map.entrySet().stream().forEach(x -> System.out.printf("%-4s %-9s %-5s %-4s %-4s %-5s %-4s %-5s %-3s\n", x.getValue().getName(),
                        x.getValue().getStudentNumber(), x.getValue().getKorean(), x.getValue().getEnglish(), x.getValue().getMath(),
                        x.getValue().getScience(), x.getValue().getTotal(), x.getValue().getAverage(), x.getValue().getGrade()));
                break;
            case "2": //학번 검색
                System.out.println("학번을 입력해 주세요.");
                String studentNumber = input.nextLine();
                StudentDto dto = searchStudent.searchBySno(studentNumber);
                System.out.printf("%-4s %-9s %-3s %-3s %-3s %-3s %-3s %-3s %-3s\n", "이름", "학번", "국어", "영어", "수학", "과학", "총점", "평균", "등급");
                System.out.printf("%-4s %-9s %-3s %-3s %-3s %-3s %-3s %-3s %-3s\n", dto.getName(),dto.getStudentNumber(), dto.getKorean(), dto.getEnglish(), dto.getMath(),
                        dto.getScience(), dto.getTotal(), dto.getAverage(), dto.getGrade());

                //Map<String, StudentDto> map1 = searchStudent.searchBySno(studentNumber);

                break;
            case "3":
                System.out.println("최고점수를 검색할 과목을 입력하세요.");
                String subject = input.nextLine();
                Map<String, List<StudentDto>> maxTotalMap = searchStudent.MaxTotalMap(subject);

                System.out.println();
                //maxTotalMap.get(subject);

                break;
            case "4":
                break;
            case "5":
                break;
        }
    }

    public void studentInfoSort() {
    }

    public void studentExit() {
    }
}
