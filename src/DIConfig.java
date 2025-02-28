import student.controller.StudentOutput;
import student.controller.StudentOutputImp;
import student.repository.StudentDBIOUseFileIO;
import student.repository.StudentManager;
import student.service.*;

/**
 * 전체 프로젝트 의존관계 주입 클래스
 */
public class DIConfig {

    /**
     * StudentIO
     * @return StudentIO 구현체 반환 캐시데이터 , dbio
     */
    public StudentIOImp getStudentIO(){
        return new StudentIOImp(StudentManager.getInstance(),new StudentDBIOUseFileIO());
    }

    /**
     * 학생 검색 구현체 주입
     * @return SearchStudentIMP
     */
    public SearchStudent getSearchStudent(){
        return new SearchStudentImp(getStudentIO());
    }

    /**
     * 학생 정렬 구현체 주입
     * @return SortedStudentIMP
     */
    public SortedStudent getSortStudent(){
        return new SortedStudentImp(getStudentIO());
    }

    /**
     * 학생 데이터 입력 구현체 주입
     * @return StudentInputImp
     */
    public StudentInput getStudentInput(){
        return new StudentInputImp(getStudentIO());
    }

    /**
     * Controller
     * @return StudentOutputImp
     */
    public StudentOutput getStudentOutput(){
        return new StudentOutputImp(getSearchStudent(),getSortStudent(),getStudentInput());
    }

}
