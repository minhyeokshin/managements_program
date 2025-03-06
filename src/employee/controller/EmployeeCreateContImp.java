package employee.controller;

import employee.dto.EmployeeDto;
import employee.service.EmployeeCreateService;
import employee.service.EmployeeReadService;

import java.util.List;
import java.util.Scanner;

import static common.EmployeeText.*;
import static common.ErrorCode.*;

public class EmployeeCreateContImp implements EmployeeCreateCont {

    Scanner in = new Scanner(System.in);
    private final String EMPLOYEE_NUMBER_REGEX = "^[1-9][0-9]*$";

    private final EmployeeCreateService createService;
    private final EmployeeReadService readService;


    public EmployeeCreateContImp(EmployeeCreateService createService, EmployeeReadService readService) {
        this.createService = createService;
        this.readService = readService;

    }


    @Override
    public EmployeeDto create() {

        EmployeeDto employeeDto = new EmployeeDto();

        List<EmployeeDto> employeeDtoList = readService.ReadAll();

        System.out.println(ENTER_INPUT_EMPLOYEE.getText());
        int inputEno = 0;
        if (employeeDtoList.isEmpty()) {
            inputEno = 0;
        } else {
            inputEno = employeeDtoList.get(employeeDtoList.size() - 1).getEno();
        }
        employeeDto.setEno(inputEno + 1);

        System.out.printf(ENTER_NAME.getText());
        employeeDto.setName(in.nextLine());
        System.out.printf(ENTER_ENTRY_YEAR.getText());
        employeeDto.setEnteryear(inputNum());
        System.out.printf(ENTER_ENTRY_MONTH.getText());
        employeeDto.setEntermonth(inputNum());
        System.out.printf(ENTER_ENTRY_DAY.getText());
        employeeDto.setEnterday(inputNum());
        System.out.printf(ENTER_ROLE.getText());
        employeeDto.setRole(in.nextLine());
        System.out.printf(ENTER_SECTION_NUMBER.getText());
        employeeDto.setSecno(inputNum());
        System.out.printf(ENTER_SALARY.getText());
        employeeDto.setSalary(inputNum());



        return createService.create(employeeDto);
    }

    @Override
    public void createrun(){

        EmployeeDto createEmployee = create();

        if (createEmployee.getEno() == null){
            System.out.println(EMPLOYEE_CREATE_FAIL.getText());
            return;
        }else {
            System.out.println(EMPLOYEE_CREATE_SUCCESS.getText());
            System.out.println(PRINT_TITLE.getText());
            System.out.println(PRINT_ROUND.getText());
            System.out.printf("%-5d %-10s %2d-%02d-%02d  %-12s %8d %,14d\n",
                    createEmployee.getEno(),createEmployee.getName(),
                    createEmployee.getEnteryear(),createEmployee.getEntermonth(),createEmployee.getEnterday(),
                    createEmployee.getRole(),createEmployee.getSecno(),createEmployee.getSalary());
        }
    }

    private int inputNum(){
        String str = null;
        int input = 0;
        while (true){
            str = in.nextLine();
            if (str.matches(EMPLOYEE_NUMBER_REGEX)) {
                input = Integer.parseInt(str);
                break;
            }
            System.out.println(ERROR_INPUT_NUM.getText());
        }
        return input;
    }

}