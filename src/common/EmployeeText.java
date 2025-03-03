package common;

/**
 * 직원 관련 메시지 텍스트 Enum
 */
public enum EmployeeText {
    UPDATE_PROMPT("직원 정보를 업데이트합니다."),
    CHOOSE_UPDATE_OPTION("변경할 정보를 선택하세요:\n1. 이름 | 2. 직급 | 3. 급여 | 4. 입사연도 | 5. 입사월 | 6. 입사일 | 7. 부서 번호 | 8. 취소"),
    ENTER_CHOICE("선택: "),
    ENTER_NAME("이름을 입력하세요: "),
    ENTER_ROLE("직급을 입력하세요: "),
    ENTER_SALARY("급여를 입력하세요: "),
    ENTER_ENTRY_YEAR("입사 연도를 입력하세요: "),
    ENTER_ENTRY_MONTH("입사 월을 입력하세요: "),
    ENTER_ENTRY_DAY("입사 일을 입력하세요: "),
    ENTER_SECTION_NUMBER("부서 번호를 입력하세요: "),
    UPDATE_CANCELLED("업데이트가 취소되었습니다."),
    INVALID_CHOICE("잘못된 선택입니다. 업데이트가 취소되었습니다.");

    private final String text;

    EmployeeText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}

