package com.adrain.exception.type;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author AdrainHuang
 * @Date 2019/9/18 22:08
 **/
@Getter
@Setter
public class RepositryException extends BaseException{
	public RepositryException(String message) {
		super(message);
	}
	
	public RepositryException() {
	}
}
