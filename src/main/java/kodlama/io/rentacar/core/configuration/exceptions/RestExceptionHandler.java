package kodlama.io.rentacar.core.configuration.exceptions;

import kodlama.io.rentacar.core.exceptions.BusinessException;
import kodlama.io.rentacar.core.utils.results.ExceptionResult;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice // controllerdan gelebilecek her hatayı dinler yakalarsam çalıştırırım diyor
public class RestExceptionHandler {
    @ExceptionHandler // hata yönetimini yapıyoruz
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ExceptionResult<BusinessException> handleBusinessException(BusinessException exception) {
        //stacktrace: hata neyin nerede olduğunu gösteriyor galiba :)
        return new ExceptionResult<>(BusinessException.class, exception.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ExceptionResult<RuntimeException> handleRuntimeException(RuntimeException exception) {
        return new ExceptionResult<>(RuntimeException.class, exception.getMessage());
    }

}
