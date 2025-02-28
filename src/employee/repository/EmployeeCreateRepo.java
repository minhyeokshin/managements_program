package employee.repository;

import employee.dto.EmployeeDto;

public interface EmployeeCreateRepo {
    Boolean create (EmployeeDto employeeDto);
}
