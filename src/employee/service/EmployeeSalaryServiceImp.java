package employee.service;

import common.ErrorCode;
import employee.dto.EmployeeDto;
import employee.repository.EmployeeReadRepo;
import employee.repository.EmployeeUpdateRepo;
import employee.repository.SalaryRepository;
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
    SalaryRepository salaryRepository;

    HashMap<String, PayRaiseRate> payRaiseRateHashMap = new HashMap<>();

    public EmployeeSalaryServiceImp(EmployeeReadRepo employeeReadRepo, EmployeeUpdateRepo employeeUpdateRepo, SalaryRepository salaryRepository) {
        this.employeeReadRepo = employeeReadRepo;
        this.employeeUpdateRepo = employeeUpdateRepo;
        this.salaryRepository = salaryRepository;
    }
    @Override
    public EmployeeDto updateSalary(Integer eno) throws EmployeeException {
        try {
            EmployeeDto employeeDto = employeeReadRepo.ReadOne(eno).orElseThrow(() -> new NotFoundException(ErrorCode.EMPLOYEE_NOT_FOUND
                    + " eno : " + eno)); // 해당 사원 dto
            setPayRaiseRateMap(new PayRateStaff(),new PayRateSecretary(), new PayRateManager());

            logSalaryHistory(employeeDto);

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

    private void setPayRaiseRateMap(PayRaiseRate payRaiseRateStaff, PayRaiseRate payRaiseRateSecretary, PayRaiseRate payRaiseRateManager) {
        payRaiseRateHashMap.put("Staff",payRaiseRateStaff);
        payRaiseRateHashMap.put("Secretary", payRaiseRateSecretary);
        payRaiseRateHashMap.put("Manager", payRaiseRateManager);
    }

    private Integer returnSalary(Integer eno) throws EmployeeException {
        return employeeReadRepo.ReadOne(eno).orElseThrow(()
                        -> new NotFoundException(ErrorCode.EMPLOYEE_NOT_FOUND + "returnBeforeSalary"))
                .getSalary();
    }

    private void logSalaryHistory(EmployeeDto employeeDto) throws EmployeeException {
        Integer beforeSalary = returnSalary(employeeDto.getEno());
        employeeDto.setSalary(payRaiseRateHashMap.get(employeeDto.getRole()).apply(employeeDto.getSalary())); // 롤에따라 다른 인상정책 적용
        Integer afterSalary = returnSalary(employeeDto.getEno());
        salaryRepository.updateSalaryHistory(employeeDto.getEno(), beforeSalary, afterSalary);
    }
}
