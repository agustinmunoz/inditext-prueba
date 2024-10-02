package com.agustin.munoz.inditex.exception;

import java.time.LocalDateTime;

import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.agustin.munoz.inditex.error.InditexErrorMessages;
import com.agustin.munoz.inditex.openapi.model.InditexErrorResponse;

/**
 * 
 * @author agust
 *
 */

@ControllerAdvice
public class InditexGlobalExceptionHandler extends ResponseEntityExceptionHandler {

	
	/**
	 * 
	 * agust
	 * @param ex
	 * @param headers
	 * @param status
	 * @param request
	 * @return
	 */
	@Override
	protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {

		InditexErrorResponse errors = new InditexErrorResponse();
		errors.setTimestamp(LocalDateTime.now().toString());
		errors.setMessage(ex.getMessage());
		errors.setError(InditexErrorMessages.BAD_REQUEST.getCode());

		return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);

	}

	
	/**
	 * 
	 * agust
	 * @param ex
	 * @param headers
	 * @param status
	 * @param request
	 * @return
	 */
	@Override
	protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers,
			HttpStatusCode status, WebRequest request) {

		InditexErrorResponse errors = new InditexErrorResponse();
		errors.setTimestamp(LocalDateTime.now().toString());
		errors.setMessage(ex.getMessage());
		errors.setError(InditexErrorMessages.BAD_REQUEST.getCode());

		return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);

	}

	
	/**
	 * 
	 * agust
	 * @param ex
	 * @return
	 * ResponseEntity<InditexErrorResponse>
	 */
	@ExceptionHandler(InditexException.class)
	public ResponseEntity<InditexErrorResponse> inditextException(InditexException ex) {
		InditexErrorResponse errors = new InditexErrorResponse();
		errors.setTimestamp(LocalDateTime.now().toString());
		errors.setMessage(ex.getMessage());
		errors.setError(ex.getInditexCode());

		return new ResponseEntity<InditexErrorResponse>(errors, HttpStatus.valueOf(ex.getStatus().value()));
	}

	
	/**
	 * 
	 * agust
	 * @param ex
	 * @return
	 * ResponseEntity<InditexErrorResponse>
	 */
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<InditexErrorResponse> customException(RuntimeException ex) {
		InditexErrorResponse errors = new InditexErrorResponse();
		errors.setTimestamp(LocalDateTime.now().toString());
		errors.setMessage(InditexErrorMessages.ERROR_INTERNO.getDescription());
		errors.setError(InditexErrorMessages.ERROR_INTERNO.getCode());

		return new ResponseEntity<>(errors, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
