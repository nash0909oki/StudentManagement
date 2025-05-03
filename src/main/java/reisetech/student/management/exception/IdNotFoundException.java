package reisetech.student.management.exception;

public class IdNotFoundException extends RuntimeException {

    private final String id;

    public IdNotFoundException(String message, String id) {
        super(message);
        this.id = id;
    }

    public IdNotFoundException(String message, Throwable cause, String id) {
        super(message, cause);
        this.id = id;
    }

    public String getId() {
        return id;
    }
}

