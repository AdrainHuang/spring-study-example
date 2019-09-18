package com.adrain.exception;

import com.adrain.exception.type.ControllerException;
import com.adrain.exception.type.RepositryException;
import com.adrain.exception.type.ServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

/**
 * @Author AdrainHuang
 * @Date 2019/9/18 22:15
 **/
@ControllerAdvice
public class GlobalExceptinHandler {


	@ExceptionHandler(ControllerException.class)
	public final ResponseEntity<Object> handlerControllerException(ControllerException ex, WebRequest request){
		return new ResponseEntity<Object>(ex.getMessage(), HttpStatus.OK);
	}
	
	@ExceptionHandler(ServiceException.class)
	public final ResponseEntity<Object> handlerServiceException(ServiceException ex, WebRequest request){
		return new ResponseEntity<Object>(ex.getMessage(), HttpStatus.OK);
	}
	
	@ExceptionHandler(RepositryException.class)
	public final ResponseEntity<Object> handlerRepositryException(RepositryException ex, WebRequest request){
		return new ResponseEntity<Object>(ex.getMessage(), HttpStatus.OK);
	}
}
