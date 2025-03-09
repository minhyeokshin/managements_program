package employee.service;

import common.ErrorCode;
import employee.dto.EmployeeDto;
import employee.repository.EmployeeCreateRepo;
import employee.repository.EmployeeDeleteRepo;
import employee.repository.EmployeeReadRepo;
import employee.vo.EmployeeVo;
import exception.EmployeeException;

/**
 * 직원 삭제 서비스 구현체
 */
public class EmployeeDeleteServiceImp implements EmployeeDeleteService{

    private final EmployeeDeleteRepo employeeDeleteRepo;
    private final EmployeeReadRepo employeeReadRepo;


    public EmployeeDeleteServiceImp(EmployeeDeleteRepo employeeDeleteRepo, EmployeeReadRepo employeeReadRepo) {
        this.employeeDeleteRepo = employeeDeleteRepo;
        this.employeeReadRepo = employeeReadRepo;
    }


    /**
     * 직원 삭제 메서드
     * @param eno 삭제할 사원 번호
     * @throws EmployeeException 직원 삭제 실패 예외
     */
    @Override
    public void Delete(Integer eno) throws EmployeeException {
        EmployeeDto employeeDto = employeeReadRepo.ReadOne(eno).orElseThrow(()->new EmployeeException(ErrorCode.DB_READ_ONE_ERROR));
        try {
            employeeDeleteRepo.Delete(EmployeeVo.builder()
                    .eno(employeeDto.getEno())
                    .name(employeeDto.getName())
                    .enteryear(employeeDto.getEnteryear())
                    .entermonth(employeeDto.getEntermonth())
                    .enterday(employeeDto.getEnterday())
                    .role(employeeDto.getRole())
                    .secno(employeeDto.getSecno())
                    .salary(employeeDto.getSalary())
                    .build());
        }catch (EmployeeException e){
            throw new EmployeeException(ErrorCode.DB_DELETE_ERROR);
        }
    }
}
