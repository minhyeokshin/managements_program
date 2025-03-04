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


    @Override
    public void ReadOne(Integer eno) {

        validCheck.isValidEmployeeNumber(eno);
        EmployeeDto employeeDto = employeeReadService.ReadOne(eno);

        if (employeeDto == null){
            System.out.println(EMPLOYEE_NOT_FOUND.getText());
            return;
        }

        System.out.println("사번\t이름\t입사일\t직군\t부서번호\t임금");
        System.out.printf("%d\t %s \t %d-%d-%d \t %s \t %d \t %d\n",
                employeeDto.getEno(),employeeDto.getName(),
                employeeDto.getEnteryear(),employeeDto.getEntermonth(),employeeDto.getEnterday(),
                employeeDto.getRole(),employeeDto.getSecno(),employeeDto.getSalary());
    }

    @Override
    public void ReadAll() {
        List<EmployeeDto> employeeDtoList = employeeReadService.ReadAll();

        if(employeeDtoList.isEmpty()){
            System.out.println(READ_ALL_NULL.getText());
            return;
        }

        System.out.println(READ_ALL.getText());
        System.out.println("사번\t이름\t입사일\t직군\t부서번호\t임금");
        System.out.println(MENU_BORDER.getText());

        for (int i = 0; i < employeeDtoList.size(); i++) {
            System.out.printf("%d\t %s \t %d-%d-%d \t %s \t %d \t %d\n",
                    employeeDtoList.get(i).getEno(),employeeDtoList.get(i).getName(),
                    employeeDtoList.get(i).getEnteryear(),employeeDtoList.get(i).getEntermonth(),employeeDtoList.get(i).getEnterday(),
                    employeeDtoList.get(i).getRole(),employeeDtoList.get(i).getSecno(),employeeDtoList.get(i).getSalary());
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
