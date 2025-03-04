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
    public void Delete(EmployeeDto employeeDto) {
       // employeeDeleteRepo.Delete(employeeDto); // vo로 바꿔야함
    }
}
