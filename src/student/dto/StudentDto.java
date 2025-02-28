package student.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 학생 정보 student.dto
 */
@ToString
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

    public StudentDto() {
    }
}
