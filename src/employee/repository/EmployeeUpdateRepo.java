package employee.repository;

import employee.dto.EmployeeDto;

public interface EmployeeUpdateRepo {
    Boolean update (EmployeeDto employeeDto);
}
