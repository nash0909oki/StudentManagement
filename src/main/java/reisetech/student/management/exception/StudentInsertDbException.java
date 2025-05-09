package reisetech.student.management.exception;

public class StudentInsertDbException extends RuntimeException {

    public StudentInsertDbException(String message) {
        super(message);
    }

    public StudentInsertDbException(String message, Throwable cause) {
        super(message, cause);
    }

}
