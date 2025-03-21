package employee.service;

import common.ErrorCode;
import employee.dto.EmployeeDto;
import employee.repository.EmployeeReadRepo;
import employee.repository.EmployeeReadRepoImp;
import exception.EmployeeException;
import exception.NotFoundException;

import java.util.List;

/**
 * 직원 조회 구현체
 */
public class EmployeeReadServiceImp implements EmployeeReadService {

    private final EmployeeReadRepo employeeReadRepo;

    public EmployeeReadServiceImp(EmployeeReadRepo employeeReadRepo) {
        this.employeeReadRepo = employeeReadRepo;
    }

    /**
     * 특정 직원 조회 메소드
     * @param eno 직원 번호
     * @return 직원 dto
     * @throws EmployeeException 직원 조회 실패 예외처리
     */
    @Override
    public EmployeeDto ReadOne(Integer eno) throws EmployeeException {
        try {
            return employeeReadRepo.ReadOne(eno).orElseThrow(() -> new NotFoundException(ErrorCode.EMPLOYEE_NOT_FOUND
                    + " eno : " + eno));
        }catch (EmployeeException e) {
            throw new EmployeeException(ErrorCode.DB_READ_ONE_ERROR);
        }
    }

    /**
     * 전체 직원 조회 메소드
     * @return 전체 직원 조회 리스트
     */
    @Override
    public List<EmployeeDto> ReadAll() {
        try {
            return employeeReadRepo.ReadAll().orElseThrow(() -> new NotFoundException(String.valueOf(ErrorCode.ERROR_EMPTY_LIST)));
        } catch (EmployeeException e) {
            throw new EmployeeException(ErrorCode.DB_READ_ALL_ERROR);
        }
    }
}
