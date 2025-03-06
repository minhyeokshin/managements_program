package employee.controller;

import common.EmployeeText;
import common.ErrorCode;
import common.ValidCheck;
import employee.dto.EmployeeDto;
import employee.service.EmployeeReadService;
import employee.service.EmployeeSalaryService;

import java.util.Scanner;

/**
 * 직원 급여 인상 컨트롤러 구현 클래스
 */
public class SalaryControllerImp implements SalaryController {

    private final EmployeeSalaryService employeeSalaryService;
    private final EmployeeReadService employeeReadService;
    private final ValidCheck validCheck;
    private final Scanner scanner = new Scanner(System.in);

    public SalaryControllerImp(EmployeeSalaryService employeeSalaryService, EmployeeReadService employeeReadService, ValidCheck validCheck) {
        this.employeeSalaryService = employeeSalaryService;
        this.employeeReadService = employeeReadService;
        this.validCheck = validCheck;
    }

    @Override
    public void payRaise() {
        System.out.print(EmployeeText.ENTER_EMPLOYEE_NUMBER.getText());

        int employeeNumber = validCheck.getValidEmployeeNumber(scanner);
        EmployeeDto employeeDto = employeeReadService.ReadOne(employeeNumber);

        if (employeeDto == null) {
            System.out.println(ErrorCode.EMPLOYEE_NOT_FOUND.getText());
            return;
        }

        try {
            EmployeeDto updatedEmployee = employeeSalaryService.updateSalary(employeeNumber);
            System.out.println(EmployeeText.SALARY_UPDATE_SUCCESS.getText());
            printEmployeeInfo(updatedEmployee);
        } catch (Exception e) {
            System.out.println(ErrorCode.DB_UPDATE_SALARY_ERROR.getText() + ": " + e.getMessage());
        }
    }


    /**
     * 직원 정보를 보기 좋은 테이블 형식으로 출력하는 메서드
     */
    private void printEmployeeInfo(EmployeeDto employee) {
        System.out.println(EmployeeText.PRINT_TITLE.getText());
        System.out.println(EmployeeText.PRINT_ROUND.getText());
        System.out.printf("%-5d %-10s %2d-%02d-%02d  %-12s %8d %,14d\n",
                employee.getEno(), employee.getName(),
                employee.getEnteryear(), employee.getEntermonth(), employee.getEnterday(),
                employee.getRole(), employee.getSecno(), employee.getSalary());
        System.out.println("=====================================================================\n");
    }



}
