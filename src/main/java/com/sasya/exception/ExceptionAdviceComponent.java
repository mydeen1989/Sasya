package com.sasya.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ExceptionAdviceComponent {

    @ExceptionHandler(SasyaException.class)
    public ResponseEntity exceptionHanler(SasyaException e, HttpServletRequest request) {
        ObjectNode rootNode = new ObjectMapper().createObjectNode();
        rootNode.put("Message", e.getMessage());
        rootNode.put("Path", request.getRequestURL().toString());
        rootNode.put("Status", "Internal Server Error");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(rootNode);
    }
}
