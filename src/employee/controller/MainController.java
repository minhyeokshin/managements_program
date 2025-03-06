package employee.controller;

import common.EmployeeText;
import common.ErrorCode;
import common.ValidCheck;
import employee.DIConfig;
import employee.dto.EmployeeDto;

import java.util.Scanner;

/**
 * 직원 관리 시스템 메인 컨트롤러
 */
public class MainController {

    private final EmployeeCreateCont createController;
    private final EmployeeDeleteCont deleteController;
    private final EmployeeReadCont readController;
    private final EmployeeUpdateCont updateController;
    private final SalaryController salaryController;
    private final ValidCheck validCheck;

    public MainController(EmployeeCreateCont createController, EmployeeDeleteCont deleteController,
                          EmployeeReadCont readController, EmployeeUpdateCont updateController,SalaryController salaryController ,ValidCheck validCheck ) {
        this.createController = createController;
        this.deleteController = deleteController;
        this.readController = readController;
        this.updateController = updateController;
        this.salaryController = salaryController;
        this.validCheck = validCheck;
    }

    /**
     * 시스템 시작 메서드
     */
    public void start() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            printMenu();
            System.out.print(EmployeeText.ENTER_CHOICE.getText());
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    createEmployee();
                    break;
                case "2":
                    deleteEmployee();
                    break;
                case "3":
                    readEmployee();
                    break;
                case "4":
                    updateEmployee();
                    break;
                case "5":
                    salaryEmployee();
                    break;
                case "6":
                    System.out.println(EmployeeText.EXIT_MESSAGE.getText());
                    return;
                default:
                    System.out.println(EmployeeText.INVALID_CHOICE_MAIN.getText());
            }
        }
    }

    /**
     * 메뉴 출력
     */
    private void printMenu() {
        System.out.println(EmployeeText.MENU_HEADER.getText());
        System.out.println(EmployeeText.MENU_BORDER.getText());
        System.out.println(EmployeeText.MENU_OPTIONS.getText());
    }

    private void createEmployee() {
        createController.createrun();
    }

    private void deleteEmployee() {
        deleteController.delete(); // 삭제할 직원 번호를 컨트롤러 내부에서 처리
    }

    private void readEmployee() {
        readController.Read();
    }

    private void updateEmployee() {
        updateController.update(); // 직원 번호를 컨트롤러에서 입력받음
    }

    private void salaryEmployee() {
        salaryController.payRaise(); // 직원 번호를 컨트롤러에서 입력받음
    }

    public static void main(String[] args) {
        MainController mainController = new DIConfig().mainController();
        mainController.start();
    }
}
