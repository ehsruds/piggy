package poly.persistance.mongo;

import java.util.List;

import poly.dto.MovieDTO;


public interface IMovieMapper {

	/**
	 * MongoDB 컬렉션 생성하기
	 * 
	 * @param colNm 생성하는 컬렉션 이름
	 */
	public boolean createCollection(String colNm2) throws Exception;

	/**
	 * MongoDB 데이터 저장하기
	 * 
	 * @param pDTO 저장될 정보
	 */
	public int insertMovieRank(List<MovieDTO> pList, String colNm2) throws Exception;

	/**
	 * MongoDB 영화 데이터 가져오기
	 * 
	 * @param colNm 가져올 컬렉션 이름
	 */
	public List<MovieDTO> getRank(String colNm2) throws Exception;




}
