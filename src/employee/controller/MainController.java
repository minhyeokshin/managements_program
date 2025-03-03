package employee.controller;

import java.util.Scanner;

/**
 * 직원 관리 시스템 메인 컨트롤러
 */
public class MainController {

    private final EmployeeCreateCont createController;
    private final EmployeeDeleteCont deleteController;
    private final EmployeeReadCont readController;
    private final EmployeeUpdateCont updateController;

    public MainController(EmployeeCreateCont createController, EmployeeDeleteCont deleteController,
                          EmployeeReadCont readController, EmployeeUpdateCont updateController) {
        this.createController = createController;
        this.deleteController = deleteController;
        this.readController = readController;
        this.updateController = updateController;
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
        System.out.println("\n====================================");
        System.out.println("\t[직원 관리 시스템]");
        System.out.println("====================================");
        System.out.println("1. 직원 생성");
        System.out.println("2. 직원 삭제");
        System.out.println("3. 직원 조회");
        System.out.println("4. 직원 수정");
        System.out.println("5. 종료");
    }

    private void createEmployee() {
    }

    private void deleteEmployee() {
    }

    private void readEmployee() {
    }

    private void updateEmployee() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("수정할 직원의 번호를 입력하세요: ");
        int eno = Integer.parseInt(scanner.nextLine());

        try {
            updateController.update(eno);
            System.out.println("직원 정보가 성공적으로 업데이트되었습니다.");
        } catch (Exception e) {
            System.out.println("직원 정보 업데이트 중 오류가 발생했습니다: " + e.getMessage());
        }

}}
