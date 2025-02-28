package employee.controller;

import employee.dto.EmployeeDto;

import java.util.List;

public interface EmployeeReadRepo {
    EmployeeDto ReadOne(Integer eno);
    boolean ReadAll();

}
