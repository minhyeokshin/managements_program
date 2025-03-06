package employee.service;

import common.ErrorCode;
import employee.dto.EmployeeDto;
import employee.repository.EmployeeReadRepo;
import employee.repository.EmployeeUpdateRepo;
import employee.service.pay.PayRaiseRate;
import employee.service.pay.PayRateManager;
import employee.service.pay.PayRateSecretary;
import employee.service.pay.PayRateStaff;
import employee.vo.EmployeeVo;
import exception.EmployeeException;
import exception.NotFoundException;

import java.util.HashMap;

public class EmployeeSalaryServiceImp implements EmployeeSalaryService {
    EmployeeReadRepo employeeReadRepo;
    EmployeeUpdateRepo employeeUpdateRepo;

    public EmployeeSalaryServiceImp(EmployeeReadRepo employeeReadRepo, EmployeeUpdateRepo employeeUpdateRepo) {
        this.employeeReadRepo = employeeReadRepo;
        this.employeeUpdateRepo = employeeUpdateRepo;
    }
    @Override
    public EmployeeDto updateSalary(Integer eno) throws EmployeeException {
        try {
            EmployeeDto employeeDto = employeeReadRepo.ReadOne(eno).orElseThrow(() -> new NotFoundException(ErrorCode.EMPLOYEE_NOT_FOUND
                    + " eno : " + eno)); // 해당 사원 dto

        HashMap<String, PayRaiseRate> payRaiseRateHashMap = new HashMap<>();
        payRaiseRateHashMap.put("Staff",new PayRateStaff());
        payRaiseRateHashMap.put("Secretary",new PayRateSecretary());
        payRaiseRateHashMap.put("Manager",new PayRateManager()); // 각각의 인상정택 저장 맵

        employeeDto.setSalary(payRaiseRateHashMap.get(employeeDto.getRole()).apply(employeeDto.getSalary())); // 롤에따라 다른 인상정책 적용

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
            return employeeReadRepo.ReadOne(eno).orElseThrow(() -> new NotFoundException(ErrorCode.EMPLOYEE_NOT_FOUND
                    + " eno : " + eno));
        }catch (EmployeeException e){
            throw new EmployeeException(ErrorCode.DB_READ_ONE_ERROR);
        }
    }
}
