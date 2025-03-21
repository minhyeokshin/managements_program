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
import java.util.function.Function;

/**
 * 직원 급여 업데이트 서비스 구현체
 */
public class EmployeeSalaryServiceImp implements EmployeeSalaryService {
    EmployeeReadRepo employeeReadRepo;
    EmployeeUpdateRepo employeeUpdateRepo;
    SalaryRepository salaryRepository;

    HashMap<String, PayRaiseRate<Integer,Integer>> payRaiseRateHashMap = new HashMap<>();

    public EmployeeSalaryServiceImp(EmployeeReadRepo employeeReadRepo, EmployeeUpdateRepo employeeUpdateRepo, SalaryRepository salaryRepository) {
        this.employeeReadRepo = employeeReadRepo;
        this.employeeUpdateRepo = employeeUpdateRepo;
        this.salaryRepository = salaryRepository;
        setPayRaiseRateMap();
    }


    /**
     * 직원 급여 업데이트 및 내역 저장
     * @param eno 직원 번호
     * @return 직원 업데이트 구현체(체이닝을 위한 return this)
     * @throws EmployeeException 직원 업데이트 예외처리
     */
    @Override
    public EmployeeSalaryService updateSalary(Integer eno) throws EmployeeException {
        try {
            EmployeeDto employeeDto = employeeReadRepo
                    .ReadOne(eno)
                    .orElseThrow(() ->
                            new NotFoundException(ErrorCode.EMPLOYEE_NOT_FOUND + " eno : " + eno)); // 해당 사원 dto

            Integer beforeSalary = employeeDto.getSalary();
            Integer afterSalary = calculateUpdatedSalary(employeeDto).getSalary();
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

    /**
     * 측정 직원 급여 업데이트 내역
     * @param eno 특정 직원 번호
     * @return 직원 급여 내역 리스트
     * @throws EmployeeException 급여내역 조회 실패 예외처리
     */
    @Override
    public List<SalaryHistoryDto> getSalaryHistory(Integer eno) throws EmployeeException {
        return salaryRepository
                .salaryHistory(eno)
                .orElseThrow(() ->
                        new NotFoundException(ErrorCode.ERROR_EMPTY_HISTORY_LIST + " eno : " + eno));
    }


    /**
     * 급여 갱신 전략 map
     */
    private void setPayRaiseRateMap() {
        payRaiseRateHashMap.put("Staff",new PayRateStaff()::apply);
        payRaiseRateHashMap.put("Secretary", new PayRateSecretary()::apply);
        payRaiseRateHashMap.put("Manager", new PayRateManager()::apply);
    }

    /**
     * 전략에 따른 급여내역 업데이트
     * @param employeeDto 직원 dto
     * @return 급여 업데이트 된 직원 dto
     * @throws EmployeeException 급여 업데이트 실패 예외처리
     */
    private EmployeeDto calculateUpdatedSalary(EmployeeDto employeeDto) throws EmployeeException {
        employeeDto.setSalary(payRaiseRateHashMap
                .get(employeeDto.getRole())
                .apply(employeeDto.getSalary())); // 롤에따라 다른 인상정책 적용
        return employeeDto;
    }
}


