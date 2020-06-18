package edu.idat.eventosvirtuales.exception;

import edu.idat.eventosvirtuales.utils.CustomResponse;
import edu.idat.eventosvirtuales.utils.Global;
import org.hibernate.JDBCException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class SpecificExceptionHandler {
    @ExceptionHandler(JDBCException.class)
    public CustomResponse sqlException(JDBCException ex) {
        return new CustomResponse("sql-exception", -1, Global.OPERACION_ERRONEA, ex.getMessage());
    }
}
