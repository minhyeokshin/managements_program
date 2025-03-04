package employee.controller;

import employee.dto.EmployeeDto;
import employee.service.EmployeeCreateService;
import employee.service.EmployeeReadService;

import java.util.List;
import java.util.Scanner;

public class EmployeeCreateContImp implements EmployeeCreateCont{

    Scanner in = new Scanner(System.in);

    private final EmployeeCreateService createService;
    private final EmployeeReadService readService;


    public EmployeeCreateContImp(EmployeeCreateService createService, EmployeeReadService readService) {
        this.createService = createService;
        this.readService = readService;
    }

    @Override
    public EmployeeDto create(EmployeeDto employeeDto) {

        List <EmployeeDto> employeeDtoList = readService.ReadAll();

        System.out.println("임직원 정보를 입력합니다.");
        int inputEno = employeeDtoList.get(employeeDtoList.size()-1).getEno();
        employeeDto.setEno(inputEno+1);

        System.out.printf("이름 입력");
        employeeDto.setName(in.nextLine());
        System.out.printf("입사년도 입력");
        employeeDto.setEnteryear(Integer.parseInt(in.nextLine()));
        System.out.printf("입사월 입력");
        employeeDto.setEntermonth(Integer.parseInt(in.nextLine()));
        System.out.printf("입사일 입력");
        employeeDto.setEnterday(Integer.parseInt(in.nextLine()));
        System.out.printf("role 입력");
        employeeDto.setRole(in.nextLine());
        System.out.printf("부서번호 입력");
        employeeDto.setSecno(Integer.parseInt(in.nextLine()));
        System.out.printf("샐러리 입력");
        employeeDto.setSalary(Integer.parseInt(in.nextLine()));

        return employeeDto;
    }
}