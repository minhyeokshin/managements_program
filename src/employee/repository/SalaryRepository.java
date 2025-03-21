package employee.repository;

import employee.dto.EmployeeDto;
import employee.dto.SalaryHistoryDto;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

/**
 * Employee의 Salary 변경 이력을 DB에 저장하고 반환하는 인터페이스
 */
public interface SalaryRepository {
    void updateSalaryHistory(int eno, int oldSalary, int newSalary);

    Optional<List<SalaryHistoryDto>> salaryHistory(int eno);
}
