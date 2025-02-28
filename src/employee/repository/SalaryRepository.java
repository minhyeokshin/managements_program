package employee.repository;

import employee.dto.EmployeeDto;

import java.util.function.Function;

public interface SalaryRepository {
    Boolean updateSalary(EmployeeDto employeeDto, Function<Integer, Integer> function);
}
