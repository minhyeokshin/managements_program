package exception;

import common.ErrorCode;

public class EmployeeException extends RuntimeException{
    private final ErrorCode error;

    public EmployeeException(ErrorCode error) {
        super(error.getText());
        this.error = error;
    }
}
