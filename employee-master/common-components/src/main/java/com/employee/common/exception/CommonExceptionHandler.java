/**
 * 
 */
package com.employee.common.exception;

import java.util.HashMap;
import java.util.Map;

import javax.validation.ValidationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.employee.common.exception.dto.IMessageDTO;
import com.employee.common.exception.dto.ValidationErrorMessageDTO;
import com.employee.common.exception.message.ErrorMessage;
import com.employee.common.exception.message.MessageType;
import com.employee.common.utils.ErrorCodes;

import javassist.NotFoundException;

/**
 *Common Exception Handler. This class handles all the excpetion being thrown
 */
@ControllerAdvice
public class CommonExceptionHandler {

	
	
    @ResponseStatus(code=HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorMessage> handleNotFoundException(NotFoundException ex) {
        ErrorMessage error = buildErrorMap(ErrorCodes.NOT_FOUND, ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
    
    @ResponseStatus(code=HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ErrorMessage> handleValidationException(ValidationException ex) {
        ErrorMessage error = buildErrorMap(ErrorCodes.VALIDATION_ERROR_CODE, ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
    
    
    @ResponseStatus(code=HttpStatus.NOT_FOUND)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> handleException(Exception ex) {
    	ErrorMessage error = buildErrorMap(ErrorCodes.NOT_FOUND, ex.getMessage());
    	return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    } 
    
	
    protected ErrorMessage buildErrorMap(ErrorCodes errorCode, String msg) {
		return buildErrorMap(errorCode, msg, "error");
	}

	protected ErrorMessage buildErrorMap(ErrorCodes errorCode, String msg, String fieldName) {
		Map<String, IMessageDTO> errors = new HashMap<>();
		IMessageDTO message = new ValidationErrorMessageDTO(errorCode, MessageType.ERROR, msg);
		errors.put(fieldName, message);
		return new ErrorMessage(errors);
	}
}
