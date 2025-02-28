package employee.service;

import employee.dto.EmployeeDto;
import employee.repository.EmployeeUpdateRepo;

public class EmployeeUpdateServiceImp implements EmployeeUpdateService{

    private final EmployeeUpdateRepo employeeUpdateRepo;

    public EmployeeUpdateServiceImp(EmployeeUpdateRepo employeeUpdateRepo) {
        this.employeeUpdateRepo = employeeUpdateRepo;
    }


    @Override
    public EmployeeDto update(EmployeeDto employeeDto) {
        return null;
    }
}
