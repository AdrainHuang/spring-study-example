package com.adrain.exception.type;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author AdrainHuang
 * @Date 2019/9/18 22:05
 **/
@Getter
@Setter
public class BaseException extends RuntimeException{
	protected String code;
	protected String message;
	
	public BaseException(String message) {
		this.message = message;
	}
	
	public BaseException() {
	}
}
