package employee.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *  Employee 정보 DTO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
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

