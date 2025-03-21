package employee.repository;

import employee.dto.EmployeeDto;

import java.util.List;
import java.util.Optional;

public interface EmployeeReadRepo {
    Optional<EmployeeDto> ReadOne(Integer eno);
    Optional<List<EmployeeDto>> ReadAll();

}
