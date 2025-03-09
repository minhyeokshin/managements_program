package employee.service;

import employee.dto.EmployeeDto;

import java.util.List;

/**
 * 직원 조회 인터페이스
 */
public interface EmployeeReadService {
    /**
     * 특정 직원 조회 메소드
     * @param eno 직원 번호
     * @return 직원 dto
     */
    EmployeeDto ReadOne(Integer eno);

    /**
     * 전체 직원 조회 메소드
     * @return 직원 리스트
     */
    List<EmployeeDto> ReadAll();

}
