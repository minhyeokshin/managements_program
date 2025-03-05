package employee.service;

import common.ErrorCode;
import employee.dto.EmployeeDto;
import employee.repository.EmployeeReadRepo;
import employee.repository.EmployeeUpdateRepo;
import employee.vo.EmployeeVo;
import exception.EmployeeException;
import exception.NotFoundException;

public class EmployeeUpdateServiceImp implements EmployeeUpdateService{

    private final EmployeeUpdateRepo employeeUpdateRepo;
    private final EmployeeReadRepo employeeReadRepo;

    public EmployeeUpdateServiceImp(EmployeeUpdateRepo employeeUpdateRepo, EmployeeReadRepo employeeReadRepo) {
        this.employeeUpdateRepo = employeeUpdateRepo;
        this.employeeReadRepo = employeeReadRepo;
    }


    @Override
    public EmployeeDto update(EmployeeDto employeeDto) throws EmployeeException {
        try {
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
            return employeeReadRepo.ReadOne(employeeDto.getEno()).orElseThrow(() -> new NotFoundException(String.valueOf(ErrorCode.UPDATE_FAILED)));
        }catch (EmployeeException e){
            throw new EmployeeException(ErrorCode.DB_UPDATE_ERROR);
        }
    }
}
