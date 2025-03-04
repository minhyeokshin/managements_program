package employee.repository;

import employee.vo.EmployeeVo;

import java.sql.SQLException;

public interface EmployeeCreateRepo {
    void create (EmployeeVo employeeVo);
}
