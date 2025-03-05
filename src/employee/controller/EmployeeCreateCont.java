package employee.controller;

import employee.dto.EmployeeDto;

public interface EmployeeCreateCont {
    EmployeeDto create (EmployeeDto employeeDto);
    void createrun(EmployeeDto employeeDto);

}


