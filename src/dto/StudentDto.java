package dto;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
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

}
