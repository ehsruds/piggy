package poly.persistance.mongo.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import com.mongodb.BasicDBObject;

import poly.dto.MelonDTO;
import poly.persistance.mongo.IMelonMapper;

@Component("MelonMapper")
public class MelonMapper implements IMelonMapper{
	
	@Autowired
	private MongoTemplate mongodb;
	
	private Logger log = Logger.getLogger(this.getClass());
	
	@Override
	public boolean createCollection(String colNm) throws Exception {
		
		log.info(this.getClass().getName() + ".createCollection start!");
		
		boolean res = false;
		
		if(mongodb.collectionExists(colNm)) {
			mongodb.dropCollection(colNm);
		}
		
		mongodb.createCollection(colNm).createIndex(new BasicDBObject("collect_time", 1).append("rank", 1), "rankIdx");
	
	res = true;
	
	log.info(this.getClass().getName() + ".createCollection End!");
	
	return res;
	
	}

	@Override
	public int insertRank(List<MelonDTO> pList, String colNm) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}


}
