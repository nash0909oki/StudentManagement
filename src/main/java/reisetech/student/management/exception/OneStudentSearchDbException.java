package reisetech.student.management.exception;

public class OneStudentSearchDbException extends RuntimeException {

    public OneStudentSearchDbException(String message) {
        super(message);
    }

    public OneStudentSearchDbException(String message, Throwable cause) {
        super(message, cause);
    }
}
