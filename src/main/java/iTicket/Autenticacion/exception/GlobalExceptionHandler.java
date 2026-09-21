package iTicket.Autenticacion.exception;

import iTicket.Autenticacion.response.ErrorResponseDTO;
import iTicket.Autenticacion.utils.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RecursoNoEncontradoException.class)
    public ResponseEntity<ErrorResponseDTO> manejarNoEncontrado(RecursoNoEncontradoException e) {
        log.warn("Recurso no encontrado: " + e.getMessage());
        String errorCodeStr = e.getErrorCode() != null ? e.getErrorCode().name() : ErrorCode.WGLB404.name();
        return buildErrorResponse(HttpStatus.NOT_FOUND, errorCodeStr, e.getMessage());
    }

    @ExceptionHandler(OperacionInvalidaException.class)
    public ResponseEntity<ErrorResponseDTO> manejarOperacionInvalida(OperacionInvalidaException e) {
        log.warn("Operacion invalida: " + e.getMessage());
        String errorCodeStr = e.getErrorCode() != null ? e.getErrorCode().name() : ErrorCode.WGLB001.name();
        return buildErrorResponse(HttpStatus.BAD_REQUEST, errorCodeStr, e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDTO> manejarValidacion(MethodArgumentNotValidException e) {
        String mensajes = e.getBindingResult().getFieldErrors().stream()
                .map(fe -> "[" + fe.getField() + "] " + fe.getDefaultMessage())
                .collect(Collectors.joining(", "));
        log.warn("Validacion fallida: " + mensajes);
        return buildErrorResponse(HttpStatus.BAD_REQUEST, ErrorCode.WGLB001.name(), "Datos invalidos: " + mensajes);
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ErrorResponseDTO> manejarRutaInexistente(NoResourceFoundException e) {
        log.warn("Ruta inexistente: " + e.getResourcePath());
        return buildErrorResponse(HttpStatus.NOT_FOUND, ErrorCode.WGLB404.name(), "La ruta solicitada no existe");
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> manejarGeneral(Exception e) {
        log.error("Error inesperado: ", e);
        return buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, ErrorCode.WGLB500.name(), "Ocurrio un error inesperado en Autenticacion.");
    }

    private ResponseEntity<ErrorResponseDTO> buildErrorResponse(HttpStatus status, String errorCode, String message) {
        ErrorResponseDTO errorResponse = new ErrorResponseDTO(status.value(), errorCode, message);
        return ResponseEntity.status(status).body(errorResponse);
    }
}