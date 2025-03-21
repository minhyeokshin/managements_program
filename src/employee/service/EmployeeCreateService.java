package employee.service;

import employee.dto.EmployeeDto;

/**
 * 직원 생성 서비스 인터페이스
 */
public interface EmployeeCreateService {
    /**
     * 직원 생성 메소드
     * @param employeeDto 직원dto
     * @return db에서 읽어온 dto
     */
    EmployeeDto create (EmployeeDto employeeDto);
}
