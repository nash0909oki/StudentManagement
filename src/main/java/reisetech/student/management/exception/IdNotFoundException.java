package reisetech.student.management.exception;

public class IdNotFoundException extends RuntimeException {

    private final String id;
    private final String code;

    public IdNotFoundException(String message, String id) {
        super(message);
        this.id = id;
        this.code = "ID_NOT_FOUND";
    }

    public IdNotFoundException(String message, Throwable cause, String id) {
        super(message, cause);
        this.id = id;
        this.code = "ID_NOT_FOUND";
    }

    public String getId() {
        return id;
    }

    public String getCode() {
        return code;
    }
}

