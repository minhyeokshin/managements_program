package employee.controller;

import common.ValidCheck;
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
    private final ValidCheck validCheck = new ValidCheck();

    public EmployeeCreateContImp(EmployeeCreateService createService, EmployeeReadService readService) {
        this.createService = createService;
        this.readService = readService;

    }


    /**
     * 직원 생성 기능
     * @return employeedto
     */
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
        employeeDto.setName(inputName());
        System.out.printf(ENTER_ENTRY_YEAR.getText());
        employeeDto.setEnteryear(inputNumRegex(validCheck.ENTERYEAR_REGEX));
        System.out.printf(ENTER_ENTRY_MONTH.getText());
        employeeDto.setEntermonth(inputNumRegex(validCheck.ENTERMONTH_REGEX));
        System.out.printf(ENTER_ENTRY_DAY.getText());
        employeeDto.setEnterday(inputNumRegex(validCheck.ENTERDAY_REGEX));
        System.out.printf(ENTER_ROLE.getText());
        System.out.println(INPUT_ROLE.getText());
        System.out.println(ENTER_CHOICE.getText());
        String roleinput = null;
        switch (inputNum()){
            case 1 -> roleinput = "Staff";
            case 2 -> roleinput = "Manager";
            case 3 -> roleinput = "Secretary";
            default -> roleinput = "Staff";
        }
        employeeDto.setRole(roleinput);
        System.out.printf(ENTER_SECTION_NUMBER.getText());
        System.out.println(INPUT_SECNO.getText());
        employeeDto.setSecno(inputNumRegex(validCheck.SECINPUT_REGEX));
        System.out.printf(ENTER_SALARY.getText());
        employeeDto.setSalary(inputNum());



        return createService.create(employeeDto);
    }

    /**
     * 직원 생성 후 출력 기능
     */
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

    /**
     * 숫자 입력 메소드
     * @return int input
     */
    private int inputNum(){
        String str;
        int input = 0;
        while (true){
            str = in.nextLine();
            if (str.matches(validCheck.NUMBER_REGEX)){
                input = Integer.parseInt(str);
                break;
            }
            System.out.println(ERROR_NUM.getText());
        }
        return input;
    }

    /**
     * 이름 입력 메소드
     * @return String name
     */
    private String inputName(){
        String name;
        do {
            System.out.printf(ENTER_NAME.getText());
            name = in.nextLine().trim();
        } while (name.isEmpty() || !name.matches(validCheck.NAME_INPUT_REGEX));
        return name;
    }

    /**
     * 벨리드체크하여 숫자 입력 메소드
     * @param regex
     * @return int input
     */
    private int inputNumRegex(String regex){
        String str;
        int input = 0;
        while (true){
            str = in.nextLine();
            if (str.matches(regex)){
                input = Integer.parseInt(str);
                break;
            }
            System.out.println(ERROR_NUM.getText());
        }
        return input;
    }

}