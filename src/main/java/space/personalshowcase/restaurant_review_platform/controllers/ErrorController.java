package space.personalshowcase.restaurant_review_platform.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import space.personalshowcase.restaurant_review_platform.domain.entities.dtos.ErrorDto;
import space.personalshowcase.restaurant_review_platform.exceptions.BaseException;
import space.personalshowcase.restaurant_review_platform.exceptions.StorageException;

@RestController
@ControllerAdvice
@Slf4j
public class ErrorController {

    @ExceptionHandler(StorageException.class)
    public ResponseEntity<ErrorDto> handleStorageException(StorageException ex){
        log.error("Caught StorageException : "+ ex );
        ErrorDto errorDto = ErrorDto.builder()
                .message("Unable to save or retrieve resources at this time.")
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .build();

        return  new ResponseEntity<>(errorDto , HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<ErrorDto> handleBaseException(BaseException ex){
        log.error("Caught BaseException : "+ ex );
        ErrorDto errorDto = ErrorDto.builder()
                .message("An unexpected error occurs : ")
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .build();

        return  new ResponseEntity<>(errorDto , HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDto> handleException(Exception ex){
        log.error("Caught Exception : "+ ex );
        ErrorDto errorDto = ErrorDto.builder()
                .message("An unexpected error occurs : ")
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .build();

        return  new ResponseEntity<>(errorDto , HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
