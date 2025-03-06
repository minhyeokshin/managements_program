package employee.service;

import common.ErrorCode;
import employee.dto.EmployeeDto;
import employee.dto.SalaryHistoryDto;
import employee.repository.*;
import employee.service.pay.PayRaiseRate;
import employee.service.pay.PayRateManager;
import employee.service.pay.PayRateSecretary;
import employee.service.pay.PayRateStaff;
import employee.vo.EmployeeVo;
import exception.EmployeeException;
import exception.NotFoundException;

import java.util.HashMap;
import java.util.List;

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
    public EmployeeSalaryService updateSalary(Integer eno) throws EmployeeException {
        try {
            EmployeeDto employeeDto = employeeReadRepo.ReadOne(eno).orElseThrow(() -> new NotFoundException(ErrorCode.EMPLOYEE_NOT_FOUND
                    + " eno : " + eno)); // 해당 사원 dto
            setPayRaiseRateMap(new PayRateStaff(),new PayRateSecretary(), new PayRateManager());

            Integer beforeSalary = returnSalary(employeeDto.getSalary());
            Integer afterSalary = makeSalaryUpdateDto(employeeDto).returnSalary(employeeDto.getSalary());
            salaryRepository.updateSalaryHistory(employeeDto.getEno(), beforeSalary, afterSalary);

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
            return this;
        }catch (EmployeeException e){
            throw new EmployeeException(ErrorCode.DB_READ_ONE_ERROR);
        }
    }

    @Override
    public List<SalaryHistoryDto> getSalaryHistory(Integer eno) throws EmployeeException {
        return salaryRepository.salaryHistory(eno).orElseThrow(() -> new NotFoundException(ErrorCode.ERROR_EMPTY_HISTORY_LIST + " eno : " + eno));
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

    private EmployeeSalaryServiceImp makeSalaryUpdateDto(EmployeeDto employeeDto) throws EmployeeException {
        employeeDto.setSalary(payRaiseRateHashMap.get(employeeDto.getRole()).apply(employeeDto.getSalary())); // 롤에따라 다른 인상정책 적용
        return this;
    }
}
