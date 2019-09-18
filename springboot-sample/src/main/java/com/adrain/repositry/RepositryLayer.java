package com.adrain.repositry;

import com.adrain.exception.type.RepositryException;
import org.springframework.stereotype.Component;

/**
 * @Author AdrainHuang
 * @Date 2019/9/18 23:11
 **/
@Component
public class RepositryLayer {

	public void repositryEx(){
		throw new RepositryException("Repositry Layer Ex");
	}
}
