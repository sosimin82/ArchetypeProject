package com.op.template.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import com.op.template.model.SampleContents;

@Aspect
@Component
public class SampleValidationCheckAspect {
	private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(SampleValidationCheckAspect.class);
	
	@Pointcut("within(com.nhnent.template.mvc.controller.*)")
	public void samplePointCutSignature() { }
	
	/**
	 * 필요한 aspect를 설정하여 validation check를 수행 
	 * 
	 * */
	@Around(value = "samplePointCutSignature() && args(sampleContents, bindingReulst, model)")
	public Object sampleAdvice(ProceedingJoinPoint joinPoint, SampleContents sampleContents, BindingResult bindingReulst, Model model) {
		LOGGER.info("sampleAdvice::Validate Parameters: sampleAdvice > " + sampleContents.toString());
		try {
			if (bindingReulst.hasErrors()) {
				LOGGER.error("Check the params");
				return "error";
			}
			return joinPoint.proceed();
		} catch (Throwable e) {
			e.printStackTrace();
			return "error";
		}
	}
	
}


