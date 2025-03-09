package employee.service;

import employee.dto.EmployeeDto;

/**
 * 직원 정보 업데이트 인터페이스
 */
public interface EmployeeUpdateService {
    /**
     * 직원 정보 업데이트 메소드
     * @param employeeDto 업데이트 할 직원 dto
     * @return 업데이트 된 직원 dto
     */
    EmployeeDto update (EmployeeDto employeeDto);
}
