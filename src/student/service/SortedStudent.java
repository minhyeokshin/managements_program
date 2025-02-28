package student.service;

/**
 * 학생 정보를 다양한 기준으로 정렬하는 기능을 제공하는 인터페이스
 */
public interface SortedStudent {

    /**
     * 총점 기준으로 학생을 정렬 (총점이 높은 순, 이름 오름차순)
     */
    void SortedByTotal();

    /**
     * 평균 점수 기준으로 학생을 정렬 (평균이 높은 순, 학번 오름차순)
     */
    void SortedByAverage();

    /**
     * 학생 이름 기준으로 정렬 (이름 오름차순, 학번 오름차순)
     */
    void SortedByName();

    /**
     * 학번 기준으로 정렬 (학번 오름차순, 이름 오름차순)
     */
    void SortedBySnoNumber();

    /**
     * 학점 기준으로 정렬 (학점 오름차순, 이름 오름차순)
     */
    void SortedByGrade();
}
