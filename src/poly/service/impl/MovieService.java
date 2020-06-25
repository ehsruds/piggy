package poly.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import poly.dto.MovieDTO;
import poly.persistance.mongo.IMovieMapper;
import poly.service.IMovieService
;
import poly.util.DateUtil;

@Service("MovieService")
public class MovieService implements IMovieService {
	
	@Resource(name = "MovieMapper")
	private IMovieMapper movieMapper;
	
	private Logger log =Logger.getLogger(this.getClass());

		@Override
		public int collectMovieRank() throws Exception {
			
			log.info(this.getClass().getName() + ".collectMovie Start!");
			
			int res = 0;
			
			List<MovieDTO> pList = new ArrayList<MovieDTO>();
			
			// Jsoup를 이용해서 http://www.cgv.co.kr/movies/ 크롤링
			String url = "https://movie.naver.com/movie/running/current.nhn?view=list&tab=normal&order=reserve"; //크롤링할 url지정
			
			Document doc = null;  //Document에는 페이지의 전체 소스가 저장된다

			doc = Jsoup.connect(url).get();
			
			//select를 이용하여 원하는 태그를 선택한다. select는 원하는 값을 가져오기 위한 중요한 기능이다.
			Elements element = doc.select("div.lst_wrap");   
			
			Iterator<Element> movieList = element.select("ul.lst_detail_t1 > li").iterator();

			while (movieList.hasNext()) {
				Element movieInfo = movieList.next();
				
				String img = movieInfo.select("div.thumb > a > img").attr("src");
				String title = movieInfo.select("dt.tit > a").text();
				String m_url = movieInfo.select("dt.tit > a").attr("href");
				
				movieInfo = null;
				
				MovieDTO pDTO = new MovieDTO();
				pDTO.setCollect_time(DateUtil.getDateTime("yyyyMMddhhmmss"));
				pDTO.setTitle(title);
				pDTO.setImg(img);
				pDTO.setM_url(m_url);
				
				pList.add(pDTO);
				
			}
			
			String colNm2 = "MovieRank" + DateUtil.getDateTime("yyyyMMdd");
			
			movieMapper.createCollection(colNm2);
			
			movieMapper.insertMovieRank(pList, colNm2);
			
			log.info(this.getClass().getName() + ".collectMovieRank End!");
			
			return res;
		}

		@Override
		public List<MovieDTO> getRank() throws Exception {

			log.info(this.getClass().getName() + ".getRankService Start!");

			// 조회할 컬렉션 이름
			String colNm2 = "MovieRank_" + DateUtil.getDateTime("yyyyMMdd"); // 생성할 컬렉션명

			List<MovieDTO> rList = movieMapper.getRank(colNm2);

			if (rList == null) {
				rList = new ArrayList<MovieDTO>();
			}

			log.info(this.getClass().getName() + ".getRankService End!");

			return rList;
		}



}
