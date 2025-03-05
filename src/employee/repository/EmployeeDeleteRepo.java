package employee.repository;

import employee.dto.EmployeeDto;
import employee.vo.EmployeeVo;

public interface EmployeeDeleteRepo {
    void Delete (EmployeeVo employeeVo);
}
