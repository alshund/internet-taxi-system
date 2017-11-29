package by.tr.web.dao.exception;

public class SQLUserDAOException extends Exception {
    private static final long serialVersionUID = 948834165251013750L;

    public SQLUserDAOException() {
    }

    public SQLUserDAOException(String message) {
        super(message);
    }

    public SQLUserDAOException(String message, Throwable cause) {
        super(message, cause);
    }

    public SQLUserDAOException(Throwable cause) {
        super(cause);
    }
}
