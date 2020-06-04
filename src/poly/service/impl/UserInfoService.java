package poly.service.impl;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import poly.dto.UserInfoDTO;
import poly.persistance.mapper.UserInfoMapper;
import poly.service.IUserInfoService;
import poly.util.CmmUtil;

@Service("UserInfoService")
public class UserInfoService implements IUserInfoService {
	
	private Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name = "UserInfoMapper")
	private UserInfoMapper userInfoMapper;


	
	@Override
	public int insertUserInfo(UserInfoDTO pDTO) throws Exception {
		
		log.info(this.getClass().getName() + ".insertUserInfo start!");
		
		// 회원가입 성공 : 1, 아이디 중복으로인한 가입 취소 : 2, 기타 에러 발생 : 0
		int res = 0;

		// controller에서 값이 정상적으로 못 넘어오는 경우를 대비하기 위해 사용함
		if (pDTO == null) {
			pDTO = new UserInfoDTO();
			
			
		}
			
		
			log.info("id : " + pDTO.getUser_id());
			log.info("name : "+ pDTO.getUser_name());
			log.info("password : " + pDTO.getPassword());
			log.info("email : " + pDTO.getEmail());

			// 회원가입
			int success = userInfoMapper.insertUserInfo(pDTO);

			// db에 데이터가 등록되었다면(회원가입 성공했다면....
			if (success > 0) {
				res = 1;

				log.info(this.getClass().getName() + ".register okay!");
				
			} else {
				res = 0;
				log.info(this.getClass().getName() + ".insertUserInfo false!");
			}

			log.info(this.getClass().getName() + ".insertUserInfo end!");


		return res;
	}

	/**
	 * 로그인을 위해 아이디와 비밀번호가 일치하는지 확인하기
	 * 
	 * @param UserInfoDTO 로그인을 위한 회원아이디, 비밀번호
	 * @return UserInfoDTO 로그인된 회원아이디 정보
	 */
	@Override
	public int getUserLoginCheck(UserInfoDTO pDTO) throws Exception {

		// 로그인 성공 : 1, 실패 : 0
		int res = 0;
		
		// 로그인을 위해 아이디와 비밀번호가 일치하는지 확인하기 위한 mapper 호출하기
		UserInfoDTO rDTO = userInfoMapper.getUserLoginCheck(pDTO);

		if (rDTO == null) {
			rDTO = new UserInfoDTO();

		}
		
		/*
		 * #######################################################
		 *        				로그인 성공 여부 체크 시작!!
		 * #######################################################
		 */
		
		/*
		 * userInfoMapper로 부터 SELECT 쿼리의 결과로 회원아이디를 받아왔다면, 로그인 성공!!
		 * 
		 * DTO의 변수에 값이 있는지 확인하기 처리속도 측면에서 가장 좋은 방법은 변수의 길이를 가져오는 것이다.
		 * 따라서  .length() 함수를 통해 회원아이디의 글자수를 가져와 0보다 큰지 비교한다.
		 * 0보다 크다면, 글자가 존재하는 것이기 때문에 값이 존재한다.
		 */
		if (CmmUtil.nvl(rDTO.getUser_id()).length()>0) {
			res = 1;
			
			
		}

		/*
		 * #######################################################
		 *        				로그인 성공 여부 체크 끝!!
		 * #######################################################
		 */
		
		return res;
	}


}
