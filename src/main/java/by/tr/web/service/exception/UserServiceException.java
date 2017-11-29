package by.tr.web.service.exception;

public class UserServiceException extends Exception {
    private static final long serialVersionUID = -60278457604503200L;

    public UserServiceException() {
    }

    public UserServiceException(String message) {
        super(message);
    }

    public UserServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserServiceException(Throwable cause) {
        super(cause);
    }
}
