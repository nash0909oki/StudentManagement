package reisetech.student.management.exception;

public class StudentUpdateDbException extends RuntimeException {

    public StudentUpdateDbException(String message) {
        super(message);
    }

    public StudentUpdateDbException(String message, Throwable cause) {
        super(message, cause);
    }
}
