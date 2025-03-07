package common;


import java.util.Scanner;

/**
 * 직원 정보 유효성 검사 클래스
 */
public class ValidCheck {
    private static final String EMPLOYEE_NUMBER_REGEX = "^[1-9][0-9]*$";
    private static final String EMPLOYEE_NAME_REGEX = "^[a-zA-Z가-힣]{2,10}$";
    private static final String EMPLOYEE_ROLE_REGEX = "^[a-zA-Z가-힣]{2,20}$";
    private static final String EMPLOYEE_SALARY_REGEX = "^\\d{1,8}$";
    public final String NUMBER_REGEX = "^[1-9][0-9]*$";
    public final String NAME_INPUT_REGEX = "^[가-힣]{2,10}$";
    public final String ENTERYEAR_REGEX = "^[1-9][0-9]{0,3}$";
    public final String ENTERMONTH_REGEX = "^(1[0-2]|[1-9])$";
    public final String ENTERDAY_REGEX = "^(3[0-1]|[1-2][0-9]|[1-9])$";
    public final String SECINPUT_REGEX = "^(1[0-4][0-9])$";


    /**
     * 직원 번호 입력 및 유효성 검사
     */
    public int getValidEmployeeNumber(Scanner scanner) {
        int eno;
        while (true) {
            try {
                String input = scanner.nextLine();
                eno = Integer.parseInt(input);
                isValidEmployeeNumber(eno);
                return eno;
            } catch (NumberFormatException e) {
                System.out.println(ErrorCode.INVALID_EMPLOYEE_NUMBER.getText());
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * 직원 삭제용 번호 입력 및 유효성 검사
     */
    public int getValidEmployeeNumberForDelete(Scanner scanner) {
        System.out.print(EmployeeText.DELETE_EMPLOYEE_NUMBER.getText()); // 삭제 메시지 출력
        return getValidEmployeeNumber(scanner);
    }

    /**
     * 직원 업데이트용 번호 입력 및 유효성 검사
     */
    public int getValidEmployeeNumberForUpdate(Scanner scanner) {
        System.out.print(EmployeeText.UPDATE_EMPLOYEE_NUMBER.getText()); // 업데이트 메시지 출력
        return getValidEmployeeNumber(scanner);
    }
    /**
     * 직원 번호 유효성 검사
     */
    public void isValidEmployeeNumber(Integer eno) {
        if (eno == null || !String.valueOf(eno).matches(EMPLOYEE_NUMBER_REGEX)) {
            throw new IllegalArgumentException(ErrorCode.INVALID_EMPLOYEE_NUMBER.getText());
        }
    }

    /**
     * 직원 이름 유효성 검사
     */
    public void isValidEmployeeName(String name) {
        if (name == null || !name.matches(EMPLOYEE_NAME_REGEX)) {
            throw new IllegalArgumentException(ErrorCode.INVALID_EMPLOYEE_NAME.getText());
        }
    }

    /**
     * 직원 직급 유효성 검사
     */
    public void isValidEmployeeRole(String role) {
        if (role == null || !role.matches(EMPLOYEE_ROLE_REGEX)) {
            throw new IllegalArgumentException(ErrorCode.INVALID_EMPLOYEE_ROLE.getText());
        }
    }

    /**
     * 직원 급여 유효성 검사
     */
    public void isValidEmployeeSalary(String salary) {
        if (salary == null || !salary.matches(EMPLOYEE_SALARY_REGEX)) {
            throw new IllegalArgumentException(ErrorCode.INVALID_EMPLOYEE_SALARY.getText());
        }
    }


}
