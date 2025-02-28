package employee.service;

import employee.dto.EmployeeDto;
import employee.repository.EmployeeCreateRepo;

public class EmployeeCreateServiceImp implements EmployeeCreateService{

    private final EmployeeCreateRepo employeeCreateRepo;

    public EmployeeCreateServiceImp(EmployeeCreateRepo employeeCreateRepo) {
        this.employeeCreateRepo = employeeCreateRepo;
    }


    @Override
    public EmployeeDto create(EmployeeDto employeeDto) {
        return null;
    }


}
