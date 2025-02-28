package employee.service;

import employee.dto.EmployeeDto;

import java.util.List;

public interface EmployeeReadService {
    EmployeeDto ReadOne(EmployeeDto employeeDto);
    List<EmployeeDto> ReadAll();

}
