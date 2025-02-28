package employee.repository;

import employee.dto.EmployeeDto;

import java.util.function.Function;

public class SalaryRepositoryImp implements SalaryRepository{


    @Override
    public Boolean updateSalary(EmployeeDto employeeDto, Function<Integer, Integer> function) {
        return null;
    }
}
