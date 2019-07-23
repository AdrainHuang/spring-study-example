package com.adrain.document;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * @Author AdrainHuang
 * @Date 2019/7/23 22:38
 **/
@Document(collection = "sequence")
@Data
public class SequenceId {
	@Id
	private String id;
	@Field("seq_id")
	private long seqId;
	@Field("coll_name")
	private String collName;
	
}