package poly.persistance.mongo.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;

import poly.dto.MovieDTO;
import poly.persistance.mongo.IMovieMapper;
import poly.util.CmmUtil;

@Component("MovieMapper")
public class MovieMapper implements IMovieMapper {
	
	@Autowired
	private MongoTemplate mongodb;
	
	private Logger log = Logger.getLogger(this.getClass());
	
	@Override
	public boolean createCollection(String colNm2) throws Exception {
		
		log.info(this.getClass().getName() + ".createCollection start!");
		
		boolean res = false;
		
		if(mongodb.collectionExists(colNm2)) {
			mongodb.dropCollection(colNm2);
		}
		
		mongodb.createCollection(colNm2).createIndex(new BasicDBObject("collect_time", 1));
		
		res = true;
		
		log.info(this.getClass().getName()+ ".createCollection End!");
		
		return res;
	}
	
	@Override
	public int insertMovieRank(List<MovieDTO> pList, String colNm2) throws Exception {
		log.info(this.getClass().getName() + ".insertRank start!");
		
		int res = 0;
		
		if(pList == null) {
			pList = new ArrayList<MovieDTO>();
		}
		
		Iterator<MovieDTO> it = pList.iterator();
		
		while (it.hasNext()) {
			MovieDTO pDTO = (MovieDTO) it.next();
			
			if(pDTO == null) {
				pDTO = new MovieDTO();
			}
			
			mongodb.insert(pDTO, colNm2);
		}
		
		res = 1;
		
		log.info(this.getClass().getName() + ".insertRank End!");
		
		return res;
	}
	
	@Override
	public List<MovieDTO> getRank(String colNm2) throws Exception {
		
		log.info(this.getClass().getName() + ".getRankMapper Start!");

		// 데이터를 가져올 컬렉션 선택
		DBCollection rCol2 = mongodb.getCollection(colNm2);

		// 컬렉션으로부터 전체 데이터 가져오기
		Iterator<DBObject> cursor = rCol2.find();

		// 컬렉션으로부터 전체 데이터 가져온 것을 List 형태로 저장하기 위한 변수 선언
		List<MovieDTO> rList = new ArrayList<MovieDTO>();

		// 퀴즈팩별 정답률 일자별 저장하기
		MovieDTO rDTO = null;

		while (cursor.hasNext()) {

			rDTO = new MovieDTO();

			final DBObject current = cursor.next();

			String collect_time = CmmUtil.nvl((String) current.get("collect_time")); // 수집시간
			String img = CmmUtil.nvl((String) current.get("img")); // 
			String title = CmmUtil.nvl((String) current.get("title")); // 
			String m_url = CmmUtil.nvl((String) current.get("m_url")); // 
		
			rDTO.setCollect_time(collect_time);
			rDTO.setImg(img);
			rDTO.setTitle(title);
			rDTO.setM_url(m_url);

			rList.add(rDTO); // List에 데이터 저장

			rDTO = null;

		}

		log.info(this.getClass().getName() + ".getRankMapper End!");

		return rList;
		
	}


}
