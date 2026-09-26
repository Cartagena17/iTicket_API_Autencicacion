package iTicket.Autenticacion.exception;

import iTicket.Autenticacion.utils.ErrorCode;

public class OperacionInvalidaException extends RuntimeException {
    private final ErrorCode errorCode;

    public OperacionInvalidaException(ErrorCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}