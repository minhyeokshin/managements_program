package employee.controller;

import common.EmployeeText;
import common.ErrorCode;
import common.ValidCheck;
import employee.dto.EmployeeDto;
import employee.service.EmployeeReadService;
import employee.service.EmployeeUpdateService;

import java.util.Scanner;

/**
 * 직원 정보 업데이트 컨트롤러 구현 클래스
 */
public class EmployeeUpdateContImp implements EmployeeUpdateCont {
    private final EmployeeUpdateService employeeUpdateService;
    private final EmployeeReadService employeeReadService;
    private final ValidCheck validCheck;
    private final Scanner scanner = new Scanner(System.in);


    public EmployeeUpdateContImp( EmployeeUpdateService employeeUpdateService,EmployeeReadService employeeReadService, ValidCheck validCheck) {
        this.employeeReadService = employeeReadService;
        this.employeeUpdateService = employeeUpdateService;
        this.validCheck = validCheck;
    }

    /**
     * 직원 정보 업데이트 실행
     */
    @Override
    public void update() {
        System.out.print(EmployeeText.UPDATE_EMPLOYEE_NUMBER.getText());

        int employeeNumber = validCheck.getValidEmployeeNumber(scanner);
        EmployeeDto existingEmployee = employeeReadService.ReadOne(employeeNumber);

        if (existingEmployee == null) {
            System.out.println(ErrorCode.EMPLOYEE_NOT_FOUND.getText());
            return;
        }

        System.out.println(EmployeeText.UPDATE_PROMPT.getText());
        System.out.println(EmployeeText.CHOOSE_UPDATE_OPTION.getText());
        System.out.print(EmployeeText.ENTER_CHOICE.getText());

        String choice = scanner.nextLine();
        EmployeeDto.EmployeeDtoBuilder updatedEmployeeBuilder = existingEmployee.toBuilder();

        switch (choice) {
            case "1":
                System.out.print(EmployeeText.ENTER_NAME.getText());
                String name = scanner.nextLine();
                validCheck.isValidEmployeeName(name);
                updatedEmployeeBuilder.name(name);
                break;
            case "2":
                System.out.print(EmployeeText.ENTER_ROLE.getText());
                String role = scanner.nextLine();
                validCheck.isValidEmployeeRole(role);
                updatedEmployeeBuilder.role(role);
                break;
            case "3":
                System.out.print(EmployeeText.ENTER_SALARY.getText());
                String salaryInput = scanner.nextLine();
                validCheck.isValidEmployeeSalary(salaryInput);
                updatedEmployeeBuilder.salary(Integer.parseInt(salaryInput));
                break;
            case "4":
                System.out.print(EmployeeText.ENTER_ENTRY_YEAR.getText());
                updatedEmployeeBuilder.enteryear(scanner.nextInt());
                break;
            case "5":
                System.out.print(EmployeeText.ENTER_ENTRY_MONTH.getText());
                updatedEmployeeBuilder.entermonth(scanner.nextInt());
                break;
            case "6":
                System.out.print(EmployeeText.ENTER_ENTRY_DAY.getText());
                updatedEmployeeBuilder.enterday(scanner.nextInt());
                break;
            case "7":
                System.out.print(EmployeeText.ENTER_SECTION_NUMBER.getText());
                updatedEmployeeBuilder.secno(scanner.nextInt());
                break;
            case "8":
                System.out.println(EmployeeText.UPDATE_CANCELLED.getText());
                return;
            default:
                System.out.println(EmployeeText.INVALID_CHOICE.getText());
                return;
        }

        EmployeeDto updatedEmployee = updatedEmployeeBuilder.build();

        printEmployeeInfo(employeeUpdateService.update(updatedEmployee));
        System.out.println(EmployeeText.UPDATE_SUCCESS.getText());
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
        System.out.println("============================================================\n");
    }

}



