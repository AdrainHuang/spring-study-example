package com.adrain.document;

import com.adrain.annotation.GeneratedValue;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @Author AdrainHuang
 * @Date 2019/7/23 22:48
 **/
@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {
	@GeneratedValue
	@Id
	private long id;
	private String name;
}
