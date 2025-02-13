package dto;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
@Builder
@Setter
@Getter
public class StudentDto {
    private String studentNumber;
    private String name;
    private int korean;
    private int english;
    private int math;
    private int science;
    private int total;
    private double average;
    private String grade;

    public StudentDto(String studentNumber, String name, int korean, int english, int math, int science, int total, double average, String grade) {
        this.studentNumber = studentNumber;
        this.name = name;
        this.korean = korean;
        this.english = english;
        this.math = math;
        this.science = science;
        this.total = total;
        this.average = average;
        this.grade = grade;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getKorean() {
        return korean;
    }

    public void setKorean(int korean) {
        this.korean = korean;
    }

    public int getEnglish() {
        return english;
    }

    public void setEnglish(int english) {
        this.english = english;
    }

    public int getMath() {
        return math;
    }

    public void setMath(int math) {
        this.math = math;
    }

    public int getScience() {
        return science;
    }

    public void setScience(int science) {
        this.science = science;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public double getAverage() {
        return average;
    }

    public void setAverage(double average) {
        this.average = average;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "학생 정보[" +
                "학번: '" + studentNumber + '\'' +
                ", 이름: '" + name + '\'' +
                ", 국어: " + korean +
                ", 영어: " + english +
                ", 수학: " + math +
                ", 과학: " + science +
                ", 총점: " + total +
                ", 평균: " + average +
                ", 등급: " + grade + '\'' +
                ']';
    }
}
