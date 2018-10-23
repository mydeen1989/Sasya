package com.sasya.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class ExceptionAdviceComponent {

    @ExceptionHandler(SasyaException.class)
    public ResponseEntity exceptionHanler(SasyaException e, HttpServletRequest request) {
        ObjectNode rootNode = new ObjectMapper().createObjectNode();
        rootNode.put("Message", e.getMessage());
        rootNode.put("Path", request.getRequestURL().toString());
        rootNode.put("Status", e.getHttpStatus().toString());
        return ResponseEntity.status(e.getHttpStatus()).body(rootNode);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity validationExceptionHandler(MethodArgumentNotValidException ex,HttpServletRequest request){
        {
            ObjectNode rootNode = new ObjectMapper().createObjectNode();
            rootNode.put("Message", "VALIDATION FAILED");
            rootNode.put("Path", request.getRequestURL().toString());
            rootNode.put("Status", "BAD_REQUEST");
            ArrayNode fieldErrorNode = rootNode.putArray("FieldErrors");
            List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
            fieldErrors.forEach(fieldError -> {
                ObjectNode field = JsonNodeFactory.instance.objectNode();
                field.put(fieldError.getField(),fieldError.getDefaultMessage());
                fieldErrorNode.add(field);
            });
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(rootNode);
        }
    }
}
