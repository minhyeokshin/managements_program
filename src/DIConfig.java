import controller.StudentOutput;
import controller.StudentOutputImp;
import repository.StudentDBIO;
import repository.StudentDBIOUseFileIO;
import repository.StudentManager;
import service.*;

public class DIConfig {


    public StudentIOImp getStudentIO(){
        setDbioAtManager();
        return new StudentIOImp(StudentManager.getInstance());
    }

    public void setDbioAtManager(){
        StudentManager.setStudentDBIO(new StudentDBIOUseFileIO());
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
