package employee.controller;

import employee.dto.EmployeeDto;
import employee.service.EmployeeDeleteService;

public class EmployeeDeleteContImp implements EmployeeDeleteCont{

    private final EmployeeDeleteService employeeDeleteService;


    public EmployeeDeleteContImp(EmployeeDeleteService employeeDeleteService) {
        this.employeeDeleteService = employeeDeleteService;
    }


    @Override
    public EmployeeDto delete(Integer eno) {
        return null;
    }
}
