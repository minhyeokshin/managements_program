package employee.service;

import employee.dto.EmployeeDto;
import employee.dto.SalaryHistoryDto;

import java.util.List;

/**
 * 직원 급여 서비스
 */
public interface EmployeeSalaryService {
    /**
     * 특정 직원 정보 업데이트
     * @param eno 직원 번호
     * @return
     */
    EmployeeSalaryService updateSalary(Integer eno);

    /**
     * 직원 급여 업데이트 내역 조회
     * @param eno 특정 직원 번호
     * @return 직원 금여 업데이트 내역 리스트
     */
    List<SalaryHistoryDto> getSalaryHistory(Integer eno);
}
