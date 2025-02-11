import controller.StudentOutput;
import controller.StudentOutputImp;
import repository.StudentManager;
import service.*;

public class DIConfig {
    public StudentIOImp getStudentIO(){
        return new StudentIOImp(StudentManager.getInstance());
    }

    public SearchStudent getSearchStudent(){
        return new SearchStudentImp(getStudentIO());
    }

    public SortedStudent getSortStudent(){
        return new SortedStudentImp(getStudentIO());
    }

    public StudentInput getStudentInput(){
        return new StudentInputImp(getStudentIO());
    }

    public StudentOutput getStudentOutput(){
        return new StudentOutputImp(getSearchStudent(),getSortStudent(),getStudentInput());
    }
}
