package edu.idat.eventosvirtuales.exception;

import edu.idat.eventosvirtuales.utils.CustomResponse;
import edu.idat.eventosvirtuales.utils.Global;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GenericExceptionHandler {
    @ExceptionHandler(Exception.class)
    public CustomResponse genericException(Exception ex) {
        return new CustomResponse("exception", -1, Global.OPERACION_ERRONEA, ex.getMessage());
    }
}
