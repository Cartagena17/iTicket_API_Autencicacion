package iTicket.Autenticacion.exception;

import iTicket.Autenticacion.utils.ErrorCode;

public class RecursoNoEncontradoException extends RuntimeException {
    private final ErrorCode errorCode;

    public RecursoNoEncontradoException(ErrorCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}