package employee.repository;

import employee.dto.EmployeeDto;

import java.util.List;

public interface EmployeeReadRepo {
    EmployeeDto ReadOne(Integer eno);
    List<EmployeeDto> ReadAll();

}
