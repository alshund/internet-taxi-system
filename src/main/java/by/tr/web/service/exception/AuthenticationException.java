package by.tr.web.service.exception;

public class AuthenticationException extends Exception {
    private static final long serialVersionUID = -3856636790133726037L;

    public AuthenticationException() {
    }

    public AuthenticationException(String message) {
        super(message);
    }

    public AuthenticationException(String message, Throwable cause) {
        super(message, cause);
    }

    public AuthenticationException(Throwable cause) {
        super(cause);
    }
}
