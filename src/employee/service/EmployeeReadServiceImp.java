package employee.service;

import employee.dto.EmployeeDto;
import employee.repository.EmployeeReadRepo;

import java.util.List;

public class EmployeeReadServiceImp implements EmployeeReadService{

    private final EmployeeReadRepo employeeReadRepo;

    public EmployeeReadServiceImp(EmployeeReadRepo employeeReadRepo) {
        this.employeeReadRepo = employeeReadRepo;
    }

    @Override
    public EmployeeDto ReadOne(Integer eno) {
        return null;
    }

    @Override
    public List<EmployeeDto> ReadAll() {
        return null;
    }
}
