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
    public void handleSalaryMenu() {
        Scanner scanner = new Scanner(System.in);
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


    @Override
    public void salaryHistory() {
        Scanner scanner = new Scanner(System.in);
        System.out.print(EmployeeText.CHECK_SALARY_HISTORY.getText());
        int eno = validCheck.getValidEmployeeNumber(scanner);
        try {
            List<SalaryHistoryDto> employeeDtoList = employeeSalaryService.getSalaryHistory(eno);
            System.out.println(PRINT_SALARY_HISTORY.getText());
            for (SalaryHistoryDto SalaryDto : employeeDtoList){
                System.out.printf("%-5d %-5s %-5d %-5d%n",
                        SalaryDto.getEno(),SalaryDto.getName(),SalaryDto.getOldSalary(),SalaryDto.getNewSalary());
            }
        } catch (NotFoundException e) {
            System.out.println(ERROR_EMPTY_HISTORY_LIST.getText());
        }
    }

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
