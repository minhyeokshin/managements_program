package employee.dto;

import lombok.Builder;
import lombok.Data;

/**
 *  Employee 정보 DTO
 */
@Data
@Builder
public class EmployeeDto {
    private Integer eno;
    private String name;
    private Integer enteryear;
    private Integer entermonth;
    private Integer enterday;
    private String role;
    private Integer secno;
    private Integer salary;
}

