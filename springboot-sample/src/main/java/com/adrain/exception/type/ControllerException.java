package com.adrain.exception.type;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author AdrainHuang
 * @Date 2019/9/18 22:07
 **/
@Getter
@Setter
public class ControllerException extends BaseException {
	public ControllerException(String message) {
		super(message);
	}
	
	public ControllerException() {
	}
}
