import employee.controller.MainController;
import student.controller.StudentOutput;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        DIConfig diConfig = new DIConfig();
        MainController employee = diConfig.mainController();
        StudentOutput student = diConfig.getStudentOutput();

        System.out.println("1.직원관리 2.학생관리");
        System.out.printf("입력 : ");
        int choice = in.nextInt();
        in.nextLine();
        switch (choice){
            case 1:
                employee.start();
                break;
            case 2:
                student.start();
                break;
            default:
                System.out.println("오류 , 시스템종료");
                break;
        }

    }

}