package employee.controller;

import employee.dto.EmployeeDto;
import employee.service.EmployeeUpdateService;

public class EmployeeUpdateContImp implements EmployeeUpdateCont{

    private final EmployeeUpdateService employeeUpdateService;


    public EmployeeUpdateContImp(EmployeeUpdateService employeeUpdateService) {
        this.employeeUpdateService = employeeUpdateService;
    }


    @Override
    public EmployeeDto update(Integer eno) {
        return null;
    }
}
