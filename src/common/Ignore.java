package common;

public enum Ignore {
    URL("jdbc:mysql://127.0.0.1:3306/employeedb?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true"),
    PASSWORD("ssg6Employee");
//    PASSWORD("employee1234");

    private String msg;

    Ignore(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }
}
