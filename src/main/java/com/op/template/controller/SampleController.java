package com.op.template.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.op.template.model.SampleContents;
import com.op.template.service.SampleService;
//import com.op.template.util.NBaseArcMap;

@Controller
public class SampleController {
	private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(SampleController.class);
	
	@Autowired 
	private SampleService sampleService;
	
//	@Autowired
//	private NBaseArcMap nBaseArcMap;
	
	/**
	 * 메인화면 메서드
	 * @return String
	 * 
	 * */
	@RequestMapping(value = {"/", "/main"}, method = RequestMethod.GET)
	public String testMain(Model model) {
		// Log level 확인  (url뒤에 userNo를 넘기면 Log level이 바뀝니다.  -> userNo=NE11111)\
		LOGGER.debug("testMain: log level test: default=[info] ");
		LOGGER.debug("debug");
		LOGGER.info("info");
		LOGGER.warn("warn");
		LOGGER.error("error");
		
		model.addAttribute("msg", sampleService.doDbTest());

		return "sample";
	}

	/**
	 * sample contents를 가져와서 화면에 보여주는 메서드
	 * @param int seq  key가 되는 일련번호
	 * @return String
	 * 
	 * */
	@RequestMapping(value = "/viewer", method = RequestMethod.GET)
	public String getContents(int seq, Model model) {
		LOGGER.debug("getContents: seq=[" + seq + "s] ");
		model.addAttribute("sampleContents", sampleService.getSampleContents(seq));
		
		return "view";
	}
	
	/**
	 * sample contents를 입력할 수 있는 화면 메서드
	 * @return ModelAndView
	 * 
	 * */
	@RequestMapping(value = "/write-view", method = RequestMethod.GET)
	public String viewWrite() {
		return "write";
	}
	
	/**
	 * sample contents를 DB에 저장하는 메서드
	 * @param SampleContents sampleContents 화면에서 입력받은 내용을 저장하는 Value Object
	 * @return String
	 * 
	 * */	
	@RequestMapping(value = "/creator", method = RequestMethod.POST)
	public String createContents(@Valid SampleContents sampleContents, BindingResult bindingResult, Model model) {
		LOGGER.debug("createContents: SampleContents > " + sampleContents.toString());
		String msg = "Fail Create";
		if (sampleService.createSampleContens(sampleContents) == 1) {
			msg = "Success Create";
		}
		
		model.addAttribute("contentsList", sampleService.getSampleContentsList());
		model.addAttribute("msg", msg);
		return "list";
	}
	
	/**
	 * sample contents를 수정할 수 있는 화면 메서드
	 * @param int seq  수정하고자 하는 Contents의 일련번호
	 * @return String
	 * 
	 * */
	@RequestMapping(value = "/modify-view", method = RequestMethod.GET)
	public String viewModify(int seq, Model model) {
		LOGGER.debug("viewModify: seq=[" + seq + "]");
		
		model.addAttribute("sampleContents", sampleService.getSampleContents(seq));
		return "write";
	}
	
	/**
	 * sample contents DB를 수정하는 메서드
	 * @param SampleContents sampleContents  수정하려는 Contents의 정보
	 * @return String
	 * 
	 * */
	@RequestMapping(value = "/modifier", method = RequestMethod.POST)
	public String modifyContents(@Valid SampleContents sampleContents, BindingResult bindingReulst, Model model) {
		LOGGER.debug("modifyContents: SampleContents > " + sampleContents.toString());
		String msg = "Fail Modify";
		if (sampleService.modifySampleContents(sampleContents) == 1) {
			msg = "Success Modify";
		}
		
		model.addAttribute("contentsList", sampleService.getSampleContentsList());
		model.addAttribute("msg", msg);
		return "list";
	}
	
	/**
	 * sample contents DB를 삭제하는 메서드
	 * @param int seq  삭제하고자 하는 Contents의 일련번호
	 * @return String
	 * 
	 * */
	@RequestMapping(value = "/remover", method = RequestMethod.GET)
	public String removeContents(int seq, Model model) {
		LOGGER.debug("removeContents: seq=[" + seq + "]");
		String msg = "Fail Remove";
		if (sampleService.removeSampleContens(seq) == 1) {
			msg = "Success Remove";
		}
		
		model.addAttribute("contentsList", sampleService.getSampleContentsList());
		model.addAttribute("msg", msg);
		return "list";
	}
	
	/**
	 * sample contents list 메서드
	 * @return String
	 * 
	 * */
	@RequestMapping(value = "/contents-list", method = RequestMethod.GET)
	public String getContentsLists(Model model) {
		model.addAttribute("contentsList", sampleService.getSampleContentsList());
		return "list";
	}
	
//	/**
//	 * nBase-ARC 에 값을 조회할 수 있는 메서드(key: msg)
//	 * @return String
//	 * 
//	 * */
//	@RequestMapping(value = "/check-nbase-arc", method = RequestMethod.GET)
//	public String checkCache(Model model) {
//		model.addAttribute("msg", nBaseArcMap.getValueFromNBaseArc("msg"));
//		return "nbaseArc";
//	}
//	
//	/**
//	 * nBase-ARC 에 값을 저장할 수 있는 메서드(key: msg)
//	 * @return String
//	 * 
//	 * */
//	@RequestMapping(value = "/nbase-arc", method = RequestMethod.GET)
//	public String setCache(Model model) {
//		String msg = "This is Saved Data in nBase-ARC";
//		nBaseArcMap.setValueToNBaseArc("msg", msg, 10);
//		model.addAttribute("msg", nBaseArcMap.getValueFromNBaseArc("msg"));
//		
//		return "nbaseArc";
//	}
	
	
}
