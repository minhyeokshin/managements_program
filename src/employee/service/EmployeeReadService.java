package employee.service;

import employee.dto.EmployeeDto;

import java.util.List;

public interface EmployeeReadService {
    EmployeeDto ReadOne(Integer eno);
    List<EmployeeDto> ReadAll();

}
