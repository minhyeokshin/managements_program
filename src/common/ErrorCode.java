package common;

/**
 * 직원 관련 오류 코드 Enum
 */
public enum ErrorCode {
    EMPLOYEE_NOT_FOUND("해당 직원을 찾을 수 없습니다."),
    INVALID_EMPLOYEE_NUMBER("잘못된 직원 번호입니다."),
    INVALID_EMPLOYEE_NAME("잘못된 직원 이름입니다."),
    INVALID_EMPLOYEE_ROLE("잘못된 직급입니다."),
    INVALID_EMPLOYEE_SALARY("잘못된 급여 값입니다.");

    private final String text;

    ErrorCode(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}