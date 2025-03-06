package common;

public enum ErrorCode {

    DB_CREATE_ERROR("[DB] 잘못된 직원 정보입니다."),
    DB_READ_ONE_ERROR("[DB] 해당 직원은 존재하지 않습니다."),
    DB_READ_ALL_ERROR("[DB] 직원 정보를 가져올 수 없습니다."),
    DB_UPDATE_ERROR("[DB] 직원 정보를 수정할 수 없습니다."),
    DB_NO_UPDATE("[DB] 수정할 직원 정보가 없습니다."),
    DB_DELETE_ERROR("[DB] 직원 정보를 삭제할 수 없습니다."),
    DB_NO_DELETE("[DB] 삭제할 직원 정보가 없습니다."),
    DB_UPDATE_SALARY_ERROR("[DB] 급여 변경이 제한됩니다."),
    DB_UPDATE_SALARY_HISTORY_ERROR("[DB] 급여 변경 이력을 불러올수 없습니다."),


    EMPLOYEE_NOT_FOUND("해당 직원을 찾을 수 없습니다."),
    INVALID_EMPLOYEE_NUMBER("잘못된 직원 번호입니다."),
    INVALID_EMPLOYEE_NAME("잘못된 직원 이름입니다."),
    INVALID_EMPLOYEE_ROLE("잘못된 직급입니다."),
    INVALID_EMPLOYEE_SALARY("잘못된 급여 값입니다."),
    UPDATE_FAILED("직원 정보 업데이트에 실패했습니다."),
    DELETE_FAILED("직원 삭제에 실패했습니다."),
    CREATE_FAILED("직원 생성에 실패했습니다."),
    EMPLOYEE_NUMBER_NOT_FOUND("입력한 직원 번호가 존재하지 않습니다."),
    ERROR_INPUT_NUM("숫자를 입력하세요."),
    ERROR_EMPTY_LIST("[service]직원 리스트 조회에 실패했습니다."),
    ERROR_EMPTY_HISTORY_LIST("해당 직원은 월급 인상 기록이 없습니다.");


    private final String text;

    ErrorCode(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}

