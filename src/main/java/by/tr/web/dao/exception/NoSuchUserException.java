package by.tr.web.dao.exception;

public class NoSuchUserException extends Exception {
    private static final long serialVersionUID = 5445056746444259207L;

    public NoSuchUserException() {
    }

    public NoSuchUserException(String message) {
        super(message);
    }

    public NoSuchUserException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoSuchUserException(Throwable cause) {
        super(cause);
    }
}
