import controller.StudentOutput;
public class Main {
    public static void main(String[] args) {

        DIConfig diConfig = new DIConfig();
        StudentOutput test = diConfig.getStudentOutput();
        test.initialize();

        while(true) {
            test.welcome();
            String number = test.numberInput();
            switch (number) {
                case "1":
                    test.studentInfoInput();
                    break;
                case "2":
                    test.studentInfoSearch();
                    break;
                case "3":
                    test.studentInfoSort();
                    break;
                case "4":
                    test.studentExit();
                    break;
            }
            if(number.equals("4")) break;
        }

    }
}