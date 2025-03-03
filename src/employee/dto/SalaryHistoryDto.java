package employee.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SalaryHistoryDto {
    private Integer eno;
    private String name;
    private Integer oldSalary;
    private Integer newSalary;
}
