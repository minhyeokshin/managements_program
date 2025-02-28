package employee.repository;

import employee.dto.EmployeeDto;

public interface EmployeeDeleteRepo {
    Boolean Delete (EmployeeDto employeeDto);
}
