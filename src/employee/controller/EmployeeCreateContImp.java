package employee.controller;

import employee.dto.EmployeeDto;
import employee.service.EmployeeCreateService;

public class EmployeeCreateContImp implements EmployeeCreateCont{

    private final EmployeeCreateService createService;


    public EmployeeCreateContImp(EmployeeCreateService createService) {
        this.createService = createService;
    }

    @Override
    public EmployeeDto create(EmployeeDto employeeDto) {
        return null;
    }
}
