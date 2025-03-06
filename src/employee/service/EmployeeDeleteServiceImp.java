package employee.service;

import common.ErrorCode;
import employee.dto.EmployeeDto;
import employee.repository.EmployeeCreateRepo;
import employee.repository.EmployeeDeleteRepo;
import employee.vo.EmployeeVo;
import exception.EmployeeException;

public class EmployeeDeleteServiceImp implements EmployeeDeleteService{

    private final EmployeeDeleteRepo employeeDeleteRepo;


    public EmployeeDeleteServiceImp(EmployeeDeleteRepo employeeDeleteRepo) {
        this.employeeDeleteRepo = employeeDeleteRepo;
    }


    @Override
    public void Delete(EmployeeDto employeeDto) throws EmployeeException {
        try {
            employeeDeleteRepo.Delete(EmployeeVo.builder()
                    .eno(employeeDto.getEno())
                    .name(employeeDto.getName())
                    .enteryear(employeeDto.getEnteryear())
                    .entermonth(employeeDto.getEntermonth())
                    .enterday(employeeDto.getEnterday())
                    .role(employeeDto.getRole())
                    .secno(employeeDto.getSecno())
                    .salary(employeeDto.getSalary())
                    .build());
        }catch (EmployeeException e){
            throw new EmployeeException(ErrorCode.DB_DELETE_ERROR);
        }
    }
}
