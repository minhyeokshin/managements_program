package employee.controller;

import employee.dto.EmployeeDto;

public interface EmployeeReadCont {
    EmployeeDto ReadOne(Integer eno);
    void ReadAll();

    void Read();

}
