package employee.dto;

import lombok.Builder;
import lombok.Data;

/**
 * Employee의 Salary 변경 이력 Dto
 */
@Data
@Builder
public class SalaryHistoryDto {
    private Integer eno;
    private String name;
    private Integer oldSalary;
    private Integer newSalary;
}
