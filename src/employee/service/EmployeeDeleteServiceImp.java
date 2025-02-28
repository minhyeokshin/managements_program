package employee.service;

import employee.dto.EmployeeDto;
import employee.repository.EmployeeCreateRepo;
import employee.repository.EmployeeDeleteRepo;

public class EmployeeDeleteServiceImp implements EmployeeDeleteService{

    private final EmployeeDeleteRepo employeeDeleteRepo;


    public EmployeeDeleteServiceImp(EmployeeDeleteRepo employeeDeleteRepo) {
        this.employeeDeleteRepo = employeeDeleteRepo;
    }


    @Override
    public EmployeeDto Delete(EmployeeDto employeeDto) {
        return null;
    }
}
