package employee.controller;

import common.ValidCheck;
import employee.dto.EmployeeDto;
import employee.service.EmployeeReadService;


import java.util.InputMismatchException;
import java.util.List;

import java.util.Scanner;

import static common.EmployeeText.*;
import static common.ErrorCode.*;


public class EmployeeReadContImp implements EmployeeReadCont{

    private final EmployeeReadService employeeReadService;
    private final ValidCheck validCheck;


    public EmployeeReadContImp(EmployeeReadService employeeReadService, ValidCheck validCheck) {
        this.employeeReadService = employeeReadService;
        this.validCheck = validCheck;
    }


    /**
     * 특정 직원 검색 메소드
     * @param eno
     * @return 출력
     */
    @Override
    public EmployeeDto ReadOne(Integer eno) {

        validCheck.isValidEmployeeNumber(eno);
        EmployeeDto employeeDto = employeeReadService.ReadOne(eno);

        if (employeeDto == null){
            System.out.println(EMPLOYEE_NOT_FOUND.getText());
            return null;
        }

        System.out.println(PRINT_TITLE.getText());
        System.out.println(PRINT_ROUND.getText());
        System.out.printf("%-5d %-10s %2d-%02d-%02d  %-12s %8d %,14d\n",
                employeeDto.getEno(),employeeDto.getName(),
                employeeDto.getEnteryear(),employeeDto.getEntermonth(),employeeDto.getEnterday(),
                employeeDto.getRole(),employeeDto.getSecno(),employeeDto.getSalary());
        return employeeDto;
    }

    /**
     * 전체 직원 검색 메소드
     */
    @Override
    public void ReadAll() {
        List<EmployeeDto> employeeDtoList = employeeReadService.ReadAll();

        if(employeeDtoList.isEmpty()){
            System.out.println(READ_ALL_NULL.getText());
            return;
        }

        System.out.println(READ_ALL.getText());
        System.out.println(PRINT_TITLE.getText());
        System.out.println(PRINT_ROUND.getText());

        for (EmployeeDto employee : employeeDtoList) {
            System.out.printf("%-5d %-10s %2d-%02d-%02d  %-12s %8d %,14d\n",
                    employee.getEno(), employee.getName(),
                    employee.getEnteryear(), employee.getEntermonth(), employee.getEnterday(),
                    employee.getRole(),  // 직군 길이 맞춤
                    employee.getSecno(), employee.getSalary());
        }
     }

    @Override
    public void Read() {
        Scanner in = new Scanner(System.in);
        System.out.println(READ_ONE_CHOICE.getText());
        System.out.printf(ENTER_CHOICE.getText());

        try {
            int choice = 0;

            choice = in.nextInt();
            in.nextLine();

            switch (choice){
                case 1:
                    ReadAll();
                    break;
                case 2:
                    System.out.println(READ_ONE_INPUT_ENO.getText());
                    System.out.printf(ENTER_CHOICE.getText());
                    try {
                        int inputeno = 0;

                        inputeno = in.nextInt();
                        in.nextLine();

                        ReadOne(inputeno);
                    } catch (InputMismatchException e) {
                        System.out.println(ERROR_INPUT_NUM.getText());
                    }
                    break;
                default:
                    System.out.println(ERROR_INPUT_NUM.getText());
            }
        } catch (InputMismatchException e) {
            System.out.println(ERROR_INPUT_NUM.getText());
            in.nextLine();
        }
    }

}
