package employee.dto;

import lombok.Data;

@Data
public class EmployeeDto {
    private Integer eno;
    private String name;
    private Integer enteryear;
    private Integer entermonth;
    private Integer enterday;
    private String role;
    private String secno;
    private String salary;
}

