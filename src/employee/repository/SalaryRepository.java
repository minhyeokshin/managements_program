package employee.repository;

import employee.dto.EmployeeDto;
import employee.dto.SalaryHistoryDto;

import java.sql.SQLException;
import java.util.List;
import java.util.function.Function;

public interface SalaryRepository {
    public void updateSalaryHistory(int eno, int oldSalary, int newSalary)

    List<SalaryHistoryDto> salaryHistory(int eno);
}
