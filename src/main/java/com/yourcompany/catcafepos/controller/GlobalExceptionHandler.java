package com.yourcompany.catcafepos.controller;
import org.springframework.http.*; import org.springframework.web.bind.MethodArgumentNotValidException; import org.springframework.web.bind.annotation.*;
import java.util.*;
@ControllerAdvice public class GlobalExceptionHandler {
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<?> handleValidation(MethodArgumentNotValidException ex){
    Map<String,String> errs=new HashMap<>(); ex.getBindingResult().getFieldErrors().forEach(e->errs.put(e.getField(),e.getDefaultMessage()));
    return ResponseEntity.badRequest().body(errs);
  }
  @ExceptionHandler(Exception.class)
  public ResponseEntity<?> handleOther(Exception ex){ return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("error",ex.getClass().getSimpleName(),"message",ex.getMessage())); }
}
