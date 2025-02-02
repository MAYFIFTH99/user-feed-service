package fastcampus.feed.common.domain.exception;

import fastcampus.feed.common.controller.Response;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public Response<Void> handleIllegalArgumentException(IllegalArgumentException e) {
        log.error(e.getMessage());
        return Response.error(ErrorCode.INVALID_INPUT_VALUE);
    }

    @ExceptionHandler(IllegalStateException.class)
    public Response<Void> handleIllegalStateException(IllegalStateException e) {
        log.error(e.getMessage());
        return Response.error(ErrorCode.NOT_FOUND);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public Response<Void> handleNotFoundException(NotFoundException e){
        log.error(e.getMessage());
        return Response.error(ErrorCode.ENTITY_NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public Response<Void> handleException(Exception e) {
        return Response.error(ErrorCode.INTERNAL_SERVER_ERROR);
    }

}
