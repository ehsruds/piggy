package poly.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import poly.service.IMelonService;

@Controller
public class MelonController {
	private Logger log = Logger.getLogger(this.getClass());
	
	
	@Resource(name="MelonService")
	private IMelonService melonService;
	
	
	@RequestMapping(value="melon/collectMelonRank")
	@ResponseBody
	public String collectMelonRank(HttpServletRequest request, HttpServletResponse response) throws Exception {
	
	log.info(this.getClass().getName() + ".collectMelonRank Start!");
	
	melonService.collectMelonRank();
	
	log.info(this.getClass().getName() + ".collectMelonRank End!");
	
	return "success";

	}
}
