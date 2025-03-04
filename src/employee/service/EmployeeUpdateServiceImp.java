package employee.service;

import employee.dto.EmployeeDto;
import employee.repository.EmployeeReadRepo;
import employee.repository.EmployeeUpdateRepo;
import employee.vo.EmployeeVo;

public class EmployeeUpdateServiceImp implements EmployeeUpdateService{

    private final EmployeeUpdateRepo employeeUpdateRepo;
    private final EmployeeReadRepo employeeReadRepo;

    public EmployeeUpdateServiceImp(EmployeeUpdateRepo employeeUpdateRepo, EmployeeReadRepo employeeReadRepo) {
        this.employeeUpdateRepo = employeeUpdateRepo;
        this.employeeReadRepo = employeeReadRepo;
    }


    @Override
    public EmployeeDto update(EmployeeDto employeeDto) {
        employeeUpdateRepo.update(EmployeeVo.builder()
                .eno(employeeDto.getEno())
                .name(employeeDto.getName())
                .enteryear(employeeDto.getEnteryear())
                .entermonth(employeeDto.getEntermonth())
                .enterday(employeeDto.getEnterday())
                .role(employeeDto.getRole())
                .secno(employeeDto.getSecno())
                .salary(employeeDto.getSalary())
                .build());
        return employeeReadRepo.ReadOne(employeeDto.getEno());
    }
}
