package com.example.clinica_odontologica.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;



@ControllerAdvice
public class GlobalException {
    //al manejo de exception hay que hacerlo de manera individual
    @ExceptionHandler({ResourceNotFoundException.class})
    public ResponseEntity<String> tratamientoRNFE(ResourceNotFoundException rnfe){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("mensaje: "+rnfe.getMessage());
    }

    @ExceptionHandler({BadRequestException.class})
    public ResponseEntity<String> tratamientoBRE(BadRequestException bre){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("mensaje: "+bre.getMessage());
    }

    @ExceptionHandler({EmailAlreadyExistsException.class})
    public ResponseEntity<String> tratamientoBRE(EmailAlreadyExistsException bre){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("mensaje: "+bre.getMessage());
    }

    @ExceptionHandler({PasswordMismatchException.class})
    public ResponseEntity<String> tratamientoBRE(PasswordMismatchException bre){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("mensaje: "+bre.getMessage());
    }
    


}
