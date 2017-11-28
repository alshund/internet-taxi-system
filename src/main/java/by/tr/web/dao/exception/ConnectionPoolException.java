package by.tr.web.dao.exception;

public class ConnectionPoolException extends Exception {
    private static final long serialVersionUID = -6004769569488512775L;

    public ConnectionPoolException() {
        super();
    }

    public ConnectionPoolException(String message) {
        super(message);
    }

    public ConnectionPoolException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConnectionPoolException(Throwable cause) {
        super(cause);
    }
}
