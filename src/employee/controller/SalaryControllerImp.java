package employee.controller;

import common.EmployeeText;
import common.ErrorCode;
import common.ValidCheck;
import employee.dto.EmployeeDto;
import employee.dto.SalaryHistoryDto;
import employee.service.EmployeeReadService;
import employee.service.EmployeeSalaryService;
import exception.NotFoundException;

import java.util.List;
import java.util.Scanner;

import static common.EmployeeText.PRINT_SALARY_HISTORY;
import static common.ErrorCode.ERROR_EMPTY_HISTORY_LIST;
import static common.ErrorCode.ERROR_INPUT_NUM;

/**
 * 직원 급여 관리 컨트롤러 구현 클래스.
 * <p>
 * 이 클래스는 직원의 급여 인상, 급여 이력 조회 등의 기능을 담당한다.
 * 사용자의 입력을 받아 적절한 서비스를 호출하고, 처리 결과를 출력하는 역할을 수행한다.
 */
public class SalaryControllerImp implements SalaryController {

    private final EmployeeSalaryService employeeSalaryService;
    private final EmployeeReadService employeeReadService;
    private final ValidCheck validCheck;
    private final Scanner scanner = new Scanner(System.in);

    /**
     * SalaryControllerImp 생성자.
     *
     * @param employeeSalaryService 급여 관련 서비스 인터페이스
     * @param employeeReadService 직원 조회 서비스 인터페이스
     * @param validCheck 입력값 검증 유틸리티
     */
    public SalaryControllerImp(EmployeeSalaryService employeeSalaryService, EmployeeReadService employeeReadService, ValidCheck validCheck) {
        this.employeeSalaryService = employeeSalaryService;
        this.employeeReadService = employeeReadService;
        this.validCheck = validCheck;
    }

    /**
     * 급여 관련 기능 메뉴를 처리하는 메서드.
     * <p>
     * 사용자가 선택한 옵션에 따라 급여 인상(payRaise) 또는 급여 이력 조회(salaryHistory)를 수행한다.
     */
    @Override
    public void handleSalaryMenu() {
        System.out.println(EmployeeText.SALARY_MENU.getText());
        System.out.print(EmployeeText.ENTER_CHOICE.getText());

        int choice = validCheck.getValidEmployeeNumber(scanner);
        switch (choice) {
            case 1:
                payRaise();
                break;
            case 2:
                salaryHistory();
                break;
            default:
                System.out.println(ERROR_INPUT_NUM.getText());
                break;
        }
    }

    /**
     * 특정 직원의 급여 인상 내역을 조회하고 출력하는 메서드.
     * <p>
     * 사용자가 입력한 직원 번호를 기반으로 급여 이력을 검색하며, 해당 직원이 없으면 오류 메시지를 출력한다.
     */
    @Override
    public void salaryHistory() {
        System.out.print(EmployeeText.CHECK_SALARY_HISTORY.getText());
        int eno = validCheck.getValidEmployeeNumber(scanner);
        try {
            List<SalaryHistoryDto> employeeDtoList = employeeSalaryService.getSalaryHistory(eno);

            if (employeeDtoList.isEmpty()) {
                System.out.println(EmployeeText.NO_SALARY_HISTORY.getText());
                return;
            }

            System.out.println(PRINT_SALARY_HISTORY.getText());

            for (SalaryHistoryDto salaryDto : employeeDtoList) {
                System.out.printf("%-5d %-10s %,14d %,14d%n",
                        salaryDto.getEno(), salaryDto.getName(), salaryDto.getOldSalary(), salaryDto.getNewSalary());
            }
        } catch (NotFoundException e) {
            System.out.println(ERROR_EMPTY_HISTORY_LIST.getText());
        }
    }

    /**
     * 특정 직원의 급여를 인상하는 메서드.
     * <p>
     * 사용자가 입력한 직원 번호를 검증한 후 급여 인상을 수행한다.
     * 급여 인상이 성공하면, 해당 직원의 최신 정보를 출력한다.
     */
    @Override
    public void payRaise() {
        System.out.print(EmployeeText.ENTER_EMPLOYEE_PAYRAISE.getText());
        int employeeNumber = validCheck.getValidEmployeeNumber(scanner);

        try {
            employeeSalaryService.updateSalary(employeeNumber);
            System.out.println(EmployeeText.SALARY_UPDATE_SUCCESS.getText());
            printEmployeeInfo(employeeReadService.ReadOne(employeeNumber));
        } catch (Exception e) {
            System.out.println(ErrorCode.DB_UPDATE_SALARY_ERROR.getText() + ": " + e.getMessage());
        }
    }

    /**
     * 특정 직원의 정보를 테이블 형식으로 출력하는 메서드.
     *
     * @param employee 직원 정보 DTO (EmployeeDto)
     */
    private void printEmployeeInfo(EmployeeDto employee) {
        System.out.println(EmployeeText.PRINT_TITLE.getText());
        System.out.println(EmployeeText.PRINT_ROUND.getText());
        System.out.printf("%-5d %-10s %2d-%02d-%02d  %-12s %8d %,14d%n",
                employee.getEno(), employee.getName(),
                employee.getEnteryear(), employee.getEntermonth(), employee.getEnterday(),
                employee.getRole(), employee.getSecno(), employee.getSalary());
        System.out.println("=====================================================================\n");
    }
}
