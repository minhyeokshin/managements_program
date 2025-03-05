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

    /**
     * 생성자: 서비스 및 유효성 검사 객체 초기화
     */
    public EmployeeUpdateContImp( EmployeeUpdateService employeeUpdateService,EmployeeReadService employeeReadService, ValidCheck validCheck) {
        this.employeeReadService = employeeReadService;
        this.employeeUpdateService = employeeUpdateService;
        this.validCheck = validCheck;
    }

    /**
     * 직원 정보를 업데이트하는 메서드
     * @param eno 직원 번호
     * @return 업데이트된 직원 정보
     */
    @Override
    public EmployeeDto update(Integer eno) {
        validCheck.isValidEmployeeNumber(eno);

        // 기존 직원 정보 가져오기
        EmployeeDto existingEmployee = employeeReadService.ReadOne(eno);
        System.out.println("con");
        if (existingEmployee == null) {
            throw new RuntimeException(ErrorCode.EMPLOYEE_NOT_FOUND.getText());
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println(EmployeeText.UPDATE_PROMPT.getText());

        System.out.println(EmployeeText.CHOOSE_UPDATE_OPTION.getText());
        System.out.print(EmployeeText.ENTER_CHOICE.getText());
        String choice = scanner.nextLine();
        //  Lombok의 @Builder(toBuilder = true) 기능을 활용하여 기존 객체를 기반으로 일부 값만 변경할 수 있도록 하는 역할
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
                int enteryear = Integer.parseInt(scanner.nextLine());
                updatedEmployeeBuilder.enteryear(enteryear);
                break;

            case "5":
                System.out.print(EmployeeText.ENTER_ENTRY_MONTH.getText());
                int entermonth = Integer.parseInt(scanner.nextLine());
                updatedEmployeeBuilder.entermonth(entermonth);
                break;

            case "6":
                System.out.print(EmployeeText.ENTER_ENTRY_DAY.getText());
                int enterday = Integer.parseInt(scanner.nextLine());
                updatedEmployeeBuilder.enterday(enterday);
                break;

            case "7":
                System.out.print(EmployeeText.ENTER_SECTION_NUMBER.getText());
                int secno = Integer.parseInt(scanner.nextLine());
                updatedEmployeeBuilder.secno(secno);
                break;

            case "8":
                System.out.println(EmployeeText.UPDATE_CANCELLED.getText());
                return existingEmployee;

            default:
                System.out.println(EmployeeText.INVALID_CHOICE.getText());
                return existingEmployee;
        }

        EmployeeDto updatedEmployee = updatedEmployeeBuilder.build();

        employeeUpdateService.update(updatedEmployee);
        return updatedEmployee;
    }
}



