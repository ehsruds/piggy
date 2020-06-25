package poly.service;

import java.util.List;

import poly.dto.MovieDTO;


public interface IMovieService {

	/**
	 * Top100 순위 수집하기
	 */
	public int collectMovieRank() throws Exception;

	/**
	 * MongoDB 데이터 가져오기
	 */
	public List<MovieDTO> getRank() throws Exception;


	
}

