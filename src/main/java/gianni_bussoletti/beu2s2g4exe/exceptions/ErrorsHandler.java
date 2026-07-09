package gianni_bussoletti.beu2s2g4exe.exceptions;

import gianni_bussoletti.beu2s2g4exe.payloads.ErrorsDTO;
import gianni_bussoletti.beu2s2g4exe.payloads.ErrorsWithListsDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ErrorsHandler {

    @ExceptionHandler(AuthorAlreadyPresentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorsDTO handleAuthorPresent(AuthorAlreadyPresentException ex) {
        return new ErrorsDTO(ex.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler(AuthorNotExistException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorsDTO handleAuthorNotFound(AuthorNotExistException ex) {
        return new ErrorsDTO(ex.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorsDTO handleGenericException(Exception ex) {
        ex.printStackTrace();
        return new ErrorsDTO("C'è stato un errore lato server", LocalDateTime.now());
    }

    @ExceptionHandler(ValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorsWithListsDTO handleValidationError(ValidationException ex) {
        return new ErrorsWithListsDTO(ex.getMessage(), LocalDateTime.now(), ex.getErrorsList());
    }

}
