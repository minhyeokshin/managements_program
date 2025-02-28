package common;

public enum Ignore {
    URL(""),
    PASSWORD("");

    private String msg;

    Ignore(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }
}
