package com.op.template.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.op.template.model.SampleContents;
import com.op.template.service.SampleService;

@RequestMapping("/rest")
@Controller
public class SampleRestController {
	private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(SampleRestController.class);
	
	@Autowired 
	SampleService sampleService;
	
	/*
	 *  응답을 Json으로 return하는 Controller 기본 기능은 SampleController와 동일
	 * 
	 *   REST 명세
	 *   Service Code: 10
	 *   Result Code
	 *    1) 성공: 0
	 *    2) 실패: 9
	 * 
	 */

	/**
	 * contents의 seq를 이용하여 조회하는 메서드 
	 * */
	@RequestMapping(value = "/contents/{seq}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Map<String, Object> getContents(@PathVariable int seq) {
		LOGGER.info("this is data from DB");
		SampleContents sampleContentsResult = sampleService.getSampleContents(seq);
		
        Map<String, Object> header = this.createHeader(10, 0, new String[] {"getContents: Success getContents"});
		
        Map<String, Object> result = new HashMap<String, Object>();
		result.put("header", header);
		result.put("seq", String.valueOf(seq));
		result.put("message", sampleContentsResult.getMsg());
		return result;
	}
	
	@RequestMapping(value = "/creator", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Map<String, Object> createContents(@RequestBody SampleContents sampleContents) {
		int executeCount = sampleService.createSampleContens(sampleContents);
		LOGGER.info(sampleContents.toString() + " / executeCount:" + executeCount);
		String msg = "Fail Create";
		int resultCode = 9;
		if (executeCount == 1) {
			msg = "Success Create";
			resultCode = 0;
		}
		
		Map<String, Object> header = this.createHeader(10, resultCode, new String[] {"createContents: " + msg});
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("header", header);
		result.put("executeCount", executeCount);
		return result;
	}
	
	@RequestMapping(value = "/modifier", method = RequestMethod.PUT, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Map<String, Object> modifyContents(@RequestBody SampleContents sampleContents) {
		int executeCount = sampleService.modifySampleContents(sampleContents);
		LOGGER.info(sampleContents.toString() + " / executeCount:" + executeCount);
		String msg = "Fail Modify";
		int resultCode = 9;
		if (executeCount == 1) {
			msg = "Success Modify";
			resultCode = 0;
		}
		SampleContents resultSampleContents = sampleService.getSampleContents(sampleContents.getSeq());
		
		Map<String, Object> header = this.createHeader(10, resultCode, new String[] {"modifyContents: " + msg});
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("header", header);
		result.put("seq", resultSampleContents.getSeq());
		result.put("message", resultSampleContents.getMsg());
		return result;
	}
	

	@RequestMapping(value = "/remover/{seq}", method = RequestMethod.DELETE, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Map<String, Object> removeContents(@PathVariable int seq) {
		String msg = "Fail Remove";
		int resultCode = 9;
		if (sampleService.removeSampleContens(seq) == 1) {
			msg = "Success Remove";
			resultCode = 0;
		}
		
		Map<String, Object> header = this.createHeader(10, resultCode, new String[] {"removeContents: " + msg});
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("header", header);
		result.put("seq", seq);
		return result;
	}
	
	@RequestMapping (value = "/list", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Map<String, Object> getJsonContentsList() {
		Map<String, Object> header = this.createHeader(10, 0, new String[] {"getJsonContentList: Success getJsonContentsList"});
		List<SampleContents> contentsList = sampleService.getSampleContentsList();
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("header", header);
		result.put("contentsList", contentsList);
		return result;
	}
	
	@RequestMapping("/contents-list")
	public String getContentList(Model model) {
		model.addAttribute("contentsList", sampleService.getSampleContentsList());
		return "listRest";
	}
	
	
	@RequestMapping("/write-view")
	public String viewWrite() {
		return "writeRest";
	}
	

	@RequestMapping("/modify-view")
	public String viewModify(int seq, Model model) {
		model.addAttribute("sampleContents", sampleService.getSampleContents(seq));
		return "writeRest";
	}
	
	
	/**
	 * REST header 부의 정보를 Map<String, Object> 의 형태로 반환
	 * @param int serviceCode 정의된 Service Code를 기입
	 * @param int resultCode 정의된 Result Code를 기입
	 * @param String[] resultMessage 결과 메시지를 String 배열로 작성하여 전달
	 * 
	 * */
	public Map<String, Object> createHeader(int serviceCode, int resultCode, String[] resultMessage) {
		Map<String, Object> header = new HashMap<String, Object>();
		header.put("serviceCode", serviceCode);
		header.put("resultCode", resultCode);
		header.put("resultMessage", resultMessage);
		return header;
	}
	
}
