package employee.controller;

import common.EmployeeText;
import common.ErrorCode;
import common.ValidCheck;
import employee.dto.EmployeeDto;
import employee.service.EmployeeDeleteService;
import employee.service.EmployeeReadService;

import java.util.Scanner;

/**
 * 직원 삭제 컨트롤러 구현 클래스
 */
public class EmployeeDeleteContImp implements EmployeeDeleteCont {

    private final EmployeeDeleteService employeeDeleteService;
    private final EmployeeReadService employeeReadService;
    private final ValidCheck validCheck;

    public EmployeeDeleteContImp(EmployeeDeleteService employeeDeleteService, EmployeeReadService employeeReadService, ValidCheck validCheck) {
        this.employeeDeleteService = employeeDeleteService;
        this.employeeReadService = employeeReadService;
        this.validCheck = validCheck;
    }

    /**
     * 직원 삭제 기능
     * @return 삭제된 직원 정보 또는 null
     */
    @Override
    public void delete() {
        Scanner scanner  = new Scanner(System.in);
        System.out.print(EmployeeText.DELETE_EMPLOYEE_NUMBER.getText());
        // 유효한 직원 번호인지 검사
        int employeeNumber = validCheck.getValidEmployeeNumber(scanner);

        // 직원 삭제 요청
        try {
            employeeDeleteService.Delete(employeeNumber);
            System.out.println(EmployeeText.DELETE_SUCCESS.getText());
        } catch (Exception e) {
            System.out.println(ErrorCode.DELETE_FAILED.getText() + ": " + e.getMessage());
        }
    }
}
