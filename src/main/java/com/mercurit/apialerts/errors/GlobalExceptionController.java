package com.mercurit.apialerts.errors;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionController extends ResponseEntityExceptionHandler{
	
	private final Logger log=LoggerFactory.getLogger(GlobalExceptionController.class);
	
	ErrorResponse error=new ErrorResponse();
	
	
	
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleException(final Exception exception,final HttpServletRequest request){
		error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		error.setApi(request.getRequestURI());
		log.info(request.getMethod());
		error.setError(exception.getMessage());
		log.error(exception.getStackTrace()+ " Exception.Class");
		return new ResponseEntity<>(error,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(NullExceptionError.class)
	public final ResponseEntity<Object> handleNullException(final NullExceptionError exception,final HttpServletRequest request){
		error.setStatus(HttpStatus.NOT_FOUND.value());
		error.setApi(request.getRequestURI());
		log.info(request.getMethod());
		error.setError(exception.getMessage());
		log.error(exception.getStackTrace()+ " NullException.Class");
		return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(CustomExceptionError.class)
	public final ResponseEntity<Object> handleCustomException(final CustomExceptionError exception,final HttpServletRequest request){
		error.setStatus(HttpStatus.BAD_REQUEST.value());
		error.setApi(request.getRequestURI());
		log.info(request.getMethod());
		error.setError(exception.getMessage());
		log.error(exception.getStackTrace()+ " CustomExceptionError.Class");
		return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
	}
	
	

}
