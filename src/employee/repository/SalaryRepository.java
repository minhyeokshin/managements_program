package employee.repository;

import employee.dto.EmployeeDto;
import employee.dto.SalaryHistoryDto;

import java.sql.SQLException;
import java.util.List;
import java.util.function.Function;

public interface SalaryRepository {
    Boolean updateSalary(EmployeeDto employeeDto, Function<Integer, Integer> function) throws SQLException;

    List<SalaryHistoryDto> salaryHistory(int eno);
}
