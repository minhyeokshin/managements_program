package employee.service;

import employee.dto.EmployeeDto;
import employee.dto.SalaryHistoryDto;

import java.util.List;

public interface EmployeeSalaryService {
    EmployeeSalaryService updateSalary(Integer eno);
    List<SalaryHistoryDto> getSalaryHistory(Integer eno);
}
