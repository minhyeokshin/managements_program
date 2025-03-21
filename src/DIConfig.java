import common.ValidCheck;
import employee.controller.*;
import employee.dto.EmployeeDto;
import employee.repository.*;
import employee.service.*;
import employee.service.pay.PayRaiseRate;
import employee.service.pay.PayRateManager;
import employee.service.pay.PayRateSecretary;
import employee.service.pay.PayRateStaff;
import student.controller.StudentOutput;
import student.controller.StudentOutputImp;
import student.repository.StudentDBIOUseFileIO;
import student.repository.StudentManager;
import student.service.*;

import java.util.Locale;

/**
 * 전체 프로젝트 의존관계 주입 클래스
 */
public class DIConfig {

    private final ValidCheck validCheck = new ValidCheck();

    private final EmployeeCreateRepo employeeCreateRepo = new EmployeeCreateRepoImp();
    private final EmployeeDeleteRepo employeeDeleteRepo = new EmployeeDeleteRepoImp();
    private final EmployeeReadRepo employeeReadRepo = new EmployeeReadRepoImp();
    private final EmployeeUpdateRepo employeeUpdateRepo = new EmployeeUpdateRepoImp();
    private final SalaryRepository salaryRepository = new SalaryRepositoryImp();

    private final EmployeeCreateService employeeCreateService = new EmployeeCreateServiceImp(employeeCreateRepo, employeeReadRepo);
    private final EmployeeDeleteService employeeDeleteService = new EmployeeDeleteServiceImp(employeeDeleteRepo, employeeReadRepo);
    private final EmployeeReadService employeeReadService = new EmployeeReadServiceImp(employeeReadRepo);
    private final EmployeeUpdateService employeeUpdateService = new EmployeeUpdateServiceImp(employeeUpdateRepo, employeeReadRepo);
    private final EmployeeSalaryService employeeSalaryService = new EmployeeSalaryServiceImp(employeeReadRepo, employeeUpdateRepo, salaryRepository);

    private final EmployeeCreateCont employeeCreateCont = new EmployeeCreateContImp(employeeCreateService, employeeReadService);
    private final EmployeeDeleteCont employeeDeleteCont = new EmployeeDeleteContImp(employeeDeleteService, employeeReadService, validCheck);
    private final EmployeeReadCont employeeReadCont = new EmployeeReadContImp(employeeReadService, validCheck);
    private final EmployeeUpdateCont employeeUpdateCont = new EmployeeUpdateContImp(employeeUpdateService, employeeReadService, validCheck);
    private final SalaryController salaryController = new SalaryControllerImp(employeeSalaryService,employeeReadService,validCheck);


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


    /**
     * EmployeeController
     * @return MainController
     */
    public MainController mainController(){
        return new MainController(
                employeeCreateCont,
                employeeDeleteCont,
                employeeReadCont,
                employeeUpdateCont,
                salaryController,
                validCheck);
    }
}
