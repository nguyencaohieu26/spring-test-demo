package com.example.sping.validation.demo.controller;

import com.example.sping.validation.demo.response.HTTPResponseObject;
import com.example.sping.validation.demo.response.HTTPResponseObjectBuilder;
import com.example.sping.validation.demo.service.TranslationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

import static com.example.sping.validation.demo.constants.TranslationCode.ERR_INPUT_VALIDATION_MSG;

@RestControllerAdvice
@AllArgsConstructor
public class ApplicationExceptionHandler {

    private final TranslationService translationService;

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<HTTPResponseObject> handleInvalidArgumentException(MethodArgumentNotValidException ex){
        Map<String,String> errorMap = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(err -> errorMap.put(err.getField(),err.getDefaultMessage()));

        return new ResponseEntity<>(new HTTPResponseObjectBuilder()
                .withMessage(translationService.getTranslation(ERR_INPUT_VALIDATION_MSG))
                .addData("validate",errorMap)
                .build(),HttpStatus.BAD_REQUEST);
    }
}
