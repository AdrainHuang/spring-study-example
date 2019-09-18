package com.adrain.service;

import com.adrain.exception.type.ServiceException;
import com.adrain.repositry.RepositryLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author AdrainHuang
 * @Date 2019/9/18 23:06
 **/
@Component
public class ServiceLayer {
	
	@Autowired
	private RepositryLayer repositryLayer;
	
	public void serviceEx(){
	  throw new ServiceException("Service Layer Ex");
	}
	
	public void serviceFine(){
		repositryLayer.repositryEx();
	}
}
