package reisetech.student.management.exception;

public class StudentSearchDbException extends RuntimeException {

    public StudentSearchDbException(String message) {
        super(message);
    }

    public StudentSearchDbException(String message, Throwable cause) {
        super(message, cause);
    }

}
