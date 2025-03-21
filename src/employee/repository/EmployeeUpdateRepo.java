package employee.repository;

import employee.dto.EmployeeDto;
import employee.vo.EmployeeVo;

/**
 * 사원 정보를 수정하는 인터페이스
 */
public interface EmployeeUpdateRepo {
    /**
     * 사원정보를 수정하는 메서드
     * @param employeeVo 수정할 사원 정보 객체
     */
    void update (EmployeeVo employeeVo);
}
