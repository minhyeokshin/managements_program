package employee.repository;

import employee.dto.EmployeeDto;
import employee.vo.EmployeeVo;

/**
 * 사원 정보를 삭제하는 인터페이스
 */
public interface EmployeeDeleteRepo {
    /**
     * 사원 정보를 삭제하는 메서드
     * @param employeeVo 삭제할 사원 정보 객체
     */
    void Delete (EmployeeVo employeeVo);
}
