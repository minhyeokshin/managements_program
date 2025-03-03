package employee.controller;

import common.EmployeeText;
import common.ErrorCode;
import common.ValidCheck;

import java.util.Scanner;

/**
 * 직원 관리 시스템 메인 컨트롤러
 */
public class MainController {

    private final EmployeeCreateCont createController;
    private final EmployeeDeleteCont deleteController;
    private final EmployeeReadCont readController;
    private final EmployeeUpdateCont updateController;
    private final ValidCheck validCheck;

    public MainController(EmployeeCreateCont createController, EmployeeDeleteCont deleteController,
                          EmployeeReadCont readController, EmployeeUpdateCont updateController,  ValidCheck validCheck ) {
        this.createController = createController;
        this.deleteController = deleteController;
        this.readController = readController;
        this.updateController = updateController;
        this.validCheck = validCheck;
    }

    /**
     * 시스템 시작 메서드
     */
    public void start() {

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
    }

    private void deleteEmployee() {
    }

    private void readEmployee() {
    }

    private void updateEmployee() {
        Scanner scanner = new Scanner(System.in);
        int eno = validCheck.getValidEmployeeNumber(scanner);

        try {
            if (readController.ReadOne(eno) == null) {
                System.out.println(ErrorCode.EMPLOYEE_NUMBER_NOT_FOUND.getText());
                return;
            }
            updateController.update(eno);
            System.out.println(EmployeeText.UPDATE_SUCCESS.getText());
        } catch (Exception e) {
            System.out.println(ErrorCode.EMPLOYEE_NOT_FOUND.getText() + ": " + e.getMessage());
        }
    }
}
