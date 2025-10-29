package space.personalshowcase.restaurant_review_platform.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import space.personalshowcase.restaurant_review_platform.domain.dtos.ErrorDto;
import space.personalshowcase.restaurant_review_platform.exceptions.BaseException;
import space.personalshowcase.restaurant_review_platform.exceptions.RestaurantNotFoundException;
import space.personalshowcase.restaurant_review_platform.exceptions.StorageException;

import java.util.stream.Collectors;

@RestController
@ControllerAdvice
@Slf4j
public class ErrorController {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDto> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex)
    {
        log.error("MethodArgumentNotValidException : " + ex);

        String errorMessage = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() +": "+ error.getDefaultMessage())
                .collect(Collectors.joining(", "));

        ErrorDto errorDto = ErrorDto.builder()
                .message(errorMessage)
                .status(HttpStatus.BAD_REQUEST.value())
                .build();

        return new ResponseEntity<>(errorDto , HttpStatus.BAD_REQUEST);
    }

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

    @ExceptionHandler(RestaurantNotFoundException.class)
    public ResponseEntity<ErrorDto> handleRestaurantNotFoundException(RestaurantNotFoundException ex)
    {
        log.error("Caught RestaurantNotFoundException: " , ex);
        ErrorDto errorDto = ErrorDto.builder()
                .status(HttpStatus.NOT_FOUND.value())
                .message("The Specified restaurant wasn't found.")
                .build();
        return new ResponseEntity<>(errorDto, HttpStatus.NOT_FOUND);
    }

}
