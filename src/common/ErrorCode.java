package common;

public enum ErrorCode {

    DB_CREATE_ERROR("[DB] 잘못된 직원 정보입니다."),
    DB_READ_ONE_ERROR("[DB] 해당 직원은 존재하지 않습니다."),
    DB_READ_ALL_ERROR("[DB] 직원 정보를 가져올 수 없습니다."),
    DB_UPDATE_SALARY_ERROR("[DB] 급여 변경이 제한됩니다."),
    DB_UPDATE_SALARY_HISTORY_ERROR("[DB] 급여 변경 이력을 불러올수 없습니다."),



    ;
    private final String text;

    ErrorCode(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
