package com.adrain.controller;

import com.adrain.exception.type.ControllerException;
import com.adrain.service.ServiceLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author AdrainHuang
 * @Date 2019/9/18 23:05
 **/
@RestController
public class ControllerLayer {
	
	@Autowired
	private ServiceLayer serviceLayer;
	
	
	@GetMapping("/ex/0")
	public Object ex0(){
		throw new ControllerException("Controller Layer");
	}
	
	@GetMapping("/ex/1")
	public Object ex1(){
		serviceLayer.serviceEx();
		return "1";
	}
	
	@GetMapping("/ex/2")
	public Object ex2(){
		serviceLayer.serviceFine();
		return "2";
	}
	
	@GetMapping("/ex/3")
	public Object ex3(){
		try{
			serviceLayer.serviceEx();
		}catch (Exception e){
			return "3e";
		}
		return "3";
	}
	
	@GetMapping("/ex/4")
	public Object ex4(){
		try{
			serviceLayer.serviceFine();
		}catch (Exception e){
			return "4e";
		}
		return "4";
	}
}
