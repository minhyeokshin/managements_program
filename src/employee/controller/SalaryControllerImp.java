package employee.controller;

import employee.dto.EmployeeDto;
import employee.service.EmployeeSalaryService;

/**
 * 급여 인상 컨트롤러 구현 클래스
 */
public class SalaryControllerImp implements SalaryController {

    private final EmployeeSalaryService employeeSalaryService;

    // 필요하다면 ReadService 또는 다른 의존성을 주입할 수 있습니다.
    public SalaryControllerImp(EmployeeSalaryService employeeSalaryService) {
        this.employeeSalaryService = employeeSalaryService;
    }

    /**
     * 급여 인상 기능
     * @param eno 직원 번호
     * @return 인상된 직원 정보
     */
    @Override
    public EmployeeDto payRaise(Integer eno) {
        // EmployeeSalaryService를 통해 급여 인상 수행
        return employeeSalaryService.updateSalary(eno);
    }
}
