package edu.idat.eventosvirtuales.exception;

import edu.idat.eventosvirtuales.utils.GenericResponse;
import edu.idat.eventosvirtuales.utils.Global;
import org.hibernate.JDBCException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static edu.idat.eventosvirtuales.utils.Global.OPERACION_ERRONEA;
import static edu.idat.eventosvirtuales.utils.Global.RPTA_WARNING;

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class SpecificExceptionHandler {
    @ExceptionHandler(JDBCException.class)
    public GenericResponse sqlException(JDBCException ex) {
        return new GenericResponse("sql-exception", -1, Global.OPERACION_ERRONEA, ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public GenericResponse validException(MethodArgumentNotValidException ex) {
        return new GenericResponse("valid-exception", RPTA_WARNING, OPERACION_ERRONEA, ex.getMessage());
    }
}
