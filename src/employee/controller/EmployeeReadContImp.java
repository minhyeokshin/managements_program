package employee.controller;

import employee.dto.EmployeeDto;
import employee.service.EmployeeReadService;

public class EmployeeReadContImp implements EmployeeReadCont{

    private final EmployeeReadService employeeReadService;


    public EmployeeReadContImp(EmployeeReadService employeeReadService) {
        this.employeeReadService = employeeReadService;
    }

    @Override
    public EmployeeDto ReadOne(Integer eno) {
        return null;
    }

    @Override
    public boolean ReadAll() {
        return false;
    }
}
