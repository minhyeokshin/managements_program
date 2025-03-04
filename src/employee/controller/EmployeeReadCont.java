package employee.controller;

import employee.dto.EmployeeDto;

public interface EmployeeReadCont {
    void ReadOne(Integer eno);
    void ReadAll();

    void Read();

}
