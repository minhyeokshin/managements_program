package employee.service;

import common.ErrorCode;
import employee.dto.EmployeeDto;
import employee.repository.EmployeeReadRepo;
import employee.repository.EmployeeReadRepoImp;
import exception.EmployeeException;
import exception.NotFoundException;

import java.util.List;

public class EmployeeReadServiceImp implements EmployeeReadService {

    private final EmployeeReadRepo employeeReadRepo;

    public EmployeeReadServiceImp(EmployeeReadRepo employeeReadRepo) {
        this.employeeReadRepo = employeeReadRepo;
    }

    @Override
    public EmployeeDto ReadOne(Integer eno) throws EmployeeException {
        try {
            return employeeReadRepo.ReadOne(eno).orElseThrow(() -> new NotFoundException(ErrorCode.EMPLOYEE_NOT_FOUND
                    + " eno : " + eno));
        }catch (EmployeeException e) {
            throw new EmployeeException(ErrorCode.DB_READ_ONE_ERROR);
        }
    }

    @Override
    public List<EmployeeDto> ReadAll() {
        try {
            return employeeReadRepo.ReadAll().orElseThrow(() -> new NotFoundException(String.valueOf(ErrorCode.ERROR_EMPTY_LIST)));
        } catch (EmployeeException e) {
            throw new EmployeeException(ErrorCode.DB_READ_ALL_ERROR);
        }
    }
}
