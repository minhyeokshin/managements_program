package employee.service;

import employee.dto.EmployeeDto;

/**
 * 직원 삭제 인터페이스
 */
public interface EmployeeDeleteService {
    /**
     * 직원 삭제 메서드
     * @param eno 삭제할 사원 번호
     */
    void Delete (Integer eno);
}
