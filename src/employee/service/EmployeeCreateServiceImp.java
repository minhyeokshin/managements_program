package employee.service;

import common.ErrorCode;
import employee.dto.EmployeeDto;
import employee.repository.EmployeeCreateRepo;
import employee.repository.EmployeeReadRepo;
import employee.vo.EmployeeVo;
import exception.EmployeeException;
import exception.NotFoundException;

/**
 * employeeDto를 받아서 디비에 튜플 추가 기능
 */
public class EmployeeCreateServiceImp implements EmployeeCreateService{

    private final EmployeeCreateRepo employeeCreateRepo;
    private final EmployeeReadRepo employeeReadRepo;


    public EmployeeCreateServiceImp(EmployeeCreateRepo employeeCreateRepo, EmployeeReadRepo employeeReadRepo) {
        this.employeeCreateRepo = employeeCreateRepo;
        this.employeeReadRepo = employeeReadRepo;
    }

    /**
     * dto를 파라미터로 받아 vo를 만들어서 employeeCreateRepo.create() 호출
     * @param employeeDto 사원dto
     * @return 해당 객체가 잘 생성 되었는지 디비에서 해당객체 반환 (컨트롤러에서 확인할 수 있음.)
     */
    @Override
    public EmployeeDto create(EmployeeDto employeeDto) throws EmployeeException {
        try {
            employeeCreateRepo.create(EmployeeVo.builder()
                    .eno(employeeDto.getEno())
                    .name(employeeDto.getName())
                    .enteryear(employeeDto.getEnteryear())
                    .entermonth(employeeDto.getEntermonth())
                    .enterday(employeeDto.getEnterday())
                    .role(employeeDto.getRole())
                    .secno(employeeDto.getSecno())
                    .salary(employeeDto.getSalary())
                    .build());
            return employeeReadRepo.ReadOne(employeeDto.getEno()).orElseThrow(() -> new NotFoundException(String.valueOf(ErrorCode.EMPLOYEE_NOT_FOUND)));
        } catch (EmployeeException e) {
            throw new EmployeeException(ErrorCode.DB_CREATE_ERROR);
        }
    }
}
