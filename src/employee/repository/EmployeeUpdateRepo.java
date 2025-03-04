package employee.repository;

import employee.dto.EmployeeDto;
import employee.vo.EmployeeVo;

public interface EmployeeUpdateRepo {
    void update (EmployeeVo employeeVo);
}
