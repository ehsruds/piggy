package poly.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import poly.dto.MovieDTO;
import poly.service.IMovieService;

/*
 * Controller 선언해야만 Spring 프레임워크에서 Controller인지 인식 가능
 * 자바 서블릿 역할 수행
 * */
@Controller
public class MovieController {
	private Logger log = Logger.getLogger(this.getClass());

	/*
	 * 비즈니스 로직(중요 로직을 수행하기 위해 사용되는 서비스를 메모리에 적재(싱글톤패턴 적용됨)
	 */
	@Resource(name = "MovieService")
	private IMovieService movieService;

	/**
	 *   수집하기
	 */
	@RequestMapping(value = "movie/collectMovieRank")
	@ResponseBody
	public String collectMovieRank(HttpServletRequest request, HttpServletResponse response) throws Exception {

		log.info(this.getClass().getName() + ".collectMovieRank Start!");

		movieService.collectMovieRank();

		log.info(this.getClass().getName() + ".collectMovieRank End!");

		return "success";
	}

	/**
	 *  데이터 가져오는 일반 화면
	 */
	@RequestMapping(value = "movie/movieRank")
	public String movieRank(HttpServletRequest request, HttpServletResponse response) throws Exception {

		log.info(this.getClass().getName() + ".movie/movieRank Start!");

		log.info(this.getClass().getName() + ".movie/movieRank End!");

		return "/movie/movieRank";
	}

	/**
	 *  데이터 가져오기
	 */
	@RequestMapping(value = "movie/getRank")
	@ResponseBody
	public List<MovieDTO> getRank(HttpServletRequest request, HttpServletResponse response) throws Exception {

		log.info(this.getClass().getName() + ".getRankController Start!");

		List<MovieDTO> rList = movieService.getRank();

		if (rList == null) {
			rList = new ArrayList<MovieDTO>();
		}

		log.info(this.getClass().getName() + ".getRankController End!");

		return rList;
	}

	
	}
	
