package com.adrain.util;

import com.adrain.MongoExampleApplication;
import com.adrain.document.SequenceId;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

/**
 * @Author AdrainHuang
 * @Date 2019/7/24 00:11
 **/
public class SequenceUtil {
	
	/**
	 * 获取下一个自增ID
	 *
	 * @param collName 集合名
	 * @return
	 */
	public static long getNextId(String collName) {
		Query query = new Query(Criteria.where("collName").is(collName));
		Update update = new Update();
		update.inc("seqId", 1);
		MongoTemplate mongoTemplate = MongoExampleApplication.getApplicationContext().getBean(MongoTemplate.class);
		if (mongoTemplate == null) {
			throw new RuntimeException("can't get the Bean:org.springframework.data.mongodb.core.MongoTemplate");
		}
		FindAndModifyOptions options = FindAndModifyOptions.options().upsert(true).returnNew(true);
		SequenceId seqId = mongoTemplate.findAndModify(query, update, options, SequenceId.class);
		return seqId.getSeqId();
	}
}
